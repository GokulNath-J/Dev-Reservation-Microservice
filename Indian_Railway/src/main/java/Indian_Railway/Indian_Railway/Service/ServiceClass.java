package Indian_Railway.Indian_Railway.Service;

import Indian_Railway.Indian_Railway.Entity.Booking.Booking;
import Indian_Railway.Indian_Railway.Entity.Booking.GeneralReservation;
import Indian_Railway.Indian_Railway.Entity.Booking.TatkalBooking;
import Indian_Railway.Indian_Railway.Entity.Ticket;
import Indian_Railway.Indian_Railway.Entity.TrainCoaches;
import Indian_Railway.Indian_Railway.Entity.TrainDetails;
import Indian_Railway.Indian_Railway.Entity.TrainRunningDays;
import Indian_Railway.Indian_Railway.Repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

@Service
public class ServiceClass {

    private static final Logger logger = LoggerFactory.getLogger(ServiceClass.class);

    private TrainDetailsRepo trainDetailsRepo;

    private StationDetailsRepo stationDetailsRepo;

    private TrainStoppingStationRepo trainStoppingStationRepo;

    private TrainCoachesRepo trainCoachesRepo;

    private TrainReservationSystemRepo trainReservationSystemRepo;

    @Autowired
    public ServiceClass(TrainDetailsRepo trainDetailsRepo, StationDetailsRepo stationDetailsRepo, TrainStoppingStationRepo trainStoppingStationRepo, TrainCoachesRepo trainCoachesRepo, TrainReservationSystemRepo trainReservationSystemRepo) {
        this.trainDetailsRepo = trainDetailsRepo;
        this.stationDetailsRepo = stationDetailsRepo;
        this.trainStoppingStationRepo = trainStoppingStationRepo;
        this.trainCoachesRepo = trainCoachesRepo;
        this.trainReservationSystemRepo = trainReservationSystemRepo;
    }

    public ResponseEntity<List<TrainDetails>> GetAll() {
        List<TrainDetails> getall = trainDetailsRepo.findAll();
        return new ResponseEntity<>(getall, HttpStatus.ACCEPTED);
    }


    public ResponseEntity<String> AddOneTrain(TrainDetails trainDetails) {
        logger.info("Enter into the method");
        trainDetails.setNo_of_stoppingstations(trainDetails.getTrainStoppingStations().size());
        trainDetails.setFrom_station(trainDetails.getTrainStoppingStations().getFirst().getStation_name());
        trainDetails.setDestination_station(trainDetails.getTrainStoppingStations().getLast().getStation_name());
        ListIterator<TrainCoaches> list = trainDetails.getTrainCoachesList().listIterator();
        while (list.hasNext()) {
            TrainCoaches trainCoaches = list.next();
            logger.info("{}", trainCoaches.getCoach_name());
            String Coaches = trainCoaches.getCoach_name();
            trainCoaches.getTicket().setTicket_id(trainDetails.getTrain_number() + ":" + Coaches);
            logger.info("{} is Perfectly added", trainCoaches.getCoach_name());
        }

//        trainDetails.getTrainCoaches().setTotal_no_of_coaches(
//                (trainDetails.getTrainCoaches().getNo_of_Generalcoaches())
//                        +(trainDetails.getTrainCoaches().getNo_of_sleepercoaches())
//                        +(trainDetails.getTrainCoaches().getNo_of_Luggagecoaches())
//                        +(trainDetails.getTrainCoaches().getNo_of_ACcoaches())
//                        +(trainDetails.getTrainCoaches().getNo_of_reserved_coache()));
        //new changes
//        trainDetails.getTrainCoaches().setId(trainDetails.getTrain_number());
//        trainDetails.getTrainStoppingStations().getFirst().setDeparture_date_time(LocalDateTime.now());
//        trainDetails.getTrainStoppingStations().getFirst().setArrival_date_time(LocalDateTime.now());


//        LocalDateTime departure_time = trainDetails.getTrainStoppingStations().get(0).getDeparture_date_time();


//        trainDetails.getTrainReservationSystem()
//                        .setReservation_closes_at(departure_time.minusHours(12));
//        trainDetails.getTrainReservationSystem().setReservation_opens_at(LocalDateTime.now());
//        trainDetails.getTrainReservationSystem().setTotal_no_reservation_tickets(
//                (trainDetails.getTrainReservationSystem().getNo_ac_coach_tickets()) +
//                        (trainDetails.getTrainReservationSystem().getNo_nonac_reservation_tickets())+
//                        (trainDetails.getTrainReservationSystem().getNo_sleeper_coach_tickets()));
//        trainDetails.getTrainReservationSystem().setTrain_number(trainDetails.getTrain_number());
        trainDetailsRepo.save(trainDetails);
        return new ResponseEntity<>("One Train Added", HttpStatus.CREATED);
    }

