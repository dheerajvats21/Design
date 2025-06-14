package com.example.RideShare;

import com.example.RideShare.config.TestSecurityConfig;
import com.example.RideShare.dtos.*;
import com.example.RideShare.entity.Driver;
import com.example.RideShare.entity.Location;
import com.example.RideShare.entity.Payment;
import com.example.RideShare.entity.Ride;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestSecurityConfig.class)
class RideShareTests {
    // register rider
    // register driver
    // updateDriverLocation
    // updateRiderLocation Location
    // findDriversNearby rider - should return driver
    // BookARide
    // startRide
    // EndARide

    @LocalServerPort
    private int port;

    RestTemplate restTemplate = new RestTemplate();

    @Test
    void singleRideTest() {


        String domain = "http://localhost:" + port;
        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // ===== register driver ======
        RequestRegisterDriverDTO requestRegisterDriverDTO = RequestRegisterDriverDTO.builder()
                .pan("ABCDEF")
                .name("ASDFGH")
                .location(new Location(12.0, 13.0))
                .build();
        HttpEntity<RequestRegisterDriverDTO> reqEntity = new HttpEntity<>(requestRegisterDriverDTO, headers);

        ResponseEntity<RegisterDriverDTO> responseRegisterDriver = restTemplate.exchange(
               domain + "/ride-share/register-driver",
                HttpMethod.POST, reqEntity, RegisterDriverDTO.class);
        String driverId = responseRegisterDriver.getBody().getDriver().getId();
        System.out.println("registered driver id=" + driverId);



        // ===== register rider ======
        HttpEntity<RequestRegisterRiderDTO> reqEntityRider = new HttpEntity<>(RequestRegisterRiderDTO.builder()
                .name("rider")
                .location(new Location(10, 10))
                .build(), headers);

        ResponseEntity<RegisterRiderDTO> responseRegisterRider = restTemplate.exchange(
                domain + "/ride-share/register-rider",
                HttpMethod.POST, reqEntityRider, RegisterRiderDTO.class);
        String riderId = responseRegisterRider.getBody().getRider().getId();
        System.out.println("registered rider id=" + riderId);
        System.out.println("registered rider id=" + riderId);


        // ==== update driver location====
        HttpEntity<LocationRequestDTO> reqEntityLocationRequestDriver = new HttpEntity<>(LocationRequestDTO.builder()
                .latitude(11.0)
                .longitude(13.0)
                .build(), headers);
        restTemplate.exchange(domain + "/ride-share/update-driver-location/" + driverId,
                HttpMethod.PUT,
                reqEntityLocationRequestDriver, String.class);

        System.out.println("updated location for driver id=" + driverId);

        // ======== update Rider location =========
        HttpEntity<LocationRequestDTO> reqEntityLocationRequestRider = new HttpEntity<>(LocationRequestDTO.builder()
                .latitude(10.0)
                .longitude(10.0)
                .build(), headers);
        ResponseEntity<String> responseSetLocationDriver = restTemplate.exchange(domain + "/ride-share/update-rider-location/" + riderId,
                HttpMethod.PUT,
                reqEntityLocationRequestDriver,
                String.class);
        System.out.println("updated location for rider id=" + riderId);
        System.out.println("updated location for rider id=" + riderId);

        // ==== findRiders ====
        String url = domain + "/ride-share/find-drivers?radius=5.0&riderId="+ riderId;
        ResponseEntity<DriversDTO> driverDtoResponse = restTemplate.exchange(url, HttpMethod.GET, null, DriversDTO.class);

        List<Driver> drivers = driverDtoResponse.getBody().getDrivers();
        System.out.println("driver name = " + drivers);

        // findDriver
        HttpEntity<BookRideRequestDTO> httpEntityBookRide = new HttpEntity<>(
                BookRideRequestDTO.builder()
                        .source(new Location(10.0, 10.0))
                        .destination(new Location(15.0, 15.0))
                        .driverId(driverId)
                        .riderId(riderId)
                        .build()
                , headers);

        url = domain + "/ride-share/book-ride";
        ResponseEntity<Ride> rideResp = restTemplate.exchange(url, HttpMethod.POST, httpEntityBookRide, Ride.class);
        String rideId = rideResp.getBody().getId();
        System.out.println("booked ride id " + rideId);

        // ===== start ride ======
        url = domain + "/ride-share/start-ride/" + rideId;
        ResponseEntity<String> respStart = restTemplate.exchange(url, HttpMethod.PUT, null, String.class);
        System.out.println("started ride");

        // ====== end ride =======
        url = domain + "/ride-share/end-ride/" + rideId;
        ResponseEntity<Payment> respEnd = restTemplate.exchange(url, HttpMethod.PUT, null, Payment.class);
        System.out.println("ended ride amount = " + respEnd.getBody().getAmount());

        // ===== rate- ride =======
        url = domain + "/ride-share/rate-ride/" + rideId +"?rating=4";
        restTemplate.exchange(url, HttpMethod.PUT, null, String.class);
        System.out.println("rated ride");
    }
}
