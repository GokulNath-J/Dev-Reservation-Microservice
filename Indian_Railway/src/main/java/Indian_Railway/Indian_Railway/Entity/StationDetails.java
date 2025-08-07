package Indian_Railway.Indian_Railway.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StationDetails {

    @Id
    @SequenceGenerator(name = "seqstation",sequenceName = "seqstationdetails",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqstation")
    private Integer id;
    private String stationcdode;
    private String stationname;
    private Integer noofplatform;


}
