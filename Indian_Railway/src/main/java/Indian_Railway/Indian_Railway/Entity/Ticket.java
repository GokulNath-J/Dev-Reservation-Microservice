package Indian_Railway.Indian_Railway.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    @SequenceGenerator(name = "seqticket",sequenceName = "seqtrainticket",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqticket")
    private int id;
    @Column(unique = true)
    private String ticket_id;
    private Integer total_no_Tickets;
    private Integer available_Tickets;
    private Double each_seat_price;

}
