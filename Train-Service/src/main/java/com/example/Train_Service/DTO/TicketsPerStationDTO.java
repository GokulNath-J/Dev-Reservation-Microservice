package com.example.Train_Service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TicketsPerStationDTO {

    private String station_name;
    private String coach_name;
    private Integer total_no_of_seats;
    private Integer no_of_seats_available;
    private Double each_seat_price;

}
