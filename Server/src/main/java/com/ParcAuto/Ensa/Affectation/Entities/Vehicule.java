package com.ParcAuto.Ensa.Affectation.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Vehicule {

    @Id
    private String immatriculation;
    private String marque;
    private String modele;
    private String type;
    private double kilometrage;
    private String typePermisRequis;
    private String equipementsSpeciaux;

    @OneToMany(targetEntity = Voyage.class ,mappedBy = "vehicule")
    private List<Voyage> voyages;

    @OneToOne(mappedBy = "vehicule")
    private CarteGrise carteGrise;
    @Override
    public String toString() {
        return "Vehicule{" +
                "immatriculation='" + immatriculation + '\'' +
                ", marque='" + marque + '\'' +
                ", modele='" + modele + '\'' +
                ", type='" + type + '\'' +
                ", kilometrage=" + kilometrage +
                ", typePermisRequis='" + typePermisRequis + '\'' +
                ", equipementsSpeciaux='" + equipementsSpeciaux + '\'' +
                '}';
    }

}

