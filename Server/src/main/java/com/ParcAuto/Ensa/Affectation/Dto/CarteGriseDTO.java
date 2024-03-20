package com.ParcAuto.Ensa.Affectation.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class CarteGriseDTO {

    private Long id;
    private String num_Immatriculation;
    private String imm_anterieure;
    private Date premiere_mise_Circulation;
    private Date mc_maroc;
    private Date mutation;
    private String usageCG;
    private String proprietaire;
    private String adresse;
    private Date fin_validite;

}
