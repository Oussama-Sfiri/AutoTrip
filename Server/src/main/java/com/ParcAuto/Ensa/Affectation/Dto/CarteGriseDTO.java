package com.ParcAuto.Ensa.Affectation.Dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CarteGriseDTO {
    private LocalDate premiereMiseCirculation;
    private String adresse;
    private String type;
    private int nombreDePlaces;
}
