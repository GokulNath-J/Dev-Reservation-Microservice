package com.example.Passenger_Reservation.Controller;

import com.example.Passenger_Reservation.Entity.PassengerTicketBooking;
import com.example.Passenger_Reservation.PassengerWrapper.TicketCheckingWrapper;
import com.example.Passenger_Reservation.PassengerWrapper.TrainWrapper;
import com.example.Passenger_Reservation.Service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app")
public class PassengerController {

    private PassengerService passengerService;

    @Autowired
    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping("/login")
    public String getmessage(){
        return "Welcome to Indian Railway";
    }

    @GetMapping("/findtrainbynumber/{train_Number}")
    public ResponseEntity<TrainWrapper> get_trainwrapper_bytrainnumber(@PathVariable int train_Number){
        return passengerService.Get_TrainWrapper_ByTrainNumber(train_Number);
    }

    @GetMapping("/checkticketavailabiltiybytrainnumber/")
    public ResponseEntity<TicketCheckingWrapper> Check_Allcoach_Ticket_Availability(@RequestParam int train_number){
        return passengerService.check_allcoach_ticket_availability(train_number);
    }

    @PutMapping("/ticket_booking/")
    public ResponseEntity<PassengerTicketBooking> Ticket_Booking(@RequestParam int train_number,
                                                                 @RequestParam String coach,
                                                                 @RequestParam int no_of_tickets){
        return passengerService.Ticket_Booking(train_number,coach,no_of_tickets);
    }


}
