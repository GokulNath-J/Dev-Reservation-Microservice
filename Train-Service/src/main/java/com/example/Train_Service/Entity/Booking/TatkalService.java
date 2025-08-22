package com.example.Train_Service.Entity.Booking;


import com.example.Train_Service.Entity.TrainDetails;
import com.example.Train_Service.Entity.TrainStoppingStation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TatkalService implements Booking {

    private static final LocalTime tatkalOpensTime = LocalTime.of(10, 00, 00);

    private static final Logger logger = LoggerFactory.getLogger(TatkalService.class);

    @Autowired
    private ValidTicket validTicket;

    @Override
    public void book(TrainDetails trainDetails, String coach, Integer noOfTickets, Integer amount,
                     String bookingType, String date, String fromstation,
                     String destination) {
        boolean check1 = checkTrainStartingStationTime(trainDetails.getTrainStoppingStations().getFirst(), date);
     //   boolean check2 = validTicket.checkTicketAvailability();

      //  int available_tickets_in_the_coach = checkAvailableTicket(trainDetails,noOfTickets);



        if(check1){

        }

    }

//    private int checkAvailableTicket(TrainDetails trainDetails, Integer noOfTickets) {
//    }

    private boolean checkTrainStartingStationTime(TrainStoppingStation first, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime starting_station_dateandtime = first.getDeparture_date_time();
        LocalDate starting_station_date = starting_station_dateandtime.toLocalDate();
        LocalTime starting_station_Time = starting_station_dateandtime.toLocalTime();
        if(starting_station_date.equals(LocalDate.now())){
            logger.info(" if(starting_station_date.equals(LocalDate.now()):{}:true",starting_station_date);
            if (starting_station_Time.isAfter(tatkalOpensTime)){
                logger.info("starting_station_Time.isAfter(tatkalOpensTime):{}:true",starting_station_Time);
                return true;
            }else {
                logger.info("starting_station_Time.isAfter(tatkalOpensTime):{}:false",starting_station_Time);
                return false;
            }
        }else {
            logger.info(" if(starting_station_date.equals(LocalDate.now()):{}:false",starting_station_date);
            return false;
        }
    }
}
