package ParkingLot.entities;

import java.util.List;

public class Floor {
    int number;

    public int getNumber() {
        return number;
    }

    public List<ParkingSlot> getSlots() {
        return slots;
    }

    List<ParkingSlot> slots;

    public Floor(int number, List<ParkingSlot> slot) {
        this.number = number;
        this.slots = slot;
    }
}
