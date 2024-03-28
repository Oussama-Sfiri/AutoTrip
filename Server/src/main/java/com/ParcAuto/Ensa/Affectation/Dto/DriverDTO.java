package com.ParcAuto.Ensa.Affectation.Dto;

import lombok.*;

import java.util.Date;
import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class DriverDTO {

    private Long id;
    private String cin;
    private String nom;
    private String prenom;
    private Date date_naissance;
    private String addresse;
    private boolean disponibility;
    private PermisDTO permis;
    private List<VacationDTO> vacations;
    private List<TripDTO> trips;


}