package Indian_Railway.Indian_Railway.Repository;

import Indian_Railway.Indian_Railway.Entity.TrainDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainDetailsRepo extends JpaRepository<TrainDetails,Integer> {

    @Query(value = "select r from TrainDetails r where r.train_number = ?1")
    TrainDetails findByTrain_Number(int trainNumber);

    @Query(value = "select * from train_details r where r.train_name =:Name",nativeQuery = true)
    TrainDetails findbytrain_name(@Param("Name") String trainname);



//    List<TrainDetails> findByStartingPointIgnoreCase(String startingpoint);

  /*  @Transactional
    @Modifying
    @Query(value = "update traindetails set no_of_stations = :Value where train_number = :train_number",nativeQuery = true)
    TrainDetails savenoofstaion_by_trainnumber(@Param("Value") int val,@Param("train_number") int trainnumber);*/

//    @Transactional
//    @Modifying
//    @Query(value = "update train_details set no_ac_tickets = ?2 where train_number = ?1",nativeQuery = true)
//    void updateTrainTicket(int trainnumber,int no_of_tickets);
}
