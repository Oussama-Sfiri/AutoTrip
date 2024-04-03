package com.ParcAuto.Ensa.Affectation.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacationDTO {

    private Long id;
    private LocalDate start;
    private LocalDate end;
    private Long driverId;
}
