package com.example.Booking.Service.Repository;

import com.example.Booking.Service.Entity.TatkalTickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TatkalRepo extends JpaRepository<TatkalTickets,Integer> {


    List<TatkalTickets> findAllByTrainNumber(Integer trainNumber);
}
