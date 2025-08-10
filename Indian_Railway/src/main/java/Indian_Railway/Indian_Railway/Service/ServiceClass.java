package Indian_Railway.Indian_Railway.Service;

import Indian_Railway.Indian_Railway.Entity.*;
import Indian_Railway.Indian_Railway.Entity.Booking.*;
import Indian_Railway.Indian_Railway.Repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceClass {

    private static final Logger logger = LoggerFactory.getLogger(ServiceClass.class);

    private TrainDetailsRepo trainDetailsRepo;

    private StationDetailsRepo stationDetailsRepo;

    private TrainStoppingStationRepo trainStoppingStationRepo;

    private TrainCoachesRepo trainCoachesRepo;

    private TrainReservationSystemRepo trainReservationSystemRepo;

    @Autowired
    private ValidTicket validTicket;

    @Autowired
    private CheckTrainRunningDays checkTrainRunningDay;

    @Autowired
    private CheckTrainStationFromToDestination checkTrainStationFromToDestination;

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
//        TrainDetails trainDetails1 = trainDetails;
        List<TrainStoppingStation> trainStoppingStation = trainDetails.getTrainStoppingStations();
        List<TrainCoaches> trainCoaches = trainDetails.getTrainCoachesList();
        ListIterator<TrainCoaches> list = trainDetails.getTrainCoachesList().listIterator();
        trainDetails.setNo_of_stoppingstations(trainDetails.getTrainStoppingStations().size());
        trainDetails.setFrom_station(trainDetails.getTrainStoppingStations().getFirst().getStation_name());
        trainDetails.setDestination_station(trainDetails.getTrainStoppingStations().getLast().getStation_name());
//        while (list.hasNext()) {
//            TrainCoaches trainCoaches1 = list.next();
//            logger.info("{}", trainCoaches1.getCoach_name());
//            String Coaches = trainCoaches1.getCoach_name();
//            trainCoaches1.getTicket().setTicket_id(trainDetails.getTrain_number() + ":" + Coaches);
//            logger.info("TickeID {} is Perfectly added", trainCoaches1.getCoach_name());
//        }

        List<String> bookingtype = List.of("Tatkal","Premium Tatkal","Normal Reservation");
        addTicketsToEachStations2(trainDetails.getTrain_number(),trainStoppingStation,trainCoaches,bookingtype);
        trainDetailsRepo.save(trainDetails);
        return new ResponseEntity<>("One Train Added", HttpStatus.CREATED);
    }
    //Third method written to add Number of tickets to each station by booking type (Method Successfully)
    private void addTicketsToEachStations2(int train_number,List<TrainStoppingStation> trainStoppingStation, List<TrainCoaches> trainCoaches, List<String> bookingtype) {
        int totalStations = trainStoppingStation.size();
        for (int i = 0; i < totalStations; i++) {
            List<TicketsPerStation> list = new ArrayList<>();
            for (TrainCoaches trainCoach : trainCoaches) {
                Integer ticketsDividedForEachBookingType = trainCoach.getTotal_no_seats() / 3;
                int divideTicketsPerStation = ticketsDividedForEachBookingType / totalStations;
                int remaining_tickets = ticketsDividedForEachBookingType % totalStations;
                for (String bookingname : bookingtype) {
                    TicketsPerStation ticketsPerStations = new TicketsPerStation
                            (bookingname,train_number ,trainCoach.getCoach_name(), divideTicketsPerStation,trainCoach.getEach_seat_price());
                    logger.info("ticketsPerStations object is Created ");
                    logger.info("To Verify ticketsPerStations object:{}", ticketsPerStations);
                    list.add(ticketsPerStations);
                    logger.info("TicketsPerStation is added to the List:");
                    logger.info("To Verify TicketsPerStation is added to the List size:{}", list.size());
                    logger.info("TicketsPerStation object is added to trainStoppingStation");
                }
            }
            trainStoppingStation.get(i).setTicketsPerStations(list);
        }
    }
