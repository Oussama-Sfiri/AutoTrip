package com.ParcAuto.Ensa.Affectation.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PermisDTO {
    private Long id;
    private Long numPermis;
    private Date fin_validite;
    private List<PermisRemiseDTO> permisRemises;
}