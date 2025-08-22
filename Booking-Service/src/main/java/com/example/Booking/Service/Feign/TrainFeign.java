package com.example.Booking.Service.Feign;

import com.example.Booking.Service.DTO.TrainDTOWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "Train-Service")
public interface TrainFeign {

    @GetMapping("/rail/sendTrainDTOToBookingServiceManually")
    public TrainDTOWrapper sendTrainDTOToBookingServiceManually();
}
