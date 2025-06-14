package com.example.RideShare.entity;

public class BoundingBox {
    public double minLat, maxLat, minLon, maxLon;

    public BoundingBox(double minLat, double maxLat, double minLon, double maxLon) {
        this.minLat = minLat;
        this.maxLat = maxLat;
        this.minLon = minLon;
        this.maxLon = maxLon;
    }
}