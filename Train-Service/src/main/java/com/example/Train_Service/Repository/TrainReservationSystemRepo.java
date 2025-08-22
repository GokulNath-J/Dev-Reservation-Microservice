package com.example.Train_Service.Repository;

import com.example.Train_Service.Entity.TrainReservationSystem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainReservationSystemRepo extends JpaRepository<TrainReservationSystem,Integer> {

    @Query(value = "select * from train_reservation_system  where train_number = ?1",nativeQuery = true)
    TrainReservationSystem findByTrain_Number(int trainNumber);

    @Transactional
    @Modifying
    @Query(value = "update train_reservation_system set no_ac_tickets = ?2 where train_number = ?1",nativeQuery = true)
    void updateAcTicket(int trainnumber, int no_of_tickets);

    @Transactional
    @Modifying
    @Query(value = "update train_reservation_system set no_sleeper_tickets = ?2 where train_number = ?1",nativeQuery = true)
    void updateSleeperTicket(int trainnumber, int no_of_tickets);

    @Transactional
    @Modifying
    @Query(value = "update train_reservation_system set no_general_reserve_tickets = ?2 where train_number = ?1",nativeQuery = true)
    void updateGeneralReserveTicket(int trainnumber, int no_of_tickets);


}
