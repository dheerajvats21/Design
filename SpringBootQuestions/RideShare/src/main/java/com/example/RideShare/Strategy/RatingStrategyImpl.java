package com.example.RideShare.Strategy;

import com.example.RideShare.Repos.DataBase;
import com.example.RideShare.entity.Ride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RatingStrategyImpl implements RatingStrategy{
    @Autowired
    DataBase db;

    @Override
    public void rate(String rideId, int rating) {
        Ride ride = db.getRideById(rideId);
        ride.setRating(rating);
        db.saveRide(ride);
        return;
    }
}
