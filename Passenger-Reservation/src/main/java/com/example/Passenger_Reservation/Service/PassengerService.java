package com.example.Passenger_Reservation.Service;

import com.example.Passenger_Reservation.Entity.PassengerTicketBooking;
import com.example.Passenger_Reservation.OpenFiegn.PassengerFiegn;
import com.example.Passenger_Reservation.PassengerWrapper.TicketCheckingWrapper;
import com.example.Passenger_Reservation.PassengerWrapper.TrainWrapper;
import com.example.Passenger_Reservation.Repository.PassengerTicketBookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PassengerService {

    private PassengerTicketBookingRepo passengerTicketBookingRepo;

    private PassengerFiegn passengerFiegn;

    @Autowired
    public PassengerService(PassengerTicketBookingRepo passengerTicketBookingRepo, PassengerFiegn passengerFiegn) {
        this.passengerTicketBookingRepo = passengerTicketBookingRepo;
        this.passengerFiegn = passengerFiegn;
    }

    public ResponseEntity<TrainWrapper> Get_TrainWrapper_ByTrainNumber(int trainNumber) {
       TrainWrapper trainWrapper = passengerFiegn.get_trainwrapper_bytrain_number(trainNumber).getBody();
       TrainWrapper trainWrapper1 = new TrainWrapper();
        trainWrapper1.setTrain_number(trainWrapper.getTrain_number());
        trainWrapper1.setTrain_name(trainWrapper.getTrain_name());
        trainWrapper1.setStarting_point(trainWrapper.getStarting_point());
        trainWrapper1.setDestination(trainWrapper.getDestination());
        trainWrapper1.setNo_stopping_stations(trainWrapper.getNo_stopping_stations());
        trainWrapper1.setTrainStoppingStationWrapperList(trainWrapper.getTrainStoppingStationWrapperList());
        return new ResponseEntity<>(trainWrapper1,HttpStatus.OK);
    }

    public ResponseEntity<TicketCheckingWrapper> check_allcoach_ticket_availability(int trainNumber) {
        ResponseEntity<TicketCheckingWrapper> ticketBookingWrapper = passengerFiegn.Check_Allcoach_Ticket_Availability(trainNumber);
        return ticketBookingWrapper;
    }

    public ResponseEntity<PassengerTicketBooking> Ticket_Booking(int trainNumber, String coach, int no_of_tickets) {
        ResponseEntity<PassengerTicketBooking> passengerTicketBooking1 = passengerFiegn.Ticket_Booking(trainNumber,coach,no_of_tickets);
        passengerTicketBookingRepo.save(passengerTicketBooking1.getBody());
        return passengerTicketBooking1;
    }


}
