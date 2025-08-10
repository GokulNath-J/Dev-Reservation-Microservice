package Indian_Railway.Indian_Railway.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TrainStoppingStation {

    @Id
    @SequenceGenerator(name = "Seqstoppingstation",sequenceName = "seqtrainstoppingstation",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Seqstoppingstation")
    private Integer id;
    private Integer train_number;
    private String station_name;
    private Integer platform_no;
    private LocalDateTime departure_date_time;
    private LocalDateTime arrival_date_time;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "StationDetailsID")
//    private StationDetails stationDetails;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "station_name",referencedColumnName = "station_name")
    private List<TicketsPerStation> ticketsPerStations;
}


