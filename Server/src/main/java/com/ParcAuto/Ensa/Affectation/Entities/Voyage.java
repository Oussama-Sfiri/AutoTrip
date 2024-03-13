package com.ParcAuto.Ensa.Affectation.Entities;

import com.ParcAuto.Ensa.Affectation.Dto.ConducteurDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class  Voyage {
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "conducteur_id")
    @ToString.Exclude
    private Conducteur conducteur;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vehicule_id")
    @ToString.Exclude
    private Vehicule vehicule;

    @Override
    public String toString() {
        return "Voyage{" +
                "id=" + id +
                ", dateDepart=" + dateDepart +
                ", heureDepart=" + heureDepart +
                ", dateArriveePrev=" + dateArriveePrev +
                ", heureArriveePrev=" + heureArriveePrev +
                ", depart='" + depart + '\'' +
                ", destination='" + destination + '\'' +
                ", typeVehicule='" + typeVehicule + '\'' +
                ", nombrePassagers=" + nombrePassagers +
                ", autresDetails='" + autresDetails + '\'' +
                ", status=" + status +
                // Exclude Conducteur and Vehicule to avoid infinite loop
                '}';
    }

}
