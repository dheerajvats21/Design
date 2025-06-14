package com.example.RideShare.dtos;

import com.example.RideShare.entity.Location;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestRegisterDriverDTO {
    public String name;
    public String pan;
    public Location location;
}
