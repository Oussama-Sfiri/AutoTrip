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
    private PermisType type;
    private Date date_remise_permis;
    private String lieu_remise_permis;
    private Long numPermis;
    private String cin;
}