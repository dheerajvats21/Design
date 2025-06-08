package ParkingLot;

import ParkingLot.entities.*;
import ParkingLot.strategy.ParkingSlotStrategy;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public interface ParkingLot {
    Ticket park(Vehicle v, Gate entryGate);
    Receipt unpark(UUID ticketId, Gate exitGate);
    Set<ParkingSlot> displayAvailableSlots(List<VehicleType> type);

}

class ParkingLotImpl implements ParkingLot {

    List<Floor> floors;
    ParkingSlotStrategy slotStrategy;
    Set<Integer> takenSlots;
    Map<UUID, Ticket> tickets;

    ParkingLotImpl(List<Floor> floor, ParkingSlotStrategy s) {
        this.slotStrategy = s;
        this.floors = floor;
        takenSlots = new HashSet<>();
        tickets = new HashMap<>();
    }

    public Ticket park(Vehicle v, Gate entryGate) {
        // prepares a ticket, assign vehicle  and an available slot to ticket
        // and prints "YOU CAN ENTER and park at <slot number>"
        ParkingSlot slot = this.slotStrategy.getSlot(floors, takenSlots, v.getV());
        if (slot == null) return null;
        takenSlots.add(slot.getNumber());
        Ticket t = new Ticket(v, entryGate, slot);
        tickets.put(t.getId(), t);
        return t;
    }

    public Receipt unpark(UUID ticketId, Gate exitGate) {
        // from ticket sees the vehicle number and slot number
        // make slot available now
        Ticket singleTicket = tickets.get(ticketId);
        Vehicle v = singleTicket.getV();
        ParkingSlot slot = singleTicket.getSlot();
        takenSlots.remove(slot.getNumber());
        tickets.remove(ticketId);

        LocalDateTime exitTime = LocalDateTime.now();
        Long cost = Duration.between(singleTicket.getEntryTimeStamp(), exitTime).toMillis() * 10;
        return new Receipt(cost, singleTicket.getEntryTimeStamp(), exitTime);
    }

    public Set<ParkingSlot> displayAvailableSlots(List<VehicleType> type) {
        return slotStrategy.displayAvailableSlots(floors, new HashSet<>(type), takenSlots);
    }
}
