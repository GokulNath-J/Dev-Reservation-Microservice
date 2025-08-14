package Indian_Railway.Indian_Railway.Wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketCheckingWrapper {

    private Integer train_number;
    private String train_name;
    private Integer no_ac_tickets;
    private Integer no_sleeper_tickets;
    private Integer no_general_reserve_tickets;
    private LocalDateTime reservation_opens_at;
    private LocalDateTime reservation_closes_at;
}
