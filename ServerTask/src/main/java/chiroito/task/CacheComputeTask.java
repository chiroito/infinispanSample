package chiroito.task;

import org.infinispan.Cache;
import org.infinispan.tasks.ServerTask;
import org.infinispan.tasks.TaskContext;
import org.infinispan.util.function.SerializableBiFunction;

import java.io.CharArrayWriter;
import java.io.PrintWriter;

public class CacheComputeTask implements ServerTask<String> {

    private TaskContext taskContext;

    @Override
    public void setTaskContext(TaskContext taskContext) {
        this.taskContext = taskContext;
    }

    @Override
    public String call() throws Exception {

        // キャッシュを取得
        Cache<Integer, Integer> userCache = (Cache<Integer, Integer>) this.taskContext.getCache().get();

        //業務処理を作成
        CacheTask task = new CacheTask();

        // 業務処理を実行
        Integer newCachedData = userCache.computeIfPresent(1, task);

        return task.message;
    }

    @Override
    public String getName() {
        return "CacheCompute";
    }


    class CacheTask implements SerializableBiFunction<Integer, Integer, Integer> {

        public String message;

        @Override
        public Integer apply(Integer userId, Integer cachedData) {

            CharArrayWriter charArrayWriter = new CharArrayWriter();
            PrintWriter printWriter = new PrintWriter(charArrayWriter);

            new Exception().printStackTrace(printWriter);
            printWriter.flush();

            message = charArrayWriter.toString();

            return 1;
        }
    };
}
