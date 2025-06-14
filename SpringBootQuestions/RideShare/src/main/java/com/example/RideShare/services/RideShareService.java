package com.example.RideShare.services;

import com.example.RideShare.entity.*;

import java.util.List;

public interface RideShareService {
    Driver registerDriver(RequestDriver req);

    Rider registerRider(RequestRider req);

    void updateDriverLocation(String driverId, Location location);

    void updateRiderLocation(String riderId, Location location);

    List<Driver> findNearbyDrivers(String riderId, double radiusInKm);

    Ride bookRide(String riderId, String driverId, Location source, Location destination);
    void startRide(String rideId);
    Payment endRide(String rideId);

    void rateRide(String rideId, int rating);
}
