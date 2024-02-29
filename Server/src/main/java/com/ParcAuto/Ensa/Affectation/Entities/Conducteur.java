package com.ParcAuto.Ensa.Affectation.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Conducteur {

    @Id
    private String matricule;

    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String cin;

    @OneToMany(mappedBy = "conducteur", cascade = CascadeType.ALL)
    private List<Voyage> voyages;

    @OneToOne(mappedBy = "conducteur", cascade = CascadeType.ALL)
    private Permis permis;


}
