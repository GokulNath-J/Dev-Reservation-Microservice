package com.example.Train_Service.Entity.Booking;


import com.example.Train_Service.Entity.TrainStoppingStation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Component
public class CheckTrainStationFromToDestination {

    private final static Logger logger = LoggerFactory.getLogger(CheckTrainRunningDays.class);

    public boolean checkTainFromToDestination(ListIterator<TrainStoppingStation> list1, String fromstation, String destination){
        List<String> list2 = new ArrayList<>();
        while (list1.hasNext()){
            logger.info(" list2 {}",list2);
            list2.add(list1.next().getStation_name());
            logger.info("list 2 size:{}",list2.size());
        }
        List<String> list3 =  List.of(fromstation,destination);
        if (list2.containsAll(list3)){
            logger.info("From {} to Destination {} FOUND",fromstation,destination);
            return true;
        }else {
            System.out.println("From and Destionation Not found Throw Exception");
            return true;
        }
    }
}
