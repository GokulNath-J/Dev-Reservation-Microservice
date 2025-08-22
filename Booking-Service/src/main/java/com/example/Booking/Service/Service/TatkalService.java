package com.example.Booking.Service.Service;

import com.example.Booking.Service.DTO.BookingRequest;
import com.example.Booking.Service.DTO.TicketPrice;
import com.example.Booking.Service.Entity.TatkalTickets;
import com.example.Booking.Service.Repository.TatkalRepo;
import com.example.Booking.Service.Repository.TicketPriceRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

@Service
public class TatkalService {

    private final static Logger log = LoggerFactory.getLogger(TatkalService.class);

    private static final LocalTime tatkal_opens_at_for_nonsleepers = LocalTime.of(10, 00, 00);
    private static final LocalTime tatkal_opens_at_for_sleepers = LocalTime.of(11, 00, 00);

    @Autowired
    private TatkalRepo tatkalRepo;

    @Autowired
    private TicketPriceRepo ticketPriceRepo;

    @Autowired
    private BookingServiceToPaymentService bookingServiceToPaymentService;


    public void book(BookingRequest request) {
        log.info("Request in TatkalService");
        if (request.getTravelDate().equals(LocalDate.now().plusDays(1))) {
            log.info("getTravelDate() is Matched:");
            List<TatkalTickets> tatkalTickets = tatkalRepo.findAllByTrainNumber(request.getTrainNumber());
            log.info("TatkalTickets in TatkalService:{}", tatkalTickets);
            TatkalTickets tickets = null;
            for (TatkalTickets tatkalTicket : tatkalTickets) {
                if (tatkalTicket.getStationName().equalsIgnoreCase(request.getFromStationName())
                        && tatkalTicket.getCoachName().equalsIgnoreCase(request.getCoachName())) {
                    tickets = tatkalTicket;
                }
            }
            log.info("TatkalTickets:{}", tickets);
            if (request.getNumberOfTickets() <= tickets.getNoOfSeatsAvailable()) {
                log.info("Tickets Are Available");
                double totalTicketAmount = calculateTotalAmount(request.getBookingMethod(), tickets.getEachSeatPrice(),
                        request.getNumberOfTickets(), request.getCoachName());
                if (totalTicketAmount > 0.0) {
                    String result = bookingServiceToPaymentService.bookTicket(tickets, request, totalTicketAmount);
                    System.out.println(result);
                }
            } else {
                System.out.println("Tickets are Insufficient We Can Confirm Onces Tickets Available");
                System.out.print("Do you Want to proceed:Y/N:");

            }
        } else {
            System.out.println("There is Not Train on this Date");
        }
    }

    private double calculateTotalAmount(String bookingMethod, Double eachSeatPrice, int numberOfTickets, String coachName) {
        TicketPrice ticketPrice = ticketPriceRepo.findByBookingTypeAndCoachName(bookingMethod, coachName);
        Scanner scanner = new Scanner(System.in);
        if (bookingMethod.equalsIgnoreCase("Tatkal")) {
            double totalTicketPrice = (ticketPrice.getPrice() + eachSeatPrice) * numberOfTickets;
            System.out.println("Total Tatakl TicketsPrice = " + totalTicketPrice);
            System.out.print("Do yo want to place booking Y/N.?:");
            String yesOrno = scanner.nextLine();
            if (yesOrno.equalsIgnoreCase("y")) {
                return totalTicketPrice;
            }
        }
        return 0.0;
    }

}
