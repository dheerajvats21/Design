package ParkingLot.entities;

public class Gate {
    int number;
    GateType type;
    public enum GateType {
        ENTRY,
        EXIT
    }

    public Gate(int num, GateType type) {
        this.number = num;
        this.type = type;
    }
}

