package com.example.ubersocketserver.controller;


import com.example.ubersocketserver.Dtos.RideRequestDto;
import com.example.ubersocketserver.Dtos.RideResponseDto;
import com.example.ubersocketserver.Dtos.UpdateBookingRequestDto;
import com.example.ubersocketserver.Dtos.UpdateBookingResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/api/socket")
public class DriverController {

    private SimpMessagingTemplate simpMessagingTemplate;
    private final RestTemplate restTemplate;

    public DriverController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.restTemplate = new RestTemplate();
    }


    @PostMapping("/newride")
    @CrossOrigin(originPatterns = "*")
    public void raiseRideRequets(@RequestBody RideRequestDto rideRequestDto) {
        sendDriversNewsRideRequest(rideRequestDto);
    }

    public void sendDriversNewsRideRequest(RideRequestDto rideRequestDto) {
      System.out.println("sendDriversNewsRideRequest");

      //TODO ideally request should only go to nearby driver but for simplicity now it will go to all driver
      simpMessagingTemplate.convertAndSend("/topic/rideRequest",rideRequestDto);
    }

    @MessageMapping("/rideresponse/{userId}")
   public void rideResponseHandler(@DestinationVariable String userId, RideResponseDto rideResponseDto) {
        System.out.println("rideResponseHandler"+rideResponseDto.getResponse()+userId);

        UpdateBookingRequestDto updateBookingDto= UpdateBookingRequestDto.builder()
                .bookingStatus("SCHEDULE")
                .driverId(Optional.of(Long.parseLong(userId)))
                .build();

        ResponseEntity<UpdateBookingResponseDto> result= this.restTemplate.postForEntity("http://localhost:5555//app/v1/bookings"+rideResponseDto.bookingId,updateBookingDto,UpdateBookingResponseDto.class);
        System.out.println(result.getBody());
    }

}
