package com.example.User_Service.OpenFeign;

import com.example.User_Service.DTO.BookingRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Booking-Service")
public interface BookingFeign {


    @PostMapping("/booking/book")
    public String booking(@RequestBody BookingRequest request);
}
