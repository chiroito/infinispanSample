import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

public class JCacheEntryProcessorEmbedded {

    public static void main(String[] args) {
        CachingProvider jcacheProvider = Caching.getCachingProvider();
        CacheManager cacheManager = jcacheProvider.getCacheManager();

        MutableConfiguration<String, String> configuration = new MutableConfiguration<>();
        configuration.setTypes(String.class, String.class);
        Cache<String, String> cache = cacheManager.createCache("mycache", configuration);

        // データを格納する
        cache.put("key1", "value1");

        // Entry Processor を実行(Lambda式版)
        cache.invoke("key1", (entry, arg) -> {
            System.out.println(entry.getValue());
            return entry.getValue();
        });

        // Entry Processor を実行(クラス版)
        cache.invoke("key1", new CustomJCacheEntryProcessor());

        cacheManager.close();
        jcacheProvider.close();
    }
}
