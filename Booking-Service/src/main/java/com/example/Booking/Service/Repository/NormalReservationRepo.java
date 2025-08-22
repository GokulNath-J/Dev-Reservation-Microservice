package com.example.Booking.Service.Repository;

import com.example.Booking.Service.Entity.NormalReservationTickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NormalReservationRepo extends JpaRepository<NormalReservationTickets,Integer> {
}
