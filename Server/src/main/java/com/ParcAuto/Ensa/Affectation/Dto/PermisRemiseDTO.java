package com.ParcAuto.Ensa.Affectation.Dto;

import com.ParcAuto.Ensa.Affectation.Entities.PermisRemise;
import com.ParcAuto.Ensa.Affectation.Entities.PermisType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PermisRemiseDTO {

    private Long id;
    private String type;
    private Date dateRemisePermis;
    private String lieuRemisePermis;
    private Long permisId; // Corresponding foreign key
    private Long driverId; // Corresponding foreign key

}