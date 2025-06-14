package com.example.RideShare.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationRequestDTO {
    public double latitude, longitude;
}
