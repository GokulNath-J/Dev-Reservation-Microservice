package com.example.Passenger_Reservation.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketBooking {

  /*  @Id
    @SequenceGenerator(name = "TicketBooking",sequenceName = "seqticketbooking", allocationSize = 1,initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "TicketBooking")*/
    private Integer passenger_id;
  //  @Column(unique = true)
    private Integer train_number;
    private String train_name;
    private Integer no_ac_tickets;
    private Integer no_sleeper_tickets;
    private Integer no_reserved_tickets;
    private LocalDateTime booking_date_time;
}
