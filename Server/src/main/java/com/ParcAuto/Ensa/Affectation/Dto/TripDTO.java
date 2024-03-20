package com.ParcAuto.Ensa.Affectation.Dto;

import com.ParcAuto.Ensa.Affectation.Entities.VehiculeType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Time;
import java.util.Date;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripDTO {

    private Long id;
    private String departure;
    private String destination;
    private Date departureDate;
    private Time departureTime;
    private Date arrivalDate;
    private Time arrivalTime;
    private int nbrOfPassengers;
    private String statusConfirmation;
    private VehiculeType vehiculType;
    private DriverDTO driver;

}