package com.ParcAuto.Ensa.Affectation.Dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
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

