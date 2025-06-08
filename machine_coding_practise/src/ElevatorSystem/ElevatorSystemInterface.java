package ElevatorSystem;

import ElevatorSystem.Strategy.MovingStrategy;
import ElevatorSystem.entities.Request;

import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class ElevatorSystemInterface {
    List<Elevator> elevators;
    Map<Integer, Elevator> numberToElevator = new HashMap<>();

    public ElevatorSystemInterface (List<Elevator> list) {
        elevators = list;
        list.forEach(elevator -> {
            numberToElevator.put(elevator.getNumber(), elevator);
            elevator.triggerMove();
        });
    }

    public void externalRequest (Request req) {
        Elevator minTimeElevator = null;
        int minTime =  Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            int time = elevator.expectedTime(req);
            if (time < minTime) {
                minTime = time;
                minTimeElevator = elevator;
            }
        }

        minTimeElevator.serveRequest(req);
    }

    public void internalRequest(Request req) {
        numberToElevator.get(req.getElevatorNumber()).serveRequest(req);
    }
}


