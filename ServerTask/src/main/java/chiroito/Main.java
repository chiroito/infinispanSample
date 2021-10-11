package chiroito;

import chiroito.task.LibraryInitializer;
import chiroito.task.StockEntity;
import org.infinispan.client.hotrod.DefaultTemplate;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.Configuration;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.commons.api.CacheContainerAdmin;
import org.infinispan.commons.marshall.ProtoStreamMarshaller;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    private static final String hostname = "127.0.0.1";
    private static final int port = 11222;
    private static final boolean isAuth = true;
    private static final String user = "admin";
    private static final String password = "password";

    public static void main(String[] args) throws Exception{

        Configuration configuration = createConfiguration();

        RemoteCacheManager manager = new RemoteCacheManager(configuration);
        RemoteCache<Object, Object> cache = manager.administration().withFlags(CacheContainerAdmin.AdminFlag.VOLATILE).getOrCreateCache("custom-cache", DefaultTemplate.DIST_SYNC);

        // サーバのPIDを取得する
        checkPid(cache);

        // サーバで処理をしているスレッドの情報を取得する
        checkThreadInformation(cache);

        // サーバで処理する際に排他処理を行うスタックを取得する
        checkCacheCompute(cache);

        // 負荷試験、排他処理がきちんと行われる
        rushTest(cache, "StockAllocationComputeTask", (i) -> cache.put(1, 100_000), (i) -> (Integer) cache.get(1));

        // 負荷試験、排他処理が行われないため、結果としてはエラーになる
        rushTest(cache, "StockAllocationGetPutTask", (i) -> cache.put(1, 100_000), (i) -> (Integer) cache.get(1));

        // 負荷試験、独自のクラスをキャッシュするようにして、排他的な処理をする
        RemoteCache<Integer, StockEntity> stockCache = manager.administration().withFlags(CacheContainerAdmin.AdminFlag.VOLATILE).getOrCreateCache("stock-cache", DefaultTemplate.DIST_SYNC);
        rushTest(stockCache, "StockAllocationComputeWithLogicTask", (i) -> stockCache.put(1, new StockEntity(100_000)), (i) -> stockCache.get(1).getNum());

        manager.stop();
    }

    private static Configuration createConfiguration() throws Exception{

//        LibraryInitializer initializer = (LibraryInitializer)(Class.forName("chiroito.task.LibraryInitializerImpl").getConstructor().newInstance());

        ConfigurationBuilder cb
                = new ConfigurationBuilder();
        cb.marshaller(new ProtoStreamMarshaller())
                .statistics()
                .enable()
                .jmxDomain("org.infinispan")
                .addServer()
                .host(hostname)
                .port(port)
                .addContextInitializer("chiroito.task.LibraryInitializerImpl")
                // デフォルトは10
                .asyncExecutorFactory().addExecutorProperty("infinispan.client.hotrod.default_executor_factory.pool_size", "10")
                //デフォルトは100,000
                .addExecutorProperty("infinispan.client.hotrod.default_executor_factory.queue_size", "100000");

        if (isAuth) {
            cb.security()
                    .authentication()
                    .realm("default")
                    .saslMechanism("DIGEST-MD5")
                    .username(user)
                    .password(password);
        }

        return cb.build();
    }

    private static void checkPid(RemoteCache<Object, Object> cache) {

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("PidTask");

        ProcessHandle currentProcess = ProcessHandle.current();
        final long currentPid = currentProcess.pid();
        System.out.println(String.format("Client PID = %d", currentPid));

        //キャッシュの初期化
        IntStream.range(0, 100).forEach(i -> cache.put(i, i));

        //サーバのPIDを回収して出力
        Set<String> pidSet = IntStream.range(0, 100).mapToObj(i -> cache.execute("pidTask", Collections.EMPTY_MAP, i).toString()).collect(Collectors.toSet());
        pidSet.stream().forEach(pid -> System.out.println(String.format("Server PID = %s", pid)));

        // キャッシュをクリア
        cache.clear();
    }

    private static void checkThreadInformation(RemoteCache<Object, Object> cache) {

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("ThreadInfoTask");

        cache.put(1, 1);

        String threadInfoMessage = (String) cache.execute("ThreadInfoTask", Collections.EMPTY_MAP, 1);
        System.out.println(threadInfoMessage);

        cache.clear();
    }

    private static void checkCacheCompute(RemoteCache<Object, Object> cache) {

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("CacheComputeTask");

        cache.put(1, 1);

        String threadInfoMessage = (String) cache.execute("CacheCompute", Collections.EMPTY_MAP, 1);
        System.out.println(threadInfoMessage);

        cache.clear();
    }

    private static void rushTest(RemoteCache<?, ?> cache, String taskName, Consumer<Void> initialProcess, Function<Integer, Integer> getProcess) {

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");

        final int orderItemNo = 1;
        final int stockNum = 100_000;

        //キャッシュを初期化
        initialProcess.accept(null);

        //処理のパラメータを作成
        Map<String, Object> orderInfo = new HashMap<>();
        orderInfo.put("ItemNo", orderItemNo);
        orderInfo.put("Num", 1);

        //負荷をかけるスレッドを作成
        int rushThreadNum = 10;
        int rushTaskQueueSize = Integer.MAX_VALUE;
        LinkedBlockingQueue<Runnable> rushTaskQueue = new LinkedBlockingQueue<>(rushTaskQueueSize);
        RejectedExecutionHandler rushTaskHandler = new ThreadPoolExecutor.DiscardOldestPolicy();
        ExecutorService executorService = new ThreadPoolExecutor(rushThreadNum, rushThreadNum,
                0L, TimeUnit.MILLISECONDS, rushTaskQueue,
                rushTaskHandler);

        // 負荷をかける前のメッセージを出力
        System.out.println(taskName);
        System.out.println("Stocked Item Num : " + stockNum);

        // 負荷をかける
        Runnable rushClientTask = () -> {
            cache.execute(taskName, orderInfo, orderItemNo);
        };
        IntStream.range(0, stockNum).forEach(i -> {
            executorService.execute(rushClientTask);
        });

        // クライアントからの負荷を全部処理し終わるのを待つ
        while (!rushTaskQueue.isEmpty()) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //負荷かけ後の在庫を出力
        System.out.println("After rushing, Stocked Item Num : " + getProcess.apply(1));

        // 事後処理
        executorService.shutdownNow();
        cache.clear();
    }
}
