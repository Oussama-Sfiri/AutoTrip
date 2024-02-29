package com.ParcAuto.Ensa.Affectation.Dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ConducteurDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String matricule;
    private LocalDate dateNaissance;
    private String cin;
    private String numeroPermis;
    private LocalDate dateRemisePermis;
    private String typePermis;
}

