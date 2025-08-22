package com.example.Train_Service.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TrainDTO {

    private Integer train_number;
    private String booking_type;
    List<TicketsPerStationDTO> ticketsPerStationDTOList;
}
