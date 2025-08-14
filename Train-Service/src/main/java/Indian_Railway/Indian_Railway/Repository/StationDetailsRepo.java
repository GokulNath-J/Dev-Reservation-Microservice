package Indian_Railway.Indian_Railway.Repository;

import Indian_Railway.Indian_Railway.Entity.StationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationDetailsRepo extends JpaRepository<StationDetails,Integer> {
}
