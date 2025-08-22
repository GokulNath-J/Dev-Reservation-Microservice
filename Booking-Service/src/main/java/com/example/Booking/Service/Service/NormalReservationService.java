package com.example.Booking.Service.Service;

import com.example.Booking.Service.DTO.BookingRequest;
import com.example.Booking.Service.DTO.TrainDTO1;
import com.example.Booking.Service.Repository.NormalReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class NormalReservationService {

    @Autowired
    private NormalReservationRepo normalReservationRepo;


}
