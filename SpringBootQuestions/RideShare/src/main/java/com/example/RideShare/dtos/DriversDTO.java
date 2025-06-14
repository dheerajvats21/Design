package com.example.RideShare.dtos;

import com.example.RideShare.entity.Driver;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DriversDTO {
    List<Driver> drivers;
}
