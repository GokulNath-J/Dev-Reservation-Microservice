package com.example.Train_Service.Service;


import com.example.Train_Service.DTO.TicketsPerStationDTO;
import com.example.Train_Service.DTO.TrainDTO;
import com.example.Train_Service.DTO.TrainDTO1;
import com.example.Train_Service.Entity.*;
import com.example.Train_Service.Entity.Booking.*;
import com.example.Train_Service.Repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public HashMap<Integer, List<TrainDTO1>> sendTrainDTO1() {
        List<TrainDetails> trainDetails = trainDetailsRepo.findAllByFromStationDepartureDate(LocalDate.now().plusDays(1));
        logger.info("trainDetails size:{}", trainDetails.size());
        HashMap<Integer, List<TrainDTO1>> listHashMap = new HashMap<>();
        String bookingName = "Normal Reservation";
        for (TrainDetails trainDetail : trainDetails) {
            List<TrainDTO1> trainDTOList = new ArrayList<>();
            logger.info("for (TrainDetails trainDetail : trainDetails):train_number:{}", trainDetail.getTrain_number());
            List<TrainStoppingStation> station = trainDetail.getTrainStoppingStations();
            logger.info("TrainStoppingStation size:{}", station.size());
            for (TrainStoppingStation trainStoppingStation : station) {
                logger.info("for (TrainStoppingStation trainStoppingStation : station):station_name:{}", trainStoppingStation.getStation_name());
                List<TicketsPerStation> tickets = trainStoppingStation.getTicketsPerStations();
                logger.info("TicketsPerStation size:{}", tickets.size());
                for (int i = 0; i < tickets.size(); i++) {
                    TicketsPerStation ticketsPerStation = tickets.get(i);
                    if (ticketsPerStation.getBooking_type().equalsIgnoreCase(bookingName)) {
                    } else if(ticketsPerStation.getStation_name().equalsIgnoreCase(trainStoppingStation.getStation_name())){
                        TrainDTO1 trainDTO1 = new TrainDTO1();
                        trainDTO1.setTrain_number(trainDetail.getTrain_number());
                        trainDTO1.setBooking_type(ticketsPerStation.getBooking_type());
                        trainDTO1.setCoach_name(ticketsPerStation.getCoach_name());
                        trainDTO1.setStation_name(trainStoppingStation.getStation_name());
                        trainDTO1.setTotal_no_of_seats(ticketsPerStation.getTotal_no_of_seats());
                        trainDTO1.setEach_seat_price(ticketsPerStation.getEach_seat_price());
                        trainDTOList.add(trainDTO1);
                    }
                }
            }
            listHashMap.put(trainDetail.getTrain_number(), trainDTOList);
        }
        return listHashMap;
    }

    public void verifyTicketsPerStation() {
        List<TrainDetails> trainDetails = trainDetailsRepo.findAllByFromStationDepartureDate(LocalDate.now().plusDays(1));
        logger.info("trainDetails size:{}", trainDetails.size());
        for (TrainDetails trainDetail : trainDetails) {
            logger.info("for (TrainDetails trainDetail : trainDetails):train_number:{}", trainDetail.getTrain_number());
            List<TrainStoppingStation> station = trainDetail.getTrainStoppingStations();
            logger.info("TrainStoppingStation size:{}", station.size());
            for (TrainStoppingStation trainStoppingStation : station) {
                logger.info("for (TrainStoppingStation trainStoppingStation : station):station_name:{}", trainStoppingStation.getStation_name());
                List<TicketsPerStation> tickets = trainStoppingStation.getTicketsPerStations();
                logger.info("TicketsPerStation size:{}", tickets.size());
                for (int i = 0; i < tickets.size(); i++) {
                    TicketsPerStation ticketsPerStation = tickets.get(i);
                    logger.info("Train Number:{}", ticketsPerStation.getTrain_number());
                    logger.info("Station Name:{}", trainStoppingStation.getStation_name());
                    logger.info("Booking type:{}", ticketsPerStation.getBooking_type());
                    logger.info("Coach:{}", ticketsPerStation.getCoach_name());
                    logger.info("Total Seat:{}", ticketsPerStation.getTotal_no_of_seats());
                    logger.info("Each Seat Price:{}", ticketsPerStation.getEach_seat_price());
                    logger.info("======================================================================");
                }
            }
        }
    }

    public void verifyTrain() {
        List<TrainDetails> trainDetails = trainDetailsRepo.findAllByFromStationDepartureDate(LocalDate.now().plusDays(1));
        logger.info("trainDetails size:{}", trainDetails.size());
        for (TrainDetails trainDetail : trainDetails) {
            System.out.println(trainDetail);
        }
    }
