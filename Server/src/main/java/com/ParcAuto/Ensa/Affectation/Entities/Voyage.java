package com.ParcAuto.Ensa.Affectation.Entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class Voyage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateDepart;
    private LocalTime heureDepart;
    private LocalDate dateArriveePrev;
    private LocalTime heureArriveePrev;
    private String depart;
    private String destination;
    private String typeVehicule;
    private int nombrePassagers;
    private String autresDetails;

    @Column(name = "status", columnDefinition = "boolean default false")
    private Boolean status;



    @ManyToOne
    @JoinColumn(name = "conducteur_id")
    private Conducteur conducteur;

    @ManyToOne
    @JoinColumn(name = "vehicule_id")
    private Vehicule vehicule;
}
