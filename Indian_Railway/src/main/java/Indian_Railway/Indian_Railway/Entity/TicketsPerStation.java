package Indian_Railway.Indian_Railway.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketsPerStation {

    @Id
    @SequenceGenerator(name = "ticketsperstation", sequenceName = "seqticketsperstation", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticketsperstation")
    private Integer sNo;
    private String booking_type;
    private Integer train_number;
    private String coach_name;
    private Integer total_no_of_seats;
    private Double each_seat_price;

    public TicketsPerStation(String booking_type, Integer train_number, String coach_name, Integer total_no_of_seats, Double each_seat_price) {
        this.booking_type = booking_type;
        this.train_number = train_number;
        this.coach_name = coach_name;
        this.total_no_of_seats = total_no_of_seats;
        this.each_seat_price = each_seat_price;
    }
}