//Second method written to add Number of tickets to each station by booking type (Method has logical Error)
//    private void addTicketsToEachStations1(List<TrainStoppingStation> trainStoppingStation, List<TrainCoaches> trainCoaches,List<String> bookingtype) {
//        int totalStations = trainStoppingStation.size();
//        for (TrainCoaches coaches : trainCoaches) {
//            logger.info("coachename:{}", coaches.getCoach_name());
//            Integer ticketsDividedForEachBookingType = coaches.getTotal_no_seats() / 3;
//            int divideTicketsPerStation = ticketsDividedForEachBookingType / totalStations;
//            int remaining_tickets = ticketsDividedForEachBookingType % totalStations;
//            if (ticketsDividedForEachBookingType != null) {
//                //logger.info("for (TrainCoaches coaches: trainCoaches):");
//                logger.info("ticketsDividedForEachBookingType:{}", ticketsDividedForEachBookingType);
//                for (String bookingname : bookingtype) {
//                    int j = 0;
//                    logger.info("bookingname:{}", bookingname);
//                    logger.info("coachename:{}", coaches.getCoach_name());
//                    logger.info("totalStations:{},divideTicketsPerStation:{},remaining_tickets:{}", totalStations, divideTicketsPerStation, remaining_tickets);
//                    TrainStoppingStation station = new TrainStoppingStation();
//                    List<TicketsPerStation> list = new ArrayList<>();
//                    for (int i = 0; i < totalStations; i++) {
//                        logger.info("stoppingStation:");
//                        logger.info("TicketsPerStation list create with size:{}", list.size());
//                        TicketsPerStation ticketsPerStations = new TicketsPerStation
//                                (bookingname, trainStoppingStation.getFirst().getTrain_number(),coaches.getCoach_name(), divideTicketsPerStation,coaches.getEach_seat_price());
//                      //  logger.info("Coach Name:{}", ticketsPerStations.getCoach_name());
//                        logger.info("ticketsPerStations object is Created ");
//                        logger.info("To Verify ticketsPerStations object:{}", ticketsPerStations);
//                        list.add(ticketsPerStations);
//                        logger.info("TicketsPerStation is added to the List:");
//                        logger.info("To Verify TicketsPerStation is added to the List size:{}", list.size());
//                        logger.info("TicketsPerStation object is added to trainStoppingStation");
//                        trainStoppingStation.get(i);
//                        j++;
//                    }
////                    trainStoppingStation.set(j)
//                    logger.info("index {} trainStoppingStation.get(j).setTicketsPerStations(list) size:{}",
//                            j,trainStoppingStation.size());
////                    logger.info("trainStoppingStation.get(j).getTicketsPerStations() size:{}",
////                            trainStoppingStation.get(j).getTicketsPerStations().size());
////                    trainStoppingStation.get(j).getTicketsPerStations().getLast().setTotal_no_of_seats(divideTicketsPerStation+remaining_tickets);
//                }
//            } else {
//                logger.info(" Divided Ticket is Null May be it is Lucage Coach");
//            }
//        }
//        logger.info("All done");
//    }

