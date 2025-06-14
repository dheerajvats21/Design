package com.example.RideShare.Strategy;

import com.example.RideShare.Repos.DataBase;
import com.example.RideShare.entity.*;
import com.example.RideShare.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DriverStrategyImpl implements DriverStrategy{
    @Autowired
    LocationService locationService;
    @Autowired
    DataBase db;
    @Override
    public List<Driver> findDrivers(String riderId, double radiusInKm) {
        Location location = locationService.getCurrentLocationOfRider(riderId);
        BoundingBox boundingBox = new BoundingBox(
                 location.latitude - radiusInKm,
                location.latitude + radiusInKm,
                location.longitude - radiusInKm,
                location.longitude + radiusInKm
        );
        List<String> driverIds = locationService.giveDriverBwLatLong(boundingBox);
        return driverIds.stream().map(id -> db.getDriverById(id)).toList();
    }
}
