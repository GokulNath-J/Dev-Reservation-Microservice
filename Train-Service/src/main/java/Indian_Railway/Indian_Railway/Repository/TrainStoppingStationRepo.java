package Indian_Railway.Indian_Railway.Repository;

import Indian_Railway.Indian_Railway.Entity.TrainStoppingStation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainStoppingStationRepo extends JpaRepository<TrainStoppingStation,Integer> {

}
