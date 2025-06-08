package TaskScheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;

class ConcreteTaskScheduler implements TaskScheduler {

    DelayQueue<Task> queue = new DelayQueue<Task>();
    Map<Integer, Task> tasks = new ConcurrentHashMap<Integer, Task>();

    @Override
    public void schedule(int taskId, int delaySeconds) {
        if (tasks.containsKey(taskId)) return;
        Task task = new Task(taskId, delaySeconds);
        tasks.put(taskId, task);
        queue.put(task);
    }

    @Override
    public void cancel(int taskID) {
        Task task = tasks.remove(taskID);
        if (task != null) {
            queue.remove(task);
        }

    }

    @Override
    public void run() throws InterruptedException {
        while (true) {
            Task task = queue.take();
            System.out.println("took out id = " + task.getTaskId());
            if (tasks.containsKey(task.getTaskId())) {
                System.out.println("executing task " + task.getTaskId());
            }
        }
    }
}