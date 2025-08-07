package Indian_Railway.Indian_Railway.Repository;


import Indian_Railway.Indian_Railway.Entity.TrainCoaches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainCoachesRepo extends JpaRepository<TrainCoaches,Integer>  {
}