    public void bookTicket(Integer trainNumber, String coach, Integer noOfTickets, Integer amount,
                           String bookingType, String date) {
        TrainDetails trainDetails = trainDetailsRepo.findByTrain_Number(trainNumber);
        ListIterator<TrainCoaches> listIterator = trainDetails.getTrainCoachesList().listIterator();
        Ticket ticket = null;
        TrainCoaches trainCoaches = null;
        while (listIterator.hasNext()) {
            trainCoaches = listIterator.next();
            String Coaches = trainCoaches.getCoach_name();
            logger.info("First If {}", Coaches);
            if (Coaches.equalsIgnoreCase(coach)) {
                ticket = trainCoaches.getTicket();
                logger.info("Second IF {}", Coaches);
                break;
            }
        }

        List<TrainRunningDays> train_running_days = trainDetails.getTrainRunningDays().stream().toList();
        if(checkTrainRunningDay(date,train_running_days)){
            logger.info("Train TrainRunningDays is Valid");
        }
        Booking booking = bookingTypeCheck(bookingType);
        if (booking != null){

        }else {
            logger.info("Throw Exception!");
        }
        if(noOfTickets <= ticket.getAvailable_Tickets()){
            logger.info("{} Tickets are Available",noOfTickets);
            booking.book();
        }else{
            logger.info("{} Tickets are Not Available",noOfTickets);
        }
        System.out.println(trainCoaches.getCoach_name());
    }

    private boolean checkTrainRunningDay(String date, List<TrainRunningDays> train_running_days) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        logger.info("Date formatter:");
        LocalDate localDate = LocalDate.parse(date,formatter);
        logger.info("{} Date",localDate);
        DayOfWeek day = localDate.getDayOfWeek();
        logger.info("{} Day",day);
        
        if (train_running_days.contains(day)){
            logger.info("train_running_days.contains(day) -> {} ",true);
            return true;
        }
        logger.info("Checking with Stream");
        ListIterator<TrainRunningDays> listIterator= train_running_days.listIterator();
        boolean check = train_running_days.stream().anyMatch(train -> train.name().equalsIgnoreCase(day.name()));
        logger.info("Stream Result {}",check);
        while(listIterator.hasNext()){
            TrainRunningDays trainRunningDays = listIterator.next();
            String name = trainRunningDays.name();
            logger.info("trainRunningDays.name() -> {}",name);
            if (name.equalsIgnoreCase(day.name())){
                logger.info("Day {} Match Found {}",day,name);
                return true;
            }
        }
        logger.info("Day {} Match Not Found",day);
        return false;
    }

    private Booking bookingTypeCheck(String bookingType) {
        if (bookingType.equalsIgnoreCase("tatkal")){
            return new TatkalBooking();
        } else if (bookingType.equalsIgnoreCase("GR")) {
            return new GeneralReservation();
        }
        return null;
    }


