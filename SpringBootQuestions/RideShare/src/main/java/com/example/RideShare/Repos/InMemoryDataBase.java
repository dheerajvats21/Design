package com.example.RideShare.Repos;

import com.example.RideShare.entity.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InMemoryDataBase implements DataBase {
    Map<String, Driver> driversById = new HashMap<>();
    Set<Driver> drivers =  new HashSet<>();
    Map<String, Rider> ridersById = new HashMap<>();
    Set<Rider> riders = new HashSet<>();
    Map<String, Ride> ridesById = new HashMap<>();

    Map<String, Ride> currentRideForRider = new HashMap<>();
    Map<String, Ride> currentRideForDriver = new HashMap<>();

    // LocationService Data
    Map<String, Location> riderLocation = new HashMap<>();
    Map<String, Location> driverLocation = new HashMap<>();

    @Override
    public Driver addDriver(Driver driver) {
        if (!drivers.contains(driver)) {
            driver.setId(UUID.randomUUID().toString());
            drivers.add(driver);
            driversById.put(driver.getId(), driver);
        }
        return driver;
    }

    @Override
    public Rider addRider(Rider rider) {
        if (!riders.contains(rider)) {
            rider.setId(UUID.randomUUID().toString());
            riders.add(rider);
            ridersById.put(rider.getId(), rider);
        }
        return rider;
    }

    @Override
    public Rider getRiderById(String id) {
        return ridersById.getOrDefault(id, null);
    }

    @Override
    public Driver getDriverById(String id) {
        return driversById.getOrDefault(id, null);
    }

    @Override
    public Ride addRide(Ride ride) {
        if (currentRideForDriver.containsKey(ride.getDriver().getId()) ||
        currentRideForRider.containsKey(ride.getRider().getId())) return null;

        ride.setId(UUID.randomUUID().toString());

        currentRideForDriver.put(ride.getDriver().getId(), ride);
        currentRideForRider.put(ride.getRider().getId(), ride);

        ridesById.put(ride.getId(), ride);
        return ride;
    }

    @Override
    public Ride getRideById(String id) {
        return ridesById.getOrDefault(id, null);
    }

    @Override
    public void saveRide(Ride ride) {
        ridesById.put(ride.getId(), ride);
    }

    @Override
    public void setLocationForDriver(String driverId, Location location) {
        driverLocation.put(driverId, location);
    }

    @Override
    public Location getLocationForDriver(String driverId) {
        return driverLocation.getOrDefault(driverId, null);
    }

    @Override
    public void setLocationForRider(String riderId, Location location) {
        riderLocation.put(riderId, location);
    }

    @Override
    public Location getLocationForRider(String riderId) {
        return riderLocation.getOrDefault(riderId, null);
    }

    @Override
    public List<String> getDriverInRange(BoundingBox range) {
        return driverLocation.entrySet().stream().filter(e -> {
            Location location = e.getValue();
            double latitude = location.latitude;
            double longitude = location.longitude;
            return latitude >= range.minLat
                    && latitude <= range.maxLat
                    && longitude >= range.minLon
                    && longitude <= range.maxLon;
        }).map(Map.Entry::getKey).toList();
    }
}
