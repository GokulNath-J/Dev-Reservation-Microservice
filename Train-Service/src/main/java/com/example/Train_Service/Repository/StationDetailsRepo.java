package com.example.Train_Service.Repository;


import com.example.Train_Service.Entity.StationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationDetailsRepo extends JpaRepository<StationDetails,Integer> {
}
