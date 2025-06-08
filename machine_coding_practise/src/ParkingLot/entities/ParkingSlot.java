package ParkingLot.entities;

public class ParkingSlot {
    Integer number;

    public VehicleType getType() {
        return type;
    }

    VehicleType type;
    public Integer getNumber() {
        return this.number;
    }

    public ParkingSlot(Integer number, VehicleType type) {
        this.number = number;
        this.type = type;
    }

    @Override
    public String toString() {
        return " " + number;
    }

}
