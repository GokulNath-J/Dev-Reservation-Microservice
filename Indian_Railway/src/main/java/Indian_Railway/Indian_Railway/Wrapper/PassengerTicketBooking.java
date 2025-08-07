package Indian_Railway.Indian_Railway.Wrapper;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerTicketBooking {


    private Integer train_number;
    private String train_name;
    private String no_of_tickets;
    private String starting_point;
    private String destination;
    private LocalDateTime reserved_on_datetime;
    private String ticket_conformation;


}
