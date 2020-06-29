import javax.cache.*;
import javax.cache.configuration.*;
import javax.cache.spi.CachingProvider;
import java.util.Properties;

public class JCacheEntryProcessorRemote {

    public static void main(String[] args) {
        CachingProvider jcacheProvider = Caching.getCachingProvider();
        Properties prop = new Properties();
        prop.setProperty("infinispan.client.hotrod.server_list", "127.0.0.1:11222");
        CacheManager cacheManager = jcacheProvider.getCacheManager(jcacheProvider.getDefaultURI(), jcacheProvider.getDefaultClassLoader(), prop);

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