//    public HashMap<Integer, List<TrainDTO>> sendTrainDTO() {
//        List<TrainDetails> trainDetails = trainDetailsRepo.findAllByFromStationDepartureDate(LocalDate.now().plusDays(1));
//        logger.info("trainDetails size:{}", trainDetails.size());
//        HashMap<Integer, List<TrainDTO>> listHashMap = new HashMap<>();
//        List<TrainDTO> trainDTOList = new ArrayList<>();
//        String bookingName = "Normal Reservation";
//        for (TrainDetails trainDetail : trainDetails) {
//            logger.info("for (TrainDetails trainDetail : trainDetails):train_number:{}", trainDetail.getTrain_number());
//            List<TrainStoppingStation> station = trainDetail.getTrainStoppingStations();
//            logger.info("TrainStoppingStation size:{}", station.size());
//            TrainDTO trainDTO1 = new TrainDTO();
//            for (TrainStoppingStation trainStoppingStation : station) {
//                logger.info("for (TrainStoppingStation trainStoppingStation : station):station_name:{}", trainStoppingStation.getStation_name());
//                List<TicketsPerStation> tickets = trainStoppingStation.getTicketsPerStations();
//                logger.info("TicketsPerStation size:{}", tickets.size());
//                List<TicketsPerStationDTO> ticketsPerStationDTOS = new ArrayList<>();
//                for (int i = 0; i < tickets.size(); i++) {
//                    TicketsPerStation ticketsPerStation = tickets.get(i);
//                    if (ticketsPerStation.getBooking_type().equalsIgnoreCase(bookingName)) {
//                    } else {
//                        TicketsPerStationDTO dto = new TicketsPerStationDTO();
//                        dto.setStation_name(trainStoppingStation.getStation_name());
//                        dto.setCoach_name(ticketsPerStation.getCoach_name());
//                        dto.setTotal_no_of_seats(ticketsPerStation.getTotal_no_of_seats());
//                        dto.setNo_of_seats_available(ticketsPerStation.getTotal_no_of_seats());
//                        dto.setEach_seat_price(ticketsPerStation.getEach_seat_price());
//                        //trainDTO1.setBooking_type(ticketsPerStation.getBooking_type());
//                        ticketsPerStationDTOS.add(dto);
//                    }
//                }
//                trainDTO1.setTrain_number(trainDetail.getTrain_number());
//                trainDTO1.setList(ticketsPerStationDTOS);
//                trainDTOList.add(trainDTO1);
//            }
//            listHashMap.put(trainDetail.getTrain_number(), trainDTOList);
//        }
//        return listHashMap;
//    }

    public void testingSendTrainDTO4() {
        HashMap<Integer, List<TrainDTO1>> hashMap = sendTrainDTO1();
        for (Integer i : hashMap.keySet()) {
            System.out.println(i);
        }
    }

    public void testingSendTrainDTO2() {
        HashMap<Integer, List<TrainDTO1>> hashMap = sendTrainDTO1();
        for (List<TrainDTO1> value : hashMap.values()) {
            System.out.println(value);
        }
    }

    public void testingSendTrainDTO3() {
        HashMap<Integer, List<TrainDTO1>> hashMap = sendTrainDTO1();
        for (Map.Entry<Integer, List<TrainDTO1>> entry : hashMap.entrySet()) {
            Integer key = entry.getKey();
            List<TrainDTO1> trains = entry.getValue();

            System.out.println("Key (some integer): " + key);
            System.out.println("Trains:");

            for (TrainDTO1 train : trains) {
                System.out.println("  Train Train : " + train.getTrain_number());
                System.out.println("  Train Booking-Type : " + train.getBooking_type());
                System.out.println("  Train Coach : " + train.getCoach_name());
                System.out.println("  Train Station : " + train.getStation_name());
                System.out.println("  Total No Of Tickets : " + train.getTotal_no_of_seats());
                System.out.println("  Each Seat Price : " + train.getEach_seat_price());
                System.out.println("  ---------------------------");
            }

            System.out.println("================================");
        }
    }

    public ResponseEntity<String> AddOneTrain(TrainDetails trainDetails) {
        logger.info("Enter into the method");
        List<TrainStoppingStation> trainStoppingStation = trainDetails.getTrainStoppingStations();
        List<TrainCoaches> trainCoaches = trainDetails.getTrainCoachesList();
        ListIterator<TrainCoaches> list = trainDetails.getTrainCoachesList().listIterator();
        trainDetails.setNo_of_stoppingstations(trainDetails.getTrainStoppingStations().size());
        trainDetails.setFrom_station(trainStoppingStation.getFirst().getStation_name());
        LocalDate date = trainStoppingStation.getFirst().getDeparture_date_time().toLocalDate();
        trainDetails.setFromStationDepartureDate(date);
        trainDetails.setDestination_station(trainDetails.getTrainStoppingStations().getLast().getStation_name());
        List<String> bookingtype = List.of("Tatkal", "Premium Tatkal", "Normal Reservation");
        addTicketsToEachStations2(trainDetails.getTrain_number(), trainStoppingStation, trainCoaches, bookingtype);
        trainDetailsRepo.save(trainDetails);
        return new ResponseEntity<>("One Train Added", HttpStatus.CREATED);
    }

    //Third method written to add Number of tickets to each station by booking type (Method Successfully)
    private void addTicketsToEachStations2(int train_number, List<TrainStoppingStation> trainStoppingStation, List<TrainCoaches> trainCoaches, List<String> bookingtype) {
        int totalStations = trainStoppingStation.size();
        for (int i = 0; i < totalStations; i++) {
            TrainStoppingStation station = trainStoppingStation.get(i);
            station.setTrain_number(train_number);
            List<TicketsPerStation> list = new ArrayList<>();
            for (TrainCoaches trainCoach : trainCoaches) {
                Integer ticketsDividedForEachBookingType = trainCoach.getTotal_no_seats() / 3;
                int divideTicketsPerStation = ticketsDividedForEachBookingType / totalStations;
                int remaining_tickets = ticketsDividedForEachBookingType % totalStations;
                for (String bookingname : bookingtype) {
                    TicketsPerStation ticketsPerStations = new TicketsPerStation(station.getStation_name(), bookingname, trainCoach.getCoach_name(), divideTicketsPerStation, trainCoach.getEach_seat_price());
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

    public void bookTicket(Integer trainNumber, String coach, List<String> passengers_name, Integer noOfTickets, Integer amount, String bookingType, String date, String fromstation, String destination) {
        TrainDetails trainDetails = trainDetailsRepo.findByTrain_Number(trainNumber);
        if (trainDetails != null) {
            logger.info("trainDetails:");
        } else {
            System.out.println("Throw Exception");
            return;
        }
        ListIterator<TrainStoppingStation> list1 = trainDetails.getTrainStoppingStations().listIterator();
        boolean check = checkTrainStationFromToDestination.checkTainFromToDestination(list1, fromstation, destination);
        ListIterator<TrainCoaches> listIterator = trainDetails.getTrainCoachesList().listIterator();
        Ticket ticket = null;
        TrainCoaches trainCoaches = null;
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
        Integer availabletickets = ticket.getAvailable_Tickets();
        boolean checkavailabletickets = validTicket.checkTicketAvailability(noOfTickets, availabletickets);
        if (check && checkavailabletickets) {
            Booking booking = bookingTypeCheck(bookingType);
            booking.book(trainDetails, coach, noOfTickets, amount, bookingType, date, fromstation, destination);
        }

        System.out.println(trainCoaches.getCoach_name());
    }

    Booking bookingTypeCheck(String bookingType) {
        if (bookingType.equalsIgnoreCase("tatkal")) {
            return new TatkalService();
        } else if (bookingType.equalsIgnoreCase("GR")) {
            return new GeneralReservationService();
        }
        return null;
    }

    public void testingSendTrainDTO() {

    }

//    public void testingSendTrainDTO() {
//        sendTrainDTO();
//    }


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
