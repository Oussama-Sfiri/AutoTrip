package com.ParcAuto.Ensa.Affectation.Entities;

import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;

public class Permis {

    @Id
    private String numeroPermis;
    private LocalDate dateRemisePermis;
    private String typePermis;

    @OneToOne
    private Conducteur conducteur;

}