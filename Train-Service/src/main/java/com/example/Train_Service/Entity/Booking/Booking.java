package com.example.Train_Service.Entity.Booking;


import com.example.Train_Service.Entity.TrainDetails;

public interface Booking {

    void book(TrainDetails trainDetails, String coach, Integer noOfTickets, Integer amount,
              String bookingType, String date, String fromstation, String destination);

}
