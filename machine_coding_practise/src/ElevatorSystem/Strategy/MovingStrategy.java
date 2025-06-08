package ElevatorSystem.Strategy;

import ElevatorSystem.Elevator;
import ElevatorSystem.entities.Request;

import java.util.LinkedList;
import java.util.Queue;

public interface MovingStrategy {
    void scheduleRequest(Request req);
    void move();
    Integer expectedTime(Request request);
}


