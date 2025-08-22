package com.example.Train_Service.Entity.Booking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ValidTicket {

    private final static Logger logger = LoggerFactory.getLogger(ValidTicket.class);


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
