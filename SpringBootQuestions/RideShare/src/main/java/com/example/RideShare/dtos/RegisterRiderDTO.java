package com.example.RideShare.dtos;

import com.example.RideShare.entity.Rider;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRiderDTO {
    Rider rider;
    String message;
}
