package com.example.Passenger_Reservation.PassengerWrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainWrapper {

    private Integer train_number;
    private String train_name;
    private String starting_point;
    private String destination;
    private List<TrainStoppingStationWrapper> trainStoppingStationWrapperList;
    private Integer no_stopping_stations;
}
