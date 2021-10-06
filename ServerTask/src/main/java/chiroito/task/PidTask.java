package chiroito.task;

import org.infinispan.tasks.ServerTask;
import org.infinispan.tasks.TaskContext;

/**
 * ServerTaskがServerで実行されていることを確認する
 */
public class PidTask implements ServerTask<Long> {

    private TaskContext taskContext;

    @Override
    public void setTaskContext(TaskContext taskContext) {
        this.taskContext = taskContext;
    }

    @Override
    public Long call() throws Exception {
        ProcessHandle process = ProcessHandle.current();
        return process.pid();
    }

    @Override
    public String getName() {
        return "pidTask";
    }
}