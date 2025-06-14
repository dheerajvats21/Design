package com.example.RideShare.dtos;

import com.example.RideShare.entity.Driver;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterDriverDTO {
    Driver driver;
    String message;
}
