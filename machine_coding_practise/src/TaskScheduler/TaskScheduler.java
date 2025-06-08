package TaskScheduler;

import java.util.concurrent.DelayQueue;

public interface TaskScheduler {
    void schedule(int taskId, int delaySeconds);
    void cancel(int taskID);
    void run() throws InterruptedException;
}