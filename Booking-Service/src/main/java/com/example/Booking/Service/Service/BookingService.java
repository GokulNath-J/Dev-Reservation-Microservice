package com.example.Booking.Service.Service;


import com.example.Booking.Service.DTO.BookingRequest;
import com.example.Booking.Service.DTO.TicketPrice;
import com.example.Booking.Service.DTO.TrainDTO1;
import com.example.Booking.Service.DTO.TrainDTOWrapper;
import com.example.Booking.Service.Entity.PremiumTatkalTickets;
import com.example.Booking.Service.Entity.TatkalTickets;
import com.example.Booking.Service.Feign.TrainFeign;
import com.example.Booking.Service.Repository.NormalReservationRepo;
import com.example.Booking.Service.Repository.PremiumTatkalRepo;
import com.example.Booking.Service.Repository.TatkalRepo;
import com.example.Booking.Service.Repository.TicketPriceRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookingService {

     private final static Logger log = LoggerFactory.getLogger(BookingService.class);

    private TatkalRepo tatkalRepo;
    private PremiumTatkalRepo premiumTatkalRepo;
    private NormalReservationRepo normalReservationRepo;


    @Autowired
    public BookingService(TatkalRepo tatkalRepo, PremiumTatkalRepo premiumTatkalRepo, NormalReservationRepo normalReservationRepo) {
        this.tatkalRepo = tatkalRepo;
        this.premiumTatkalRepo = premiumTatkalRepo;
        this.normalReservationRepo = normalReservationRepo;
    }
//    @Autowired
//    private Booking booking;

    @Autowired
    private TicketPriceRepo ticketPriceRepo;

    @Autowired
    private TrainFeign trainFeign;

    @Autowired
    private TatkalService tatkalService;

    public void addTatkalAndPremiumTatkatTickets(HashMap<Integer, List<TrainDTO1>> trainDTO1) {
        List<TatkalTickets> tatkalList = new ArrayList<>();
        List<PremiumTatkalTickets> premiunTataklList = new ArrayList<>();
        for (Map.Entry<Integer, List<TrainDTO1>> listEntry : trainDTO1.entrySet()) {
            List<TrainDTO1> dto1List = listEntry.getValue();
            for (TrainDTO1 dto1 : dto1List) {
                if (dto1.getBooking_type().equalsIgnoreCase("Premium Tatkal")) {
                    PremiumTatkalTickets premiumTatkalTickets = new PremiumTatkalTickets(dto1.getTrain_number()
                            , dto1.getCoach_name(), dto1.getStation_name(), dto1.getBooking_type()
                            , dto1.getTotal_no_of_seats(), dto1.getTotal_no_of_seats()
                            , 0, dto1.getEach_seat_price());
                    premiunTataklList.add(premiumTatkalTickets);
                } else {
                    TatkalTickets tatkalTickets = new TatkalTickets(dto1.getTrain_number()
                            , dto1.getCoach_name(), dto1.getStation_name(), dto1.getBooking_type()
                            , dto1.getTotal_no_of_seats(), dto1.getTotal_no_of_seats()
                            , 0, dto1.getEach_seat_price());
                    tatkalList.add(tatkalTickets);
                }
            }
        }
        tatkalRepo.saveAll(tatkalList);
        premiumTatkalRepo.saveAll(premiunTataklList);
    }

        public String book(BookingRequest request){
        log.info("request in the BookingService");
            if(request.getBookingMethod().equalsIgnoreCase("Tatkal")) {
                tatkalService.book(request);
            }
        return "success";
        }


    public List<TatkalTickets> getAllTatkalTickets() {
        return tatkalRepo.findAll();
    }

    public List<PremiumTatkalTickets> getPremiumTatkalTickets() {
        return premiumTatkalRepo.findAll();
    }

    private void checkNormalReservationTicketsAvailability(BookingRequest request) {
    }

    private void checkPremiumTatkalTicketsAvailability(BookingRequest request) {
    }

//    public String book(BookingRequest request) {
//        log.info("request in the BookingService");
//        booking = bookingValidate(request.getBookingMethod());
//        String bookingMethod = request.getBookingMethod();
//        if (bookingMethod.equalsIgnoreCase("Tatkal")){
//            checkTatkalTicketsAvailability(request);
//        } else if (bookingMethod.equalsIgnoreCase("Premium Tatkal")) {
//            checkPremiumTatkalTicketsAvailability(request);
//        }else {
//            checkNormalReservationTicketsAvailability(request);
//        }
//        booking.book(request);
//        return "Failed";
//    }

//    private void checkTatkalTicketsAvailability(BookingRequest request) {
//        if (request.getTravelDate().equals(LocalDate.now().plusDays(1))) {
//            List<TatkalTickets> tatkalTickets = tatkalRepo.findAllByTrainNumber(request.getTrainNumber());
//            TatkalTickets tickets = tatkalTickets.stream().filter(
//                    tatkalTickets1 -> tatkalTickets1.getStationName().equalsIgnoreCase(request.getStationName())
//                            && tatkalTickets1.getCoachName().equalsIgnoreCase(request.getCoachName())).findFirst().orElse(null);
//            if (request.getNumberOfTickets() <= tickets.getNoOfSeatsAvailable()) {
//                log.info("Tickets Are Available");
//                if(calculateTotalAmount(request.getBookingMethod(),tickets.getEachSeatPrice(),request.getNumberOfTickets()
//                        ,request.getCoachName())){
//                    bookingServiceToPaymentService.bookTicket(tickets, request, totalTicketAmount);
//                }else {
//                    System.out.println("booking Cancelled");
//                }
//            }else {
//                System.out.println("Insufficient Tickets");
//            }
//        }else {
//            System.out.println("There is Not Train on this Date");
//        }
//    }
//    private boolean calculateTotalAmount(String bookingMethod, Double eachSeatPrice, int numberOfTickets, String coachName) {
//        TicketPrice ticketPrice = ticketPriceRepo.findByBookingTypeAndCoachName(bookingMethod,coachName);
//        Scanner scanner = new Scanner(System.in);
//        if (bookingMethod.equalsIgnoreCase("Tatkal")){
//            double totalTicketPrice =  (ticketPrice.getPrice()+eachSeatPrice)*numberOfTickets;
//            System.out.println("Total Tatakl TicketsPrice = "+ totalTicketPrice);
//            System.out.print("Do yo want to place booking Y/N.?:");
//            String yesOrno = scanner.nextLine();
//            if (yesOrno.equalsIgnoreCase("y")){
//                return true;
//            }
//        }else if (bookingMethod.equalsIgnoreCase("Premium tatkal")) {
//            double totalTicketPrice = (ticketPrice.getPrice() + eachSeatPrice) * numberOfTickets;
//            System.out.println("Total Tatakl TicketsPrice = "+ totalTicketPrice);
//            System.out.print("Do yo want to place booking Y/N.?:");
//            String yesOrno = scanner.nextLine();
//            if (yesOrno.equalsIgnoreCase("y")){
//                return true;
//            }
//            ticketPrice.setPrice(ticketPrice.getPrice() + 200);
//            ticketPriceRepo.save(ticketPrice);
//        }else {
//            double totalTicketPrice =  (ticketPrice.getPrice()+eachSeatPrice)*numberOfTickets;
//            System.out.println("Total Tatakl TicketsPrice = "+ totalTicketPrice);
//            return true;
//        }
//        return false;
//        }

//
//    private Booking bookingValidate(String bookingMethod) {
//        if(bookingMethod.equalsIgnoreCase("Tatkal")){
//            return new TatkalService();
//        }else if(bookingMethod.equalsIgnoreCase("Premium Tatkal")){
//            return new PremiumTatkalService();
//        }else{
//            return new NormalReservationService();
//        }
//    }

    public HashMap<Integer, List<TrainDTO1>> getTrainDTOWrapperManually() {
        HashMap<Integer, List<TrainDTO1>> trainDTO1  = trainFeign.sendTrainDTOToBookingServiceManually().getHashMap();
        addTatkalAndPremiumTatkatTickets(trainDTO1);
        return trainDTO1;
    }

    public List<TatkalTickets> getAllTatkalTicketsByTrainNumber(Integer trainNumber) {
        return tatkalRepo.findAllByTrainNumber(trainNumber);
    }

    public void addPrice(TicketPrice ticketPrice) {
        ticketPriceRepo.save(ticketPrice);
    }
}
