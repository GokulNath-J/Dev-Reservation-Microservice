package Indian_Railway.Indian_Railway.Entity.Booking;

import Indian_Railway.Indian_Railway.Entity.TrainDetails;
import Indian_Railway.Indian_Railway.Entity.TrainStoppingStation;
import Indian_Railway.Indian_Railway.Service.ServiceClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
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


    }
    private boolean checkTrainStartingStationTime(TrainStoppingStation first, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate now = LocalDate.now();
        LocalDate date1 = LocalDate.parse(date,formatter);
        logger.info("Now : {}",now);
        logger.info("Date : {}",date1);
        if (now.equals(date1)) {
            logger.info("True");
            return true;
        }
        logger.info("False");
        return false;
    }
}
