package com.ParcAuto.Ensa.Affectation.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class CarteGrise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Vehicule vehicule;


   private LocalDate premiereMiseCirculation ;
   private String Adresse ;
   private String Type;
   private int NobmreDePlace;

}
