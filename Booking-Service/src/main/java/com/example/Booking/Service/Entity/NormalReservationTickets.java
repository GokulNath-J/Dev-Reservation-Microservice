package com.example.Booking.Service.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NormalReservationTickets {
    @Id
    @SequenceGenerator(name = "nrtickets", sequenceName = "seqnrtickets", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nrtickets")
    private Integer sNo;
    private LocalDateTime booking_date_and_time;
    private Integer train_number;
    private String coach_name;
    private Integer total_no_of_seats;
    private Integer no_of_seats_available;
    private Integer no_of_seats_booked;
    private Double each_seat_price;
}
