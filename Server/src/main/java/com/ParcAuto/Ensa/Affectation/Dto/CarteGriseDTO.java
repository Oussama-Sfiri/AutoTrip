package com.ParcAuto.Ensa.Affectation.Dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class CarteGriseDTO {

    private Long id;
    private String num_Immatriculation;
    private String imm_anterieure;
    private LocalDate premiere_mise_Circulation;
    private LocalDate mc_maroc;
    private LocalDate mutation;
    private String usageCG;
    private String proprietaire;
    private String adresse;
    private LocalDate fin_validite;

}
