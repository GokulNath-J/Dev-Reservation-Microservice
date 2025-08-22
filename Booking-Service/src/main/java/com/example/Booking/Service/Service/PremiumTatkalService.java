package com.example.Booking.Service.Service;

import com.example.Booking.Service.DTO.BookingRequest;
import com.example.Booking.Service.DTO.TrainDTO1;
import com.example.Booking.Service.Repository.PremiumTatkalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PremiumTatkalService {

    @Autowired
    private PremiumTatkalRepo premiumTatkalRepo;


}
