//package com.example.Train_Service.KafkaEvents;
//
//
//import com.example.Train_Service.DTO.TrainDTO;
//import com.example.Train_Service.DTO.TrainDTO1;
//import com.example.Train_Service.DTO.TrainDTOWrapper;
//import com.example.Train_Service.Service.ServiceClass;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.HashMap;
//import java.util.List;
//
//@Component
//@AllArgsConstructor
//public class TrainSchedulerForBookings {
//
////    private final KafkaTemplate<String, HashMap<Integer, List<TrainDTO>>> kafkaTemplate;
////    private final KafkaTemplate<String, HashMap<Integer, List<TrainDTO1>>> kafkaTemplate1;
//    private final KafkaTemplate<String, TrainDTOWrapper> kafkaTemplate2;
//
////    private final KafkaTemplate<String,String> kafkaTemplate3;
//
//    @Autowired
//    private ServiceClass serviceClass;
//
////    public String sendTrainDTO(){
////        kafkaTemplate.send("TrainDTO-Topic-1",serviceClass.sendTrainDTO());
////        return "TrainDTO Sent Sucessfully";
////    }
//
////    @Scheduled(cron = "10 11 * * *" )
////    public String sendTrainDTO1(){
////        kafkaTemplate1.send("TrainDTO-Topic-1",serviceClass.sendTrainDTO1());
////        return "TrainDTO Sent Sucessfully";
////    }
//    @Transactional(readOnly = true)
//    @Scheduled(cron = "0 10 19 * * *" )
//    public String sendTrainDTO1(){
//        TrainDTOWrapper trainDTOWrapper = new TrainDTOWrapper(serviceClass.sendTrainDTO1());
//        kafkaTemplate2.send("TrainDTO-Topic-1",trainDTOWrapper);
//        System.out.println("TrainDTO Sent Sucessfully");
//        return "TrainDTO Sent Sucessfully";
//    }
//
////    @Scheduled(cron = "0 43 15 * * *" )
////    public void sendmessage(){
////        kafkaTemplate3.send("TrainDTO-Topic-1","Welcome Message");
////        System.out.println("Message Sent Sucessfully");
////    }
//}
