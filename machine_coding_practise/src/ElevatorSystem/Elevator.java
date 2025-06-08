package ElevatorSystem;

import ElevatorSystem.Strategy.MovingStrategy;
import ElevatorSystem.entities.MoverTypes;
import ElevatorSystem.entities.Request;
import ElevatorSystem.factory.MoverFactory;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Elevator {
    MovingStrategy mover;

    public int getNumber() {
        return number;
    }

    int number;

    public Elevator(MoverTypes type, int n) {
        MoverFactory factory = new MoverFactory();
        mover = factory.createMover(type, this);
        number = n;
    }

    void serveRequest(Request request) {
        System.out.println("Adding in queue for elevator " + number + " for floor " + request.getRequestedFloor() + " ");
        mover.scheduleRequest(request);
    }

    Integer expectedTime(Request request) {
        return mover.expectedTime(request);
    }

    public void triggerMove() {
        ScheduledExecutorService ex = Executors.newSingleThreadScheduledExecutor();
        // schedular scheduleat fixed rate
        ex.schedule(() -> {
            while (true) {
                Thread.sleep(3000);
                mover.move();
            }
        }, 0, TimeUnit.SECONDS);
    }
}