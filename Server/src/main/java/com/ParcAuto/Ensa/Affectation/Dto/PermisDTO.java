package com.ParcAuto.Ensa.Affectation.Dto;

import com.ParcAuto.Ensa.Affectation.Entities.Conducteur;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PermisDTO {
    private String numeroPermis;
    private LocalDate dateRemisePermis;
    private String typePermis;
}
