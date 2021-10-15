package chiroito.task;

import chiroito.domain.Stock;
import chiroito.entity.AllocationHistoryKey;
import chiroito.entity.AllocationHistoryValue;
import chiroito.entity.AllocationRequest;
import chiroito.entity.AllocationResponse;
import chiroito.entity.StockEntity;
import org.infinispan.Cache;
import org.infinispan.distribution.LocalizedCacheTopology;
import org.infinispan.tasks.ServerTask;
import org.infinispan.tasks.TaskContext;
import org.infinispan.util.function.SerializableBiFunction;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class StockAllocationWithHistoryTask implements ServerTask<chiroito.entity.AllocationResponse> {

    private TaskContext context;

    @Override
    public void setTaskContext(TaskContext taskContext) {
        this.context = taskContext;
    }

    /**
     * ServerSideTask本体
     *
     * @return
     * @throws Exception
     */
    @Override
    public chiroito.entity.AllocationResponse call() throws Exception {

        try {
            // 実行時のパラメータを取得
            Map<String, ?> param = this.context.getParameters().get();
            String orderItemNo = (String) param.get("ItemNo");
            int orderNum = (Integer) param.get("Num");

            chiroito.entity.AllocationRequest request = new chiroito.entity.AllocationRequest(orderNum);

            // キャッシュを取得
            Cache<String, chiroito.entity.StockEntity> userCache = (Cache<String, StockEntity>) this.context.getCache().get();

            //業務処理を作成
            AllocateTask allocateTask = new AllocateTask(request);

            // 業務処理を実行
            chiroito.entity.StockEntity newCachedData = userCache.compute(orderItemNo, allocateTask);

            Cache<Object, Object> historyCache = context.getCacheManager().getCache("allocation-history");
            Object key = new AllocationHistoryKey(orderItemNo, UUID.randomUUID().toString());
            Object value = new AllocationHistoryValue(orderNum);
            historyCache.put(key, value);

            // データの偏りを確認
            LocalizedCacheTopology cacheTopology = userCache.getAdvancedCache().getDistributionManager().getCacheTopology();

            System.out.println("+++++++++++++++++++++++++++++");
            System.out.println("在庫の情報がどこのセグメントにあるのかを出力");
            for (Map.Entry e : context.getCacheManager().getCache("custom-cache").entrySet()) {
                Object k = e.getKey();
                String ownerInfo = cacheTopology.getWriteOwners(k).stream().map(Object::toString).collect(Collectors.joining(","));
                System.out.println(String.format("StockEntity : Key = %s, Segment = %d, Member = %s", k.toString(), cacheTopology.getSegment(k), ownerInfo));
            }
            System.out.println("在庫履歴の情報がどこのセグメントにあるのかを出力");
            for (Map.Entry e : historyCache.entrySet()) {
                Object k = e.getKey();
                String ownerInfo = cacheTopology.getWriteOwners(k).stream().map(Object::toString).collect(Collectors.joining(","));
                System.out.println(String.format("HistoryEntity : Key = %s, Segment = %d, Member = %s", k.toString(), cacheTopology.getSegment(k), ownerInfo));
            }
            System.out.println("+++++++++++++++++++++++++++++");

            return new chiroito.entity.AllocationResponse(newCachedData != null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AllocationResponse(false);
    }

    @Override
    public String getName() {
        return "StockAllocationWithHistoryTask";
    }

    /**
     * キャッシュに対して行う処理
     */
    class AllocateTask implements SerializableBiFunction<String, chiroito.entity.StockEntity, chiroito.entity.StockEntity> {

        private final chiroito.entity.AllocationRequest request;

        public AllocateTask(AllocationRequest request) {
            this.request = request;
        }

        @Override
        public chiroito.entity.StockEntity apply(String userId, chiroito.entity.StockEntity cachedStockData) {

            if(cachedStockData == null){
                // データがまだなければ在庫は10とする。現実的にはあり得ないが検証のため。
                cachedStockData = new chiroito.entity.StockEntity(10);
            }
            // キャッシュされた情報からドメインオブジェクトを作成
            chiroito.domain.Stock stock = new chiroito.domain.Stock(Integer.parseInt(userId), cachedStockData.getNum());

            Stock newStock = stock.allocate(request);

            // 新たにキャッシュに格納されるデータ
            return new chiroito.entity.StockEntity(newStock.getStockedNum());
        }
    }
}
