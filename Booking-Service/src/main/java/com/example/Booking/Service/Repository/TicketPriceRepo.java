package com.example.Booking.Service.Repository;

import com.example.Booking.Service.DTO.TicketPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketPriceRepo extends JpaRepository<TicketPrice,Integer> {
    TicketPrice findByBookingTypeAndCoachName(String bookingMethod, String coachName);
}
