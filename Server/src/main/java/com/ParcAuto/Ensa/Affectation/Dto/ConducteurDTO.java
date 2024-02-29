package com.ParcAuto.Ensa.Affectation.Dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ConducteurDTO {
    private String matricule;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String cin;
}

