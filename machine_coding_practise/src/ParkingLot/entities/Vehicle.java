package ParkingLot.entities;

public class Vehicle {
    public int getNumber() {
        return number;
    }

    public VehicleType getV() {
        return v;
    }

    int number;
    VehicleType v;

    public Vehicle(int number, VehicleType type) {
        this.number = number;
        this.v = type;
    }
}
