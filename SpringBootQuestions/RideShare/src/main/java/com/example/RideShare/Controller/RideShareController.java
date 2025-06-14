package com.example.RideShare.Controller;

import com.example.RideShare.dtos.*;
import com.example.RideShare.entity.*;
import com.example.RideShare.services.RideShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ride-share")
public class RideShareController {

    @Autowired
    RideShareService rideShareService;

    // post
    // /rideshare/register-driver
    // BODY - RequestDTODriver - coz we dont need to change controller signature of
    // new parameters emerge.
    // Response - msg - "created success", DriverDTO
    @PostMapping("/register-driver")
    public ResponseEntity<RegisterDriverDTO> registerDriver(@RequestBody RequestRegisterDriverDTO req) {
        Driver d = rideShareService.registerDriver(RequestDriver.builder()
                        .pan(req.pan)
                        .initialLocation(req.location)
                        .name(req.name)
                .build());
        return ResponseEntity.ok(RegisterDriverDTO.builder()
                        .driver(d)
                        .message("success")
                .build());
    }


    // post
    // /rideshare/register-rider
    // BODY - RequestDTORider
    // Response - msg - "created success", RiderDTO, 200
    @PostMapping("/register-rider")
    public ResponseEntity<RegisterRiderDTO> registerDriver(@RequestBody RequestRegisterRiderDTO req) {
        Rider r = rideShareService.registerRider(RequestRider.builder()
                .initialLocation(req.location)
                .name(req.name)
                .build());
        return ResponseEntity.ok(RegisterRiderDTO.builder()
                .rider(r)
                .message("success")
                .build());
    }

    // put
    // /rideshare/update-driver-location/{driver-id}
    // BODY - LocationRequestDTO
    // Response - 200, msg - updated
    @PutMapping("/update-driver-location/{driverId}")
    public ResponseEntity<String> updateLocationDriver(@PathVariable String driverId, @RequestBody LocationRequestDTO req) {
        rideShareService.updateDriverLocation(driverId, new Location(req.latitude, req.longitude));
        return ResponseEntity.ok("success");
    }

    // put
    // rideshare/update-rider-location/{rider-id}
    // BODY - LocationRequestDTO
    // Response - 200, msg - updated
    @PutMapping("/update-rider-location/{riderId}")
    public ResponseEntity<String> updateLocationRider(@PathVariable String riderId, @RequestBody LocationRequestDTO req) {
        rideShareService.updateRiderLocation(riderId, new Location(req.latitude, req.longitude));
        return ResponseEntity.ok("success");
    }

    // get
    // rideshare/find-drivers?radius={radius},riderID={riderID}
    // Response - List<Driver> , 200, msg = 'done'
    @GetMapping("/find-drivers")
    public ResponseEntity<DriversDTO> findDrivers(@RequestParam double radius, @RequestParam String riderId) {
        return ResponseEntity.ok(DriversDTO.builder()
                        .drivers(rideShareService.findNearbyDrivers(riderId, radius))
                .build());
    }

    // POST
    // rideshare/book-ride
    // BODY - sourceLocation, destLocation, rider-id, driver-id
    // Response - Ride ibject msg = 'done' , 200
    @PostMapping("/book-ride")
    public ResponseEntity<Ride> bookRide(@RequestBody BookRideRequestDTO req) {
        Ride r = rideShareService.bookRide(req.riderId, req.driverId, req.source, req.destination);
        return ResponseEntity.ok(r);
    }

    // patch
    // rideshare/start-ride/{rideID}
    // Response - message, 200
    @PutMapping("/start-ride/{rideID}")
    public ResponseEntity<String> start(@PathVariable String rideID) {
        rideShareService.startRide(rideID);
        return ResponseEntity.ok("success");
    }

    // patch
    // ride-share/end-ride/{ride-id}
    // Response - amountToBePaid, message, 200
    @PutMapping("/end-ride/{rideID}")
    public ResponseEntity<Payment> end(@PathVariable String rideID) {
        Payment pay = rideShareService.endRide(rideID);
        return ResponseEntity.ok(pay);
    }

    // patch
    // ride-share/rate-ride/{ride-id}
    // Response - msg = "rating received, thank you !" , 200
    @PutMapping("/rate-ride/{rideId}")
    public ResponseEntity<String> rate(@PathVariable String rideId, @RequestParam int rating) {
        rideShareService.rateRide(rideId, rating);
        return ResponseEntity.ok("success");
    }

}
