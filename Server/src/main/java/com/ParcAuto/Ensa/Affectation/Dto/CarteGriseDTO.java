package com.ParcAuto.Ensa.Affectation.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class CarteGriseDTO {

    private Long id;
    private String numImmatriculation;
    private String immAnterieure;
    private Date premiereMiseCirculation;
    private Date mcMaroc;
    private Date mutation;
    private String usageCG;
    private String proprietaire;
    private String adresse;
    private Date finValidite;
    private Long vehiculeId; // Corresponding foreign key

}
