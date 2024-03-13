package com.ParcAuto.Ensa.Affectation.Dto;

import com.ParcAuto.Ensa.Affectation.Entities.Permis;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConducteurDTO {
    private String matricule;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String cin;
    private PermisDTO permis;
}

