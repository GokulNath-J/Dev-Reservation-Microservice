package com.example.Passenger_Reservation.PassengerWrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainStoppingStationWrapper {

    private String station_name;
    private Integer platform_no;
}
