package com.ParcAuto.Ensa.Affectation.Dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PermisDTO {
    private String numeroPermis;
    private LocalDate dateRemisePermis;
    private String typePermis;
}
