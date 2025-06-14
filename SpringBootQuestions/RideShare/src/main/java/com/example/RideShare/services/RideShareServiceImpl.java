package com.example.RideShare.services;

import com.example.RideShare.Repos.DataBase;
import com.example.RideShare.Strategy.DriverStrategy;
import com.example.RideShare.Strategy.RatingStrategy;
import com.example.RideShare.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Component
public class RideShareServiceImpl implements RideShareService{

    @Autowired
    DataBase db;
    @Autowired
    LocationService locationService;
    @Autowired
    RatingStrategy ratingStrategy;
    @Autowired
    DriverStrategy strategy;

    @Override
    public Driver registerDriver(RequestDriver req) {
        return db.addDriver(Driver.builder()
                        .name(req.name)
                        .location(req.initialLocation)
                        .pan(req.pan)
                .build());
    }

    @Override
    public Rider registerRider(RequestRider req) {
        return db.addRider(Rider.builder()
                        .location(req.initialLocation)
                        .name(req.name)
                .build());
    }

    @Override
    public void updateDriverLocation(String driverId, Location location) {
        locationService.updateDriverLocation(driverId, location);
    }

    @Override
    public void updateRiderLocation(String riderId, Location location) {
        locationService.updateRiderLocation(riderId, location);
    }

    @Override
    public List<Driver> findNearbyDrivers(String riderId, double radiusInKm) {
        return strategy.findDrivers(riderId, radiusInKm);
    }

    @Override
    public Ride bookRide(String riderId, String driverId, Location source,
                         Location destination) {
        Rider rider = db.getRiderById(riderId);
        Driver driver = db.getDriverById(driverId);
        Ride ride = Ride.builder()
                .rider(rider)
                .driver(driver)
                .destination(destination)
                .source(source)
                .book(Instant.now())
                .status(Ride.Status.BOOKED)
                .build();
        return db.addRide(ride);
    }

    @Override
    public void startRide(String rideId) {
        Ride ride = db.getRideById(rideId);
        ride.setStart(Instant.now());
        ride.setStatus(Ride.Status.ENDED);
        db.saveRide(ride);
    }


    @Override
    public Payment endRide(String rideId) {
        Ride ride = db.getRideById(rideId);
        ride.setEnd(Instant.now());
        ride.setStatus(Ride.Status.ENDED);
        db.saveRide(ride);
        // can return payment from here too
        return Payment.builder()
                .amount(Duration.between(ride.getStart(), ride.getEnd()).toMillis() * 1000)
                .build();
    }

    @Override
    public void rateRide(String rideId, int rating) {
        ratingStrategy.rate(rideId, rating);
    }
}
