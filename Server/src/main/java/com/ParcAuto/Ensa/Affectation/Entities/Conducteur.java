package com.ParcAuto.Ensa.Affectation.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Conducteur {
    @Id
    private String matricule;

    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String cin;


    @OneToMany(mappedBy = "conducteur")
    private List<Voyage> voyages;

    @OneToOne
    private Permis permis;

}
