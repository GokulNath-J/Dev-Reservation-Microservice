package Indian_Railway.Indian_Railway.Entity;

import Indian_Railway.Indian_Railway.Repository.TrainDetailsRepo;
import Indian_Railway.Indian_Railway.Repository.StationDetailsRepo;
import Indian_Railway.Indian_Railway.Repository.TrainStoppingStationRepo;
import Indian_Railway.Indian_Railway.Service.ServiceClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/*
@SpringBootTest
class TrainDetailsTest {

    @Autowired
    private ServiceClass serviceClass;

    @Autowired
    private TrainDetailsRepo trainDetailsRepo;

    @Autowired
    private StationDetailsRepo stationDetailsRepo;

    @Autowired
    private TrainStoppingStationRepo trainStoppingStationRepo;


    @Test
    public void add_train_withstation(){
      TrainStoppingStation Bangalore = TrainStoppingStation.builder()
              .station_name("Bangalore")
              .platform_no(1)
              .build();
      TrainStoppingStation Katpadi= TrainStoppingStation.builder()
                .station_name("Katpadi")
                .platform_no(2)
                .build();
      TrainStoppingStation Arakonam= TrainStoppingStation.builder()
                .station_name("Arakonam")
                .platform_no(4)
                .build();
      TrainStoppingStation ChennaiCentral= TrainStoppingStation.builder()
                .station_name("ChennaiCentral")
                .platform_no(11)
                .build();
      trainStoppingStationRepo.saveAll(List.of(Bangalore,Katpadi,Arakonam,ChennaiCentral));
      TrainDetails BrindavanExpress = TrainDetails.builder()
              .train_name("BrindavanExpress")
              .no_of_tickes(100)
              .train_number(12607)
              .starting_point("Bangalore")
              .destination("ChennaiCentral")
              .trainStoppingStations(List.of(Bangalore,Katpadi,Arakonam,ChennaiCentral))
              .build();
      trainDetailsRepo.save(BrindavanExpress);
       */
/* TrainDetails trainDetails = trainDetailsRepo.findByTrain_Number(12607);
        int no_of_stations = trainDetails.getTrainStoppingStations().size();
        trainDetails.setNo_of_stations(no_of_stations);
//      trainDetailsRepo.savenoofstaion_by_trainnumber()*//*

    }
    @Test
    public void add_noofstaions_in_traindetails(int train_number){
        TrainDetails trainDetails = trainDetailsRepo.findByTrain_Number(train_number);
        int no_of_stations = trainDetails.getTrainStoppingStations().size();
        trainDetails.setNo_of_stations(no_of_stations);


}

    */
/*@Test
    public int No_of_stops(int train_number){
        TrainDetails trainDetails = repo.findByTrain_Number(train_number);
        List<TrainStoppingStation> stopping_list = trainDetails.getTrainStoppingStations();
        int no_of_stops = stopping_list.size();
        return no_of_stops;
    }*//*

}*/
