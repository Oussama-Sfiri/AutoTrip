package com.ParcAuto.Ensa.Affectation.Dto;

import lombok.Data;

@Data
public class VehiculeDTO {
    private Long id;
    private String immatriculation;
    private String marque;
    private String modele;
    private String type;
    private double kilometrage;
    private boolean disponible;
    private String typePermisRequis;
    private String equipementsSpeciaux;


    public String getModelName() {
        return null;
    }
}

