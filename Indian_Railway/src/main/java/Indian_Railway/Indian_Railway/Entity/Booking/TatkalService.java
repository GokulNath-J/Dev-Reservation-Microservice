package Indian_Railway.Indian_Railway.Entity.Booking;

import Indian_Railway.Indian_Railway.Entity.TrainDetails;
import Indian_Railway.Indian_Railway.Entity.TrainStoppingStation;
import Indian_Railway.Indian_Railway.Service.ServiceClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TatkalService implements Booking {

    private static final LocalTime tatkalOpensTime = LocalTime.of(10, 00, 00);

    private static final Logger logger = LoggerFactory.getLogger(TatkalService.class);

    @Override
    public void book(TrainDetails trainDetails, String coach, Integer noOfTickets, Integer amount,
                     String bookingType, String date, String fromstation,
                     String destination) {
        boolean check = checkTrainStartingStationTime(trainDetails.getTrainStoppingStations().getFirst(), date);
        if(check){

        }

    }
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
//        LocalDate date1 = LocalDate.parse(date,formatter);
//        logger.info("Now : {}",now);
//        logger.info("Date : {}",date1);
//        if (now.equals(date1)) {
//            logger.info("True");
//            return true;
//        }
//        logger.info("False");
//        return false;
    }
    public boolean checkTicketAvailability(Integer noOfTickets,Integer availableTickets){
        if(noOfTickets <= availableTickets){
            logger.info("{} Tickets are Available",noOfTickets);
//            booking.book();
            return true;
        }else{
            logger.info("{} Tickets are Not Available",noOfTickets);
        }
        return false;
    }
}
