package com.ParcAuto.Ensa.Affectation.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;

@Entity
public class Permis {
    @Id
    private String numeroPermis;
    private LocalDate dateRemisePermis;
    private String typePermis;

    @OneToOne
    private Conducteur conducteur;
}
