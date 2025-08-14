package Indian_Railway.Indian_Railway.Entity.Booking;

import Indian_Railway.Indian_Railway.Entity.TrainDetails;

public interface Booking {

    void book(TrainDetails trainDetails,String coach, Integer noOfTickets, Integer amount,
              String bookingType, String date, String fromstation, String destination);

}
