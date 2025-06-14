package com.example.RideShare.entity;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class Ride {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    Driver driver;

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    Rider rider;

    public Instant getStart() {
        return start;
    }

    public void setStart(Instant start) {
        this.start = start;
    }

    public Instant getEnd() {
        return end;
    }

    Instant start;

    public Instant getBook() {
        return book;
    }

    public void setBook(Instant book) {
        this.book = book;
    }

    Instant book;

    public void setRating(int rating) {
        this.rating = rating;
    }

    int rating;

    public void setEnd(Instant end) {
        this.end = end;
    }

    Instant end;
    Location source;
    Location destination;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    Status status;

    public enum Status {
        BOOKED,
        IN_PROGREES,
        ENDED
    }
}
