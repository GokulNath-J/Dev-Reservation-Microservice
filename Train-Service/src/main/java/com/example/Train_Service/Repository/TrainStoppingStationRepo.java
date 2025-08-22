package com.example.Train_Service.Repository;
import com.example.Train_Service.Entity.TrainStoppingStation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainStoppingStationRepo extends JpaRepository<TrainStoppingStation,Integer> {

}
