package TaskScheduler;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Task implements Delayed {
    public int getTaskId() {
        return taskId;
    }
    int taskId;
    private final long executeAt;

    public Task(int taskId, int delay) {
        this.taskId = taskId;
        this.executeAt = System.currentTimeMillis() + delay * 1000;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long delayVal = executeAt - System.currentTimeMillis();
        return unit.convert(delayVal, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (o == this) return 0;
        if (o instanceof Task) {
            return Long.compare(this.executeAt, ((Task) o).executeAt);
        }
        throw new IllegalArgumentException("Cannot compare with unknown type");
    }
}
