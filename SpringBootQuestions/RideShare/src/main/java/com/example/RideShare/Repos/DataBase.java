package com.example.RideShare.Repos;

import com.example.RideShare.entity.*;
import org.springframework.context.annotation.Bean;

import java.util.List;

public interface DataBase {
    Driver addDriver(Driver driver);
    Rider addRider(Rider rider);
    Rider getRiderById(String id);
    Driver getDriverById(String id);

    Ride addRide(Ride ride);
    Ride getRideById(String id);
    void saveRide(Ride ride);

    void setLocationForDriver(String driverId, Location location);
    Location getLocationForDriver(String driverId);
    void setLocationForRider(String riderId, Location location);
    Location getLocationForRider(String riderId);
    List<String> getDriverInRange(BoundingBox range);

}
