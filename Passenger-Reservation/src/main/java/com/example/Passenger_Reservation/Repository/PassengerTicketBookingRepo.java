package com.example.Passenger_Reservation.Repository;

import com.example.Passenger_Reservation.Entity.PassengerTicketBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerTicketBookingRepo extends JpaRepository<PassengerTicketBooking,Integer> {
}
