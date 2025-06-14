package com.example.RideShare.dtos;

import com.example.RideShare.entity.Location;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookRideRequestDTO {
    public Location source, destination;
    public String riderId, driverId;
}
