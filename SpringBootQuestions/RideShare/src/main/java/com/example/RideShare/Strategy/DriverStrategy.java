package com.example.RideShare.Strategy;

import com.example.RideShare.entity.Driver;

import java.util.List;


public interface DriverStrategy {
    List<Driver> findDrivers(String riderId, double radiusInKm);
}
