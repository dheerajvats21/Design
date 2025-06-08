package ElevatorSystem.Strategy;

import ElevatorSystem.Elevator;
import ElevatorSystem.entities.Request;

import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimpleMovingStrategy implements MovingStrategy {
    private Integer DEFAULT = 0;
    private Elevator elevator ;
    private boolean isMoving;
    private Request currentRequest;
    private Deque<Request> requests = new LinkedBlockingDeque<>();
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();


    public SimpleMovingStrategy(Elevator e) {
        elevator = e;
    }
    @Override
    public void scheduleRequest(Request req) {
        requests.add(req);
    }

    @Override
    public void move() {
        if (isMoving) {
            return;
        }
        if (requests.isEmpty()) {
            System.out.println("No req for lift " + elevator.getNumber());
            return;
        }
        currentRequest = requests.poll();

        isMoving = true;
        final Integer toFloor = currentRequest.getRequestedFloor();
        System.out.println("Lift = " + elevator.getNumber()
                + " going to " + toFloor);
        executor.schedule(() -> {
            System.out.println("Lift "+ elevator.getNumber() + " reached floor " + toFloor + " .");
            isMoving = false;
        }, 2, TimeUnit.SECONDS);
    }

    @Override
    public Integer expectedTime(Request request) {
        int lastFloor = requests.isEmpty() ?
                currentRequest == null
                        ? DEFAULT
                        : currentRequest.getRequestedFloor()
                :  requests.getLast().getRequestedFloor();
        Map<String, Integer> v = new HashMap<>();
        return Math.abs(lastFloor - request.getRequestedFloor());
    }


}