package com.ParcAuto.Ensa.Affectation.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Conducteur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nom;
    private String prenom;
    private String matricule;
    private LocalDate dateNaissance;
    private String cin;
    private String numeroPermis;
    private LocalDate dateRemisePermis;
    private String typePermis;

    @OneToMany(mappedBy = "conducteur")
    private List<Voyage> voyages;}
