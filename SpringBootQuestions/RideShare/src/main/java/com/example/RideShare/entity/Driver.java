package com.example.RideShare.entity;

import lombok.Builder;

@Builder
public class Driver {
    String id;
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    Location location;
    String pan;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Driver)) return false;
        return ((Driver) o).pan.equals(this.pan);
    }
}