//    public void AddAllRail(List<TrainDetails> trainDetails) {
//        trainDetailsRepo.saveAll(trainDetails);
//    }
//
//    public ResponseEntity<TrainDetails> GetTrainbyName(String trainname) {
//        return new ResponseEntity<>(trainDetailsRepo.findbytrain_name(trainname),HttpStatus.FOUND);
//    }
//
//    public ResponseEntity<TrainWrapper> Get_TrainWrapper_ByTrainNumber(int trainNumber) {
//        TrainDetails trainDetails = trainDetailsRepo.findByTrain_Number(trainNumber);
//        TrainWrapper trainWrapper = new TrainWrapper();
//        trainWrapper.setTrain_number(trainDetails.getTrain_number());
//        trainWrapper.setTrain_name(trainDetails.getTrain_name());
//        trainWrapper.setStarting_point(trainDetails.getStartingPoint());
//        trainWrapper.setDestination(trainDetails.getDestination());
//        List<TrainStoppingStationWrapper> stoppingStationWrappers = new ArrayList<>();
//        for (int i = 0; i < trainDetails.getTrainStoppingStations().size(); i++) {
//            String station_name = trainDetails.getTrainStoppingStations().get(i).getStation_name();
//            Integer platform_number = trainDetails.getTrainStoppingStations().get(i).getPlatform_no();
//            TrainStoppingStationWrapper trainStoppingStationWrapper = new TrainStoppingStationWrapper();
//            trainStoppingStationWrapper.setStation_name(station_name);
//            trainStoppingStationWrapper.setPlatform_no(platform_number);
//            stoppingStationWrappers.add(trainStoppingStationWrapper);
//        }
//
//        trainWrapper.setTrainStoppingStationWrapperList(stoppingStationWrappers);
//        trainWrapper.setNo_stopping_stations(trainDetails.getNo_of_stoppingstations());
//        return new ResponseEntity<>(trainWrapper,HttpStatus.OK);
//    }
//
//    public ResponseEntity<TrainDetails> GetDetailsTrainByNumber(int trainNumber) {
//        return new ResponseEntity<>(trainDetailsRepo.findByTrain_Number(trainNumber),HttpStatus.FOUND);
//    }
//
//    public ResponseEntity<TicketCheckingWrapper> check_all_coach_ticket_availability(int trainnumber){
//         TrainDetails trainDetails = trainDetailsRepo.findByTrain_Number(trainnumber);
//         TicketCheckingWrapper ticketCheckingWrapper = new TicketCheckingWrapper();
//         ticketCheckingWrapper.setTrain_name(trainDetails.getTrain_name());
//         ticketCheckingWrapper.setTrain_number(trainDetails.getTrain_number());
//         ticketCheckingWrapper.setNo_general_reserve_tickets(trainDetails.getTrainReservationSystem().getNo_nonac_reservation_tickets());
//         ticketCheckingWrapper.setNo_ac_tickets(trainDetails.getTrainReservationSystem().getNo_ac_coach_tickets());
//         ticketCheckingWrapper.setNo_sleeper_tickets(trainDetails.getTrainReservationSystem().getNo_sleeper_coach_tickets());
//         ticketCheckingWrapper.setReservation_closes_at(trainDetails.getTrainReservationSystem().getReservation_closes_at());
//         ticketCheckingWrapper.setReservation_opens_at(trainDetails.getTrainReservationSystem().getReservation_opens_at());
//         return new ResponseEntity<>(ticketCheckingWrapper,HttpStatus.OK);
//    }
//    public ResponseEntity<PassengerTicketBooking> ticket_booking(int trainNumber, String coach, int noOfTickets) {
//        TrainReservationSystem trainReservationSystem = trainReservationSystemRepo.findByTrain_Number(trainNumber);
//        PassengerTicketBooking passengerTicketBooking = new PassengerTicketBooking();
//        Integer ticket_Available;
//        String Message = "";
//        if(coach.trim().equalsIgnoreCase("AC")){
//             ticket_Available = trainReservationSystem.getNo_ac_coach_tickets();
//             Message = Ac_ticket_booking(trainNumber,ticket_Available,noOfTickets,passengerTicketBooking);
//             if(Message.equalsIgnoreCase("success")){
//                 passengerTicketBooking1(passengerTicketBooking,coach.toUpperCase()+" Ticket :"+noOfTickets,trainNumber);
//             }
//
//        } else if (coach.trim().equalsIgnoreCase("Sleeper")) {
//            ticket_Available = trainReservationSystem.getNo_sleeper_coach_tickets();
//
//            Message = Ac_ticket_booking(trainNumber,ticket_Available,noOfTickets,passengerTicketBooking);
//            if(Message.equalsIgnoreCase("success")){
//                passengerTicketBooking1(passengerTicketBooking,coach.toUpperCase()+" Ticket :"+noOfTickets,trainNumber);
//
//            }
//
//        }else {
//            ticket_Available = trainReservationSystem.getNo_nonac_reservation_tickets();
//
//            Message = Ac_ticket_booking(trainNumber,ticket_Available,noOfTickets,passengerTicketBooking);
//            if(Message.equalsIgnoreCase("success")){
//                passengerTicketBooking1(passengerTicketBooking,coach.toUpperCase()+" Ticket :"+noOfTickets,trainNumber);
//                //   passengerTicketBooking.setNo_of_tickets("Ac Ticket : "+noOfTickets);
//            }
//
//        }
//        if(Message.equalsIgnoreCase("Success")){
//            return new ResponseEntity<>(passengerTicketBooking,HttpStatus.OK);
//        }else {
//            return new ResponseEntity<>(passengerTicketBooking,HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    private String Ac_ticket_booking(int trainNumber, Integer ticketAvailable, int noOfTickets,PassengerTicketBooking passengerTicketBooking) {
//        if (noOfTickets<=ticketAvailable){
//            noOfTickets = (ticketAvailable-noOfTickets);
//            trainReservationSystemRepo.updateAcTicket(trainNumber,noOfTickets);
//            passengerTicketBooking.setTicket_conformation("Success");
//            passengerTicketBooking.setReserved_on_datetime(LocalDateTime.now());
//            return "Success";
//        }else{
//            passengerTicketBooking.setTicket_conformation("Reservation Failed");
//            passengerTicketBooking.setReserved_on_datetime(LocalDateTime.now());
//            return "Reservation Failed";
//        }
//    }
//    private String Sleeper_ticket_booking(int trainNumber, Integer ticketAvailable, int noOfTickets,PassengerTicketBooking passengerTicketBooking) {
//        if (noOfTickets<=ticketAvailable){
//            noOfTickets = (ticketAvailable-noOfTickets);
//            trainReservationSystemRepo.updateSleeperTicket(trainNumber,noOfTickets);
//            passengerTicketBooking.setTicket_conformation("Success");
//            passengerTicketBooking.setReserved_on_datetime(LocalDateTime.now());
//            return "Success";
//        }else{
//            passengerTicketBooking.setTicket_conformation("Reservation Failed");
//            passengerTicketBooking.setReserved_on_datetime(LocalDateTime.now());
//            return "Reservation Failed";
//        }
//    }
//    private String General_reserve_ticket_booking(int trainNumber, Integer ticketAvailable, int noOfTickets,PassengerTicketBooking passengerTicketBooking) {
//        if (noOfTickets<=ticketAvailable){
//            noOfTickets = (ticketAvailable-noOfTickets);
//            trainReservationSystemRepo.updateGeneralReserveTicket(trainNumber,noOfTickets);
//            passengerTicketBooking.setTicket_conformation("Success");
//            passengerTicketBooking.setReserved_on_datetime(LocalDateTime.now());
//            return "Success";
//        }else{
//            passengerTicketBooking.setTicket_conformation("Reservation Failed");
//            passengerTicketBooking.setReserved_on_datetime(LocalDateTime.now());
//            return "Reservation Failed";
//        }
//    }
//
//    private void passengerTicketBooking1(PassengerTicketBooking passengerTicketBooking,String noOfTickets,int trainNumber) {
//        TrainDetails trainDetails = trainDetailsRepo.findByTrain_Number(trainNumber);
//       passengerTicketBooking.setTrain_name(trainDetails.getTrain_name());
//       passengerTicketBooking.setTrain_number(trainDetails.getTrain_number());
//       passengerTicketBooking.setStarting_point(trainDetails.getStartingPoint());
//       passengerTicketBooking.setDestination(trainDetails.getDestination());
//       passengerTicketBooking.setNo_of_tickets(noOfTickets);
//       passengerTicketBooking.setReserved_on_datetime(LocalDateTime.now());
//    }
//
//    public String delete_train_trainid(int trainNumber) {
//        trainDetailsRepo.deleteById(trainNumber);
//        return "Train Deleted";
//    }
//
//    public ResponseEntity<List<TrainWrapper>> check_train_from_startingpoint(String startingpoint) {
//        List<TrainDetails> traindetails = trainDetailsRepo.findByStartingPointIgnoreCase(startingpoint);
//        List<TrainWrapper> trainWrapperList = new ArrayList<>();
//        for (int i = 0; i < traindetails.size(); i++){
//            trainWrapperList.get(i).setTrain_number(traindetails.get(i).getTrain_number());
//            trainWrapperList.get(i).setTrain_name(traindetails.get(i).getTrain_name());
//            List<TrainStoppingStationWrapper> trainStoppingStationWrapper = new ArrayList<>();
//            for (int j = 0; j < traindetails.get(i).getTrainStoppingStations().size(); j++) {
//                String station_name = traindetails.get(i).getTrainStoppingStations().get(j).getStation_name();
//                Integer platform_number = traindetails.get(i).getTrainStoppingStations().get(j).getPlatform_no();
//                trainStoppingStationWrapper.get(j).setStation_name(station_name);
//                trainStoppingStationWrapper.get(j).setPlatform_no(platform_number);
//            }
//            trainWrapperList.get(i).setTrainStoppingStationWrapperList(trainStoppingStationWrapper);
//            trainWrapperList.get(i).setNo_stopping_stations(traindetails.get(i).getNo_of_stoppingstations());
//        }
//        return new ResponseEntity<>(trainWrapperList,HttpStatus.OK);
//    }


}