//First method written to add Number of tickets to each station by booking type (Method has logical Error)
//        private void addTicketsToEachStations (String bookingname,int dividedTickets, String coachename,
//        double each_seat_price, List<TrainStoppingStation > trainStoppingStation){
//            logger.info("addTicketsToEachStations method()");
//            logger.info("Values:-> bookingname:{},dividedTickets:{},coachename:{},each_seat_price:{},trainStoppingStation:{}"
//                    , bookingname, dividedTickets, coachename, each_seat_price);
//            int totalStations = trainStoppingStation.size();
//            int divideTicketsPerStation = dividedTickets / totalStations;
//            int remaining_tickets = dividedTickets % totalStations;
//            logger.info("totalStations:{},divideTicketsPerStation:{},remaining_tickets:{}", totalStations, divideTicketsPerStation, remaining_tickets);
//            TrainStoppingStation station = new TrainStoppingStation();
//            for (int i = 0; i < totalStations; i++) {
//                logger.info("stoppingStation:");
//                List<TicketsPerStation> list = new ArrayList<>();
//                logger.info("TicketsPerStation list create with size:{}", list.size());
//                TicketsPerStation ticketsPerStations = new TicketsPerStation
//                        (bookingname, trainStoppingStation.getFirst().getTrain_number(), coachename, divideTicketsPerStation, each_seat_price);
//                logger.info("Coach Name:{}", ticketsPerStations.getCoach_name());
//                logger.info("ticketsPerStations object is Created ");
//                logger.info("To Verify ticketsPerStations object:{}", ticketsPerStations);
//                list.add(ticketsPerStations);
//                logger.info("TicketsPerStation is added to the List:");
//                logger.info("To Verify TicketsPerStation is added to the List size:{}", list.size());
//                trainStoppingStation.get(i).setTicketsPerStations(list);
//                logger.info("TicketsPerStation object is added to trainStoppingStation");
//            }
//            for (TrainStoppingStation stoppingStation : trainStoppingStation) {
//                logger.info("stoppingStation:");
//                TicketsPerStation ticketsPerStations = new TicketsPerStation
//                        (bookingname, stoppingStation.getTrain_number(), coachename, divideTicketsPerStation, each_seat_price);
//                TicketsPerStation ticketsPerStations = new TicketsPerStation();
//                ticketsPerStations.setTrain_number(stoppingStation.getTrain_number());
//                ticketsPerStations.setCoach_name(coachename);
//                ticketsPerStations.setBooking_type(bookingname);
//                ticketsPerStations.setEach_seat_price(each_seat_price);
//                ticketsPerStations.setTotal_no_of_seats(divideTicketsPerStation);
//                List<TicketsPerStation> list = List.of(ticketsPerStations);
//                TrainStoppingStation station = stoppingStation;
//                station.setTicketsPerStations(list);
//                trainStoppingStation.get(i).
//                if (stoppingStation.getTicketsPerStations() != null) {
//                    logger.info("TicketsPerStation is add succssefuly");
//                    continue;
//                } else {
//                    logger.info("TicketsPerStation is Not add succssefuly");
//                }
//                logger.info("for (TrainStoppingStation stoppingStation : trainStoppingStation)");
//                for (TicketsPerStation ticketsPerStation : ticketsPerStations) {
//                    logger.info("for (TicketsPerStation ticketsPerStation : ticketsPerStations)");
//
//                    ticketsPerStations.setTrain_number(stoppingStation.getTrain_number());
//                    ticketsPerStations.setCoach_name(coachename);
//                    ticketsPerStations.setBooking_type(bookingname);
//                    ticketsPerStations.setEach_seat_price(each_seat_price);
//                    ticketsPerStations.setTotal_no_of_seats(divideTicketsPerStation);
//                    stoppingStation.setTicketsPerStations(ticketsPerStations);
//                }
//            }
//            trainStoppingStation.getLast().getTicketsPerStations().getLast().setTotal_no_of_seats(divideTicketsPerStation + remaining_tickets);
//            trainStoppingStation.getLast().setTicketsPerStations(ticketsPerStations.getLast().setTotal_no_of_seats(divideTicketsPerStation + remaining_tickets));
//        }
//    }

//    public void bookTicket(Integer trainNumber, String coach, Integer noOfTickets, Integer amount,
//                           String bookingType, String date,String fromstation,String destination) {
//        TrainDetails trainDetails = trainDetailsRepo.findByTrain_Number(trainNumber);
//        if(trainDetails != null){
//            logger.info("trainDetails:");
//        }else {
//            System.out.println("Throw Exception");
//        }
//        ListIterator<TrainStoppingStation> list1 = trainDetails.getTrainStoppingStations().listIterator();
//        boolean check = checkTrainStationFromToDestination.checkTainFromToDestination
//                (list1,fromstation,destination);
//        ListIterator<TrainCoaches> listIterator = trainDetails.getTrainCoachesList().listIterator();
//        Ticket ticket = null;
//        TrainCoaches trainCoaches = null;
//        while (listIterator.hasNext()) {
//            trainCoaches = listIterator.next();
//            String Coaches = trainCoaches.getCoach_name();
//            logger.info("First If {}", Coaches);
//            if (Coaches.equalsIgnoreCase(coach)) {
//                ticket = trainCoaches.getTicket();
//                logger.info("{}", Coaches);
//                break;
//            }
//        }
//        Integer availabletickets = ticket.getAvailable_Tickets();
//        boolean checkavailabletickets = validTicket.checkTicketAvailability(noOfTickets,availabletickets);
//        if (check && checkavailabletickets){
//            Booking booking = bookingTypeCheck(bookingType);
//            booking.book(trainDetails,coach,noOfTickets,amount,bookingType,date,fromstation,destination);
//        }
//
//        System.out.println(trainCoaches.getCoach_name());
//    }


     Booking bookingTypeCheck(String bookingType) {
        if (bookingType.equalsIgnoreCase("tatkal")){
            return new TatkalService();
        } else if (bookingType.equalsIgnoreCase("GR")) {
            return new GeneralReservationService();
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
