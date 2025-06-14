package com.example.RideShare.services;

import com.example.RideShare.entity.BoundingBox;
import com.example.RideShare.entity.Driver;
import com.example.RideShare.entity.Location;
import com.example.RideShare.entity.Rider;

import java.util.List;

public interface LocationService {
    void updateDriverLocation(String driverId, Location location);
    void updateRiderLocation(String riderId, Location location);
    Location getCurrentLocationOfRider(String riderId);
    List<String> giveDriverBwLatLong(BoundingBox boundingBox);
}
