package com.example.Train_Service.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketsPerStation {

    @Id
    @SequenceGenerator(name = "ticketsperstation", sequenceName = "seqticketsperstation", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticketsperstation")
    private Integer sNo;
    private Integer train_number;
    private String station_name;
    private String booking_type;
    private String coach_name;
    private Integer total_no_of_seats;
    private Double each_seat_price;

    public TicketsPerStation(Integer train_number, String station_name, String booking_type, String coach_name, Integer total_no_of_seats, Double each_seat_price) {
        this.train_number = train_number;
        this.station_name = station_name;
        this.booking_type = booking_type;
        this.coach_name = coach_name;
        this.total_no_of_seats = total_no_of_seats;
        this.each_seat_price = each_seat_price;
    }

    public TicketsPerStation(String station_name, String booking_type, String coach_name, Integer total_no_of_seats, Double each_seat_price) {
        this.station_name = station_name;
        this.booking_type = booking_type;
        this.coach_name = coach_name;
        this.total_no_of_seats = total_no_of_seats;
        this.each_seat_price = each_seat_price;
    }
}
