package Indian_Railway.Indian_Railway.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainCoaches {

    @Id
    @SequenceGenerator(name = "seqcoaches",sequenceName = "seqtrainCoaches",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqcoaches")
    private int id;
    private String coach_name;
    private Integer total_no_of_coaches;
//    private Integer total_no_seats;
//    private Double each_seat_price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_id",referencedColumnName = "ticket_id")
    private Ticket ticket;

}

