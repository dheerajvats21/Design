package ElevatorSystem.factory;

import ElevatorSystem.Elevator;
import ElevatorSystem.Strategy.MovingStrategy;
import ElevatorSystem.entities.MoverTypes;
import ElevatorSystem.Strategy.SimpleMovingStrategy;


public class MoverFactory {

    public MovingStrategy createMover(MoverTypes type, Elevator e) {
        if (type == MoverTypes.SIMPLE) return new SimpleMovingStrategy(e);
        return new SimpleMovingStrategy(e);
    }
}
