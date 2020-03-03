package chiroito.sample;

import org.infinispan.Cache;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import protostream.com.google.gson.internal.bind.util.ISO8601Utils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CacheSample {

    public static void main(String[] args) throws Exception {
        EmbeddedCacheManager manager = new DefaultCacheManager();
        manager.defineConfiguration("custom-cache", new ConfigurationBuilder()
                .build());
        Cache<Object, Object> c = manager.getCache("custom-cache");
        c.put("key", "value");

        System.out.println(c.get("key"));

        c.forEach((k, v) -> System.out.println(k + ":" + v));
        CompletableFuture<Object> future = c.computeAsync("key", (k, v) -> {
            System.out.println(k + ":" + v + " @ " + Thread.currentThread().getName());
            return null;
        });

        System.out.println("wait");
        TimeUnit.SECONDS.sleep(10);
        future.get();
    }
}

