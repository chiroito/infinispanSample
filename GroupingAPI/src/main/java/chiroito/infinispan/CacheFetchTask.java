package chiroito.infinispan;

import org.infinispan.AdvancedCache;
import org.infinispan.commons.util.IntSet;
import org.infinispan.commons.util.IntSets;
import org.infinispan.distribution.LocalizedCacheTopology;
import org.infinispan.tasks.ServerTask;
import org.infinispan.tasks.TaskContext;
import org.infinispan.tasks.TaskExecutionMode;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CacheFetchTask implements ServerTask<Integer> {

    private final ThreadLocal<AdvancedCache<?, ?>> parentThreadLocal = new ThreadLocal<>();
    private final ThreadLocal<AdvancedCache<?, ?>> childThreadLocal = new ThreadLocal<>();

    @Override
    public void setTaskContext(TaskContext taskContext) {
        String childCacheName = taskContext.getParameters().get().get("childCacheName").toString();

        AdvancedCache<?, ?> parentCache = taskContext.getCache().get().getAdvancedCache();
        AdvancedCache<?, ?> childCache = parentCache.getCacheManager().getCache(childCacheName).getAdvancedCache();

        parentThreadLocal.set(parentCache);
        childThreadLocal.set(childCache);
    }

    @Override
    public Integer call() throws Exception {

        // Output local primary segments and data in the segment which this node has
        outputCacheInformation(parentThreadLocal.get());

        // Output local primary segments and data in the segment which this node has
        outputCacheInformation(childThreadLocal.get());

        return null;
    }

    @Override
    public String getName() {
        return "cacheFetchTask";
    }

    @Override
    public TaskExecutionMode getExecutionMode() {
        return TaskExecutionMode.ALL_NODES;
    }


    private <K, V> void outputCacheInformation(AdvancedCache<K, V> cache) {

        LocalizedCacheTopology cacheTopology = cache.getDistributionManager().getCacheTopology();

        // Retrieving local primary segments which this node has
        IntSet localReadSegments = cache.getDistributionManager().getCacheTopology().getLocalReadSegments();

        System.out.println("*************");

        // Output the primary segments
        Predicate<Integer> primarySegmentFilter = s -> cacheTopology.getSegmentDistribution(s).isPrimary();
        IntSet primarySegments = IntSets.from(localReadSegments.stream().filter(primarySegmentFilter).collect(Collectors.toUnmodifiableSet()));
        outputSegmentsInformation(cache, "Primary segments", primarySegments);

        // Output the backup segments
        Predicate<Integer> backupSegmentFilter = s -> cacheTopology.isSegmentWriteOwner(s) && !cacheTopology.getSegmentDistribution(s).isPrimary();
        IntSet backupSegments = IntSets.from(localReadSegments.stream().filter(backupSegmentFilter).collect(Collectors.toUnmodifiableSet()));
        outputSegmentsInformation(cache, "Backup segments", backupSegments);
    }

    private <K, V> void outputSegmentsInformation(AdvancedCache<K, V> cache, String message, IntSet segments) {

        String targetSegmentsStr = segments.stream().sorted().map(i -> String.valueOf(i)).collect(Collectors.joining(", ", "", ""));

        System.out.println(message);
        System.out.println(targetSegmentsStr);
        System.out.println();

        LocalizedCacheTopology cacheTopology = cache.getDistributionManager().getCacheTopology();
        Map<K, SegmentInfo> allKeyAndSegmentInfo = new HashMap<>();

        for (Integer storedSegment : segments) {
            if (cache.entrySet().stream().filterKeySegments(IntSets.immutableSet(storedSegment)).count() == 0) {
                // if no data is in this storedSegment, nothing to output
                continue;
            }

            // Retrieving keys which this node has in the local primary storedSegments
            Set<Map.Entry<K, V>> dataInSegment = cache.entrySet().stream().filterKeySegments(IntSets.immutableSet(storedSegment)).collect(Collectors.toUnmodifiableSet());

            // store storedSegment information per key
            dataInSegment.forEach(e -> {
                K key = e.getKey();
                int calculatedSegment = cacheTopology.getSegment(key);
                allKeyAndSegmentInfo.put(key, new SegmentInfo(calculatedSegment, storedSegment));
            });
        }

        new TreeMap<>(allKeyAndSegmentInfo).entrySet().stream().forEach(e -> System.out.println(e.getKey() + " : " + e.getValue()));

        System.out.println();
    }

    public class SegmentInfo {

        private final int calculatedSegment;
        private final int storedSegment;

        public SegmentInfo(int calculatedSegment, int storedSegment) {
            this.calculatedSegment = calculatedSegment;
            this.storedSegment = storedSegment;
        }

        public int getCalculatedSegment() {
            return calculatedSegment;
        }

        public int getStoredSegment() {
            return storedSegment;
        }

        @Override
        public String toString() {
            return "SegmentInfo{ " +
                    "calculatedSegment=" + calculatedSegment +
                    ", storedSegment=" + storedSegment +
                    " }";
        }
    }
}