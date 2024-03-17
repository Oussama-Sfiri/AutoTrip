package com.ParcAuto.Ensa.Affectation.mappers;

import com.ParcAuto.Ensa.Affectation.Dto.PermisDTO;
import com.ParcAuto.Ensa.Affectation.Dto.PermisRemiseDTO;
import com.ParcAuto.Ensa.Affectation.Entities.Permis;
import com.ParcAuto.Ensa.Affectation.Entities.PermisRemise;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.ParcAuto.Ensa.Affectation.mappers.PermisRemiseMappers.DTOToPermisRemise;

public class PermisMappers {

    public static PermisDTO PermisToDTO(Permis permis) {
        PermisDTO permisDTO = new PermisDTO();
        permisDTO.setId(permis.getId());
        permisDTO.setNumPermis(permis.getNumPermis());
        permisDTO.setFin_validite(permis.getFin_validite());
        if (permis.getPermisRemises() != null) {
            List<PermisRemiseDTO> permisRemisesDTO = permis.getPermisRemises().stream()
                    .map(PermisRemiseMappers::PermisRemiseToDTO)
                    .collect(Collectors.toList());
            permisDTO.setPermisRemises(permisRemisesDTO);
        }

        return permisDTO;
    }




    public static Permis DTOToPermis(PermisDTO permisDTO) {
        Permis permis = new Permis();
        permis.setNumPermis(permisDTO.getNumPermis());
        permis.setFin_validite(permisDTO.getFin_validite());
        return permis;
    }

}





