package com.example.Booking.Service.Service;

import com.example.Booking.Service.DTO.BookingRequest;
import com.example.Booking.Service.Entity.TatkalTickets;
import com.example.Booking.Service.Feign.PaymentFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingServiceToPaymentService {

    private final static Logger log = LoggerFactory.getLogger(BookingServiceToPaymentService.class);

    @Autowired
    private PaymentFeign paymentFeign;


    @Transactional
    public String bookTicket(TatkalTickets tickets, BookingRequest request, double totalTicketAmount) {
        log.info("Request on BookingServiceToPaymentService");
        int noOfTickets = request.getNumberOfTickets();
        try {
            tickets.setNoOfSeatsAvailable(tickets.getNoOfSeatsAvailable() - noOfTickets);
            tickets.setNoOfSeatsBooked(tickets.getNoOfSeatsBooked() + noOfTickets);
            log.info("userName:{}", request.getUserName());
            String paymentResult = paymentFeign.paymentRequest(request.getUserName(), totalTicketAmount);
            log.info("Payment Result:{}", paymentResult);
            return paymentResult;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
