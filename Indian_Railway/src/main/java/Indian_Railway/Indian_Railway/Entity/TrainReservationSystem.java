package Indian_Railway.Indian_Railway.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainReservationSystem {

    @Id
    @SequenceGenerator(name = "trainreservationseq",sequenceName = "seqtrainreservation", allocationSize = 1,initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "trainreservationseq")
    private Integer id;
    private Integer train_number;
    private Integer no_ac_coach_tickets;
    private Integer no_sleeper_coach_tickets;
    private Integer no_nonac_reservation_tickets;
    private Integer total_no_reservation_tickets;
    private LocalDateTime reservation_opens_at;
    private LocalDateTime reservation_closes_at;
}
