package com.ParcAuto.Ensa.Affectation.Dto;

import com.ParcAuto.Ensa.Affectation.Entities.Conducteur;
import com.ParcAuto.Ensa.Affectation.Entities.Vehicule;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VoyageDTO {
    private Long id;
    private LocalDate dateDepart;
    private LocalTime heureDepart;
    private LocalDate dateArriveePrev;
    private LocalTime heureArriveePrev;
    private String depart;
    private String destination;
    private String typeVehicule;
    private int nombrePassagers;
    private String autresDetails;
    private Boolean status;
    private ConducteurDTO conducteur;
    private VehiculeDTO vehicule;
}

