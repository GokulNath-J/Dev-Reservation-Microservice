package com.example.Train_Service.Entity.Booking;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tatkal {
    private static final LocalTime tatkal_opens_at_for_nonsleepers = LocalTime.of(10, 00, 00);
    private static final LocalTime tatkal_opens_at_for_sleepers = LocalTime.of(11, 00, 00);
    @Id
    @SequenceGenerator(name = "tatkalseq", sequenceName = "seqtatkal", allocationSize = 1, initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tatkalseq")
    private Integer id;
    private String coach;
    private String passenger_name;
    private Integer seat_number;
    private String from_station;
    private String destination_station;
    private LocalDateTime from_station_datetime;
    private LocalDateTime destination_station_datetime;
    private Integer amount;


}
