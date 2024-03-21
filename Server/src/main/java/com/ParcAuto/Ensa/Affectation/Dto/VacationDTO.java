package com.ParcAuto.Ensa.Affectation.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacationDTO {

    private Long id;
    private Date start;
    private Date end;
    private Long driverId;
}
