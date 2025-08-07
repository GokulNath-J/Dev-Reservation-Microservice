package com.example.Passenger_Reservation.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerTicketBooking {

    @Id
    @SequenceGenerator(name = "passenger",sequenceName = "seqpassenger", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "passenger")
    private Integer id;
    private Integer train_number;
    private String train_name;
    private String no_of_tickets;
    private String starting_point;
    private String destination;
    private String ticket_conformation;
    private LocalDateTime reserved_on_datetime;

}
