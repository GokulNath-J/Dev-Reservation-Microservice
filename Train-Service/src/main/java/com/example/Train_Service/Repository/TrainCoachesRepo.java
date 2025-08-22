package com.example.Train_Service.Repository;

import com.example.Train_Service.Entity.TrainCoaches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainCoachesRepo extends JpaRepository<TrainCoaches,Integer>  {
}
