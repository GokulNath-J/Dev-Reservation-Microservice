package com.example.Booking.Service.Controller;

import com.example.Booking.Service.DTO.BookingRequest;
import com.example.Booking.Service.DTO.TicketPrice;
import com.example.Booking.Service.DTO.TrainDTO1;
import com.example.Booking.Service.DTO.TrainDTOWrapper;
import com.example.Booking.Service.Entity.PremiumTatkalTickets;
import com.example.Booking.Service.Entity.TatkalTickets;
import com.example.Booking.Service.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingServiceController {

    @Autowired
    private BookingService bookingService;


//    @PostMapping("/addtickets")
//    public String addTatkalTickets(){
//        bookingService.addTatkalAndPremiumTatkatTickets();
//        return "Tickets Saved";
//    }

    @GetMapping("/getAllTatkalTickets")
    public List<TatkalTickets> getAllTatkalTickets() {
        return bookingService.getAllTatkalTickets();
    }

    @GetMapping("/getPremiumTatkalTickets")
    public List<PremiumTatkalTickets> getPremiumTatkalTickets() {
        return bookingService.getPremiumTatkalTickets();
    }

    @PostMapping("/book")
    public String booking(@RequestBody BookingRequest request) {
        return bookingService.book(request);
    }

    @GetMapping("/getAllTatkalTicketsByTrainNumber")
    public List<TatkalTickets> getAllTatkalTicketsByTrainNumber(@RequestParam Integer trainNumber) {
        return bookingService.getAllTatkalTicketsByTrainNumber(trainNumber);
    }

    @GetMapping("/getTrainDTOWrapperManually")
    public HashMap<Integer, List<TrainDTO1>> getTrainDTOWrapperManually() {
        return bookingService.getTrainDTOWrapperManually();
    }

    @PostMapping("/addPrice")
    public String addPrice(@RequestBody TicketPrice ticketPrice){
        bookingService.addPrice(ticketPrice);
        return "Success";
    }
}
