package chiroito.sample.benchmark;
import org.infinispan.Cache;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.openjdk.jmh.annotations.*;

@Warmup(iterations = 1)
@BenchmarkMode(Mode.Throughput)
@Fork(1)
@Threads(2)
public class CacheAccess {

    private static Cache<String, String> c;

    static {
        EmbeddedCacheManager manager = new DefaultCacheManager();
        manager.defineConfiguration("custom-cache", new ConfigurationBuilder()
                .build());
        c = manager.getCache("custom-cache");
        c.put("key", "value");
    }

    @Benchmark
    public void get(){
        c.get("key");
    }
}