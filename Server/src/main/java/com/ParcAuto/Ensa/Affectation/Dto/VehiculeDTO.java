package com.ParcAuto.Ensa.Affectation.Dto;

import com.ParcAuto.Ensa.Affectation.Entities.PermisType;
import jakarta.annotation.Nullable;
import lombok.*;

import java.util.Date;
import java.util.List;
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
    private Date visiteTech;
    private Date vignette;
    private boolean disponibilite;
    private CarteGriseDTO carteGrise;

}
