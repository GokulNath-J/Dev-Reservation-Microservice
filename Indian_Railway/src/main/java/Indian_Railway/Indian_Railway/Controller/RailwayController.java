package Indian_Railway.Indian_Railway.Controller;

import Indian_Railway.Indian_Railway.Entity.TrainDetails;
import Indian_Railway.Indian_Railway.Service.ServiceClass;
import Indian_Railway.Indian_Railway.Wrapper.PassengerTicketBooking;
import Indian_Railway.Indian_Railway.Wrapper.TicketCheckingWrapper;
import Indian_Railway.Indian_Railway.Wrapper.TrainWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rail")
public class RailwayController {

    private ServiceClass serviceClass;

    @Autowired
    public RailwayController(ServiceClass serviceClass) {
        this.serviceClass = serviceClass;
    }

   @GetMapping("/getall")
   public ResponseEntity<List<TrainDetails>> getall(){
        return serviceClass.GetAll();
   }

   @PostMapping("/addonetrain")
   public ResponseEntity<String> addoneRail(@RequestBody TrainDetails trainDetails){
        return serviceClass.AddOneTrain(trainDetails);
   }

//   @PostMapping("/addalltrain")
//   @ResponseStatus(HttpStatus.CREATED)
//   public String addallrail(@RequestBody List<TrainDetails> trainDetails){
//        serviceClass.AddAllRail(trainDetails);
//        return "All Rail Saved";
//   }
//
//    @GetMapping("/findbytrainnumberinrail/{train_Number}")
//    public ResponseEntity<TrainDetails> getdetailstrainbynumber(@PathVariable int train_Number){
//        return serviceClass.GetDetailsTrainByNumber(train_Number);
//    }
//
//    /*@GetMapping("/findbytrainname")
//    public ResponseEntity<RailwayNetwork> gettrainbyname(@RequestParam("name") String trainname){
//        return serviceClass.GetTrainbyName(trainname);
//
//    }*/
//
//    @GetMapping("/findtrainbynumber/{train_Number}")
//    public ResponseEntity<TrainWrapper> get_trainwrapper_bytrain_number(@PathVariable int train_Number){
//        return serviceClass.Get_TrainWrapper_ByTrainNumber(train_Number);
//    }
//
//    @GetMapping("/checkticketavailabiltiybytrainnumber/")
//    public ResponseEntity<TicketCheckingWrapper> Check_Allcoach_Ticket_Availability(@RequestParam int train_number){
//        return serviceClass.check_all_coach_ticket_availability(train_number);
//    }
//
//   @PutMapping("/ticket_booking/")
//   public ResponseEntity<PassengerTicketBooking> Ticket_Booking(@RequestParam int train_number, @RequestParam String coach,
//                                                                @RequestParam int no_of_tickets){
//        return serviceClass.ticket_booking(train_number,coach,no_of_tickets);
//   }
//
//   public String Delete_Train_TrainID(@RequestParam int train_number){
//        return serviceClass.delete_train_trainid(train_number);
//    }
//
//   @GetMapping("/Check_Train_From_Startinpoint")
//   public ResponseEntity<List<TrainWrapper>> Check_Train_From_Startinpoint(@RequestParam String startingpoint){
//        return serviceClass.check_train_from_startingpoint(startingpoint);
//   }

    @PostMapping("/book")
    public void bookTicket(@RequestParam Integer trainNumber, @RequestParam String coach,
                           @RequestParam Integer noOfTickets, @RequestParam Integer amount,
                           @RequestParam String bookingType, @RequestParam String date){
       serviceClass.bookTicket(trainNumber,coach,noOfTickets,amount,bookingType,date);
    }
}
