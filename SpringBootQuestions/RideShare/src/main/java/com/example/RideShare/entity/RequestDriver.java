package com.example.RideShare.entity;

import lombok.Builder;

@Builder
public class RequestDriver {
    public String name;
    public Location initialLocation;
    public String pan;
}
