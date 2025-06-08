package ParkingLot.entities;

import java.time.LocalDateTime;

public class Receipt {
    Long cost;
    LocalDateTime entry;
    LocalDateTime exit;
    Vehicle v;

    public Receipt(Long cost, LocalDateTime entry, LocalDateTime exit) {
        this.cost = cost;
        this.entry = entry;
        this.exit = exit;
    }
}
