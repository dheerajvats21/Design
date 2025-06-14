package com.example.RideShare.services;

import com.example.RideShare.Repos.DataBase;
import com.example.RideShare.entity.BoundingBox;
import com.example.RideShare.entity.Driver;
import com.example.RideShare.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService{
    @Autowired
    DataBase db;
    @Override
    public void updateDriverLocation(String driverId, Location location) {
        db.setLocationForDriver(driverId, location);
    }

    @Override
    public void updateRiderLocation(String riderId, Location location) {
        db.setLocationForRider(riderId, location);
    }

    @Override
    public Location getCurrentLocationOfRider(String riderId) {
        return db.getLocationForRider(riderId);
    }

    @Override
    public List<String> giveDriverBwLatLong(BoundingBox boundingBox) {
        return db.getDriverInRange(boundingBox);
    }
}
