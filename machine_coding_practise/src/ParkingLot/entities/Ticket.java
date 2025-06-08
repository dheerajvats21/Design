package ParkingLot.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {


    UUID id;
    Vehicle v;

    public Vehicle getV() {
        return v;
    }

    public Gate getEntryGate() {
        return entryGate;
    }

    public Gate getExitGate() {
        return exitGate;
    }

    public ParkingSlot getSlot() {
        return slot;
    }

    Gate entryGate;
    Gate exitGate;

    public LocalDateTime getEntryTimeStamp() {
        return entryTimeStamp;
    }

    LocalDateTime entryTimeStamp;
    ParkingSlot slot;
    public Ticket(Vehicle v, Gate entryGate, ParkingSlot s) {
        id = UUID.randomUUID();
        this.v = v;
        this.entryGate = entryGate;
        this.entryTimeStamp = LocalDateTime.now();
        this.slot = s;
    }
    public UUID getId() {
        return id;
    }

    @Override
    public String toString () {
        return "id = " + id + " vehicle Num = " + v.getV() + " " + v.getNumber();
    }
}
