package com.ParcAuto.Ensa.Affectation.Dto;

import com.ParcAuto.Ensa.Affectation.Entities.VehiculeType;
import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripDTO {

    private Long id;
    private String departure;
    private String destination;
    private LocalDate departureDate;
    private Time departureTime;
    private LocalDate arrivalDate;
    private Time arrivalTime;
    private int nbrOfPassengers;
    private String statusConfirmation;
    private VehiculeType vehiculType;
    private DriverDTO driver;
    private VehiculeDTO vehicule;

}