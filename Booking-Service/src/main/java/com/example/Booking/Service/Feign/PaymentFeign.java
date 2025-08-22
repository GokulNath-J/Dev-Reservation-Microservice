package com.example.Booking.Service.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "Payment-Service")
public interface PaymentFeign {

    @PostMapping("/payment/paymentRequest")
    public String paymentRequest(@RequestParam String userName,@RequestParam double totalTicketAmount);
}
