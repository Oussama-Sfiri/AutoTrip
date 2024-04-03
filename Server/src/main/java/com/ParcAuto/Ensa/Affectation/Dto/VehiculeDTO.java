package com.ParcAuto.Ensa.Affectation.Dto;

import com.ParcAuto.Ensa.Affectation.Entities.PermisType;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehiculeDTO {

    private Long id;
    private String marque;
    private String type;
    private int kilemotrage;
    private PermisType typePermisRequis;
    private String assurance;
    private LocalDate visiteTech;
    private LocalDate vignette;
    private boolean disponibilite;
    private CarteGriseDTO carteGrise;

}
