package com.example.Train_Service.Entity.Booking;

import com.example.Train_Service.Entity.TrainRunningDays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class CheckTrainRunningDays {

    private final static Logger logger = LoggerFactory.getLogger(CheckTrainRunningDays.class);

    public boolean checkTrainRunningDay(String date, List<TrainRunningDays> train_running_days) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        logger.info("Date formatter:");
        LocalDate localDate = LocalDate.parse(date,formatter);
        logger.info("{} Date",localDate);
        DayOfWeek day = localDate.getDayOfWeek();
        logger.info("{} Day",day);
        logger.info("Checking with Stream");
        // ListIterator<TrainRunningDays> listIterator= train_running_days.listIterator();
        boolean check = train_running_days.stream().anyMatch(train -> train.name().equalsIgnoreCase(day.name()));
        if(check){
            logger.info("Stream Result {}",check);
            return true;
        }
        logger.info("Day {} Match Not Found",day);
        return false;
    }

}
