package com.ParcAuto.Ensa.Affectation.Dto;

import jakarta.annotation.Nullable;
import lombok.*;

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
    private String typePermisRequis;
    private String assurance;
    private int visiteTech;
    private int vignette;
    private boolean disponibilite;
    private Long carteGriseId;

}
