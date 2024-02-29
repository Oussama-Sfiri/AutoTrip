package com.ParcAuto.Ensa.Affectation.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String immatriculation;
    private String marque;
    private String modele;
    private String type;
    private double kilometrage;
    private boolean disponible;
    private String typePermisRequis;
    private String equipementsSpeciaux;

    @OneToMany(mappedBy = "vehicule")
    private List<Voyage> voyages;
}

