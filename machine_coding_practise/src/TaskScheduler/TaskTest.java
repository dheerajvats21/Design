package TaskScheduler;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskTest {

    public static void main(String[] args) throws Exception {

        TaskScheduler sc = new ConcreteTaskScheduler();

        ScheduledExecutorService ex = Executors.newSingleThreadScheduledExecutor();

        ex.schedule(() -> {
            sc.schedule(1, 5);
            sc.schedule(2, 10);
            sc.schedule(3, 12);
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println("handle");
            }
            sc.cancel(2);
            sc.schedule(4, 8);
            System.out.println("added");
        }, 0, TimeUnit.SECONDS);

        sc.run();


    }
}

