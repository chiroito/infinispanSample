package chiroito.task;

import org.infinispan.tasks.ServerTask;
import org.infinispan.tasks.TaskContext;

import java.io.CharArrayWriter;
import java.io.PrintWriter;

public class ThreadInfoTask implements ServerTask<String> {

    private TaskContext taskContext;

    @Override
    public void setTaskContext(TaskContext taskContext) {
        this.taskContext = taskContext;
    }

    @Override
    public String call() throws Exception {

        Thread currentThread = Thread.currentThread();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ThraedGroup : " + currentThread.getThreadGroup().getName() + System.lineSeparator());
        stringBuilder.append("Thraed : " + currentThread.getName() + System.lineSeparator());
        stringBuilder.append("StackTrace :" + System.lineSeparator());

        CharArrayWriter charArrayWriter = new CharArrayWriter();
        PrintWriter printWriter = new PrintWriter(charArrayWriter);

        new Exception().printStackTrace(printWriter);
        printWriter.flush();

        stringBuilder.append(charArrayWriter.toString());

        return stringBuilder.toString();
    }

    @Override
    public String getName() {
        return "ThreadInfoTask";
    }
}