package com.example.Passenger_Reservation.OpenFiegn;

import com.example.Passenger_Reservation.Entity.PassengerTicketBooking;
import com.example.Passenger_Reservation.PassengerWrapper.TicketCheckingWrapper;
import com.example.Passenger_Reservation.PassengerWrapper.TrainWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "indian-railway",url = "http://localhost:8882/rail")
public interface PassengerFiegn {

   /* @GetMapping("rail/findbytrainnumber/{train_Number}")
    public ResponseEntity<TrainWrapper> gettrainbynumber(@PathVariable int train_Number);*/

    @GetMapping("/findtrainbynumber/{train_Number}")
    public ResponseEntity<TrainWrapper> get_trainwrapper_bytrain_number(@PathVariable int train_Number);

    @GetMapping("/checkticketavailabiltiybytrainnumber/")
    public ResponseEntity<TicketCheckingWrapper> Check_Allcoach_Ticket_Availability(@RequestParam int train_number);

    @PutMapping("/ticket_booking/")
    public ResponseEntity<PassengerTicketBooking> Ticket_Booking(@RequestParam int train_number, @RequestParam String coach,
                                                                 @RequestParam int no_of_tickets);
}
