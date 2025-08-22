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
public class PremiumTatkalTickets {

    @Id
    @SequenceGenerator(name = "prtickets", sequenceName = "seqprtickets", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prtickets")
    private Integer sNo;
    private Integer train_number;
    private String coach_name;
    private String station_name;
    private String booking_type;
    private Integer total_no_of_seats;
    private Integer no_of_seats_available;
    private Integer no_of_seats_booked;
    private Double each_seat_price;

    public PremiumTatkalTickets(Integer train_number, String coach_name, String station_name, String booking_type, Integer total_no_of_seats, Integer no_of_seats_available, Integer no_of_seats_booked, Double each_seat_price) {
        this.train_number = train_number;
        this.coach_name = coach_name;
        this.station_name = station_name;
        this.booking_type = booking_type;
        this.total_no_of_seats = total_no_of_seats;
        this.no_of_seats_available = no_of_seats_available;
        this.no_of_seats_booked = no_of_seats_booked;
        this.each_seat_price = each_seat_price;
    }
}
