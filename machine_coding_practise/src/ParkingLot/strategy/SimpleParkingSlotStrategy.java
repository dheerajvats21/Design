package ParkingLot.strategy;

import ParkingLot.entities.Floor;
import ParkingLot.entities.ParkingSlot;
import ParkingLot.entities.VehicleType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SimpleParkingSlotStrategy implements ParkingSlotStrategy {
    public ParkingSlot getSlot(List<Floor> floors, Set<Integer> takenSlots, VehicleType type){
        Set<ParkingSlot> availableSlots = displayAvailableSlots(floors, new HashSet<>(Arrays.asList(type)), takenSlots);
        if (availableSlots.isEmpty()) return null;
        return availableSlots.iterator().next();
    }
    public Set<ParkingSlot> displayAvailableSlots(List<Floor> floors, Set<VehicleType> types,
                                                  Set<Integer> takenSlots){
        Set<ParkingSlot> slots = floors.stream().flatMap(floor -> floor.getSlots().stream())
                .filter(slot -> types.contains(slot.getType()) && !takenSlots.contains(slot.getNumber()))
                .collect(Collectors.toSet());

        return slots;
    }
}