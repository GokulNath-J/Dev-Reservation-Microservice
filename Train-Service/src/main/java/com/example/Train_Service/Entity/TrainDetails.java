package com.example.Train_Service.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TrainDetails {

    @Id
    @SequenceGenerator(name = "traindetailsseq", sequenceName = "seqtraindetails", allocationSize = 1, initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "traindetailsseq")
    private Integer id;
    @Column(unique = true)
    private Integer train_number;
    private String train_name;
    private String from_station;
    private String destination_station;
    private Integer no_of_stoppingstations;
    @Column(name = "from_station_departure_date")
    private LocalDate fromStationDepartureDate;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "train_number", referencedColumnName = "train_number")
    private List<TrainStoppingStation> trainStoppingStations;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "train_number", referencedColumnName = "train_number")
    private List<TrainCoaches> trainCoachesList;

    @ElementCollection(targetClass = TrainRunningDays.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "train_running_days", joinColumns = @JoinColumn(name = "train_number", referencedColumnName = "train_number"))
    private Set<TrainRunningDays> trainRunningDays;
}
