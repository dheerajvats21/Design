package ParkingLot.strategy;

import ParkingLot.entities.Floor;
import ParkingLot.entities.ParkingSlot;
import ParkingLot.entities.VehicleType;

import java.util.List;
import java.util.Set;

public interface ParkingSlotStrategy {
    ParkingSlot getSlot(List<Floor> floors, Set<Integer> takenSlots, VehicleType type);
    Set<ParkingSlot> displayAvailableSlots(List<Floor> floors, Set<VehicleType> types, Set<Integer> takenSlots);
}
