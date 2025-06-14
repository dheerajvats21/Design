package com.example.RideShare.entity;

import lombok.Builder;

@Builder
public class RequestRider {
    public String name;
    public Location initialLocation;
}
