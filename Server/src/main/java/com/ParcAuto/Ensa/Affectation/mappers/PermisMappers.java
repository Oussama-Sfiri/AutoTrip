package com.ParcAuto.Ensa.Affectation.mappers;

import com.ParcAuto.Ensa.Affectation.Dto.PermisDTO;
import com.ParcAuto.Ensa.Affectation.Dto.PermisRemiseDTO;
import com.ParcAuto.Ensa.Affectation.Entities.Permis;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class PermisMappers {

    public static PermisDTO PermisToDTO(Permis permis) {
        PermisDTO permisDTO = new PermisDTO();
        BeanUtils.copyProperties(permis, permisDTO);
        // Additional mapping if needed
        return permisDTO;
    }

    public static PermisDTO PermisToDTOWithRemise(Permis permis, PermisRemiseDTO permisRemiseDTO) {
        PermisDTO permisDTO = PermisToDTO(permis);

        // Map fields from PermisRemiseDTO to PermisDTO
        List<String> types = new ArrayList<>();
        types.add(permisRemiseDTO.getType());
        permisDTO.setTypes(types);
        permisDTO.setDate_remise_permis(permisRemiseDTO.getDate_remise_permis());
        permisDTO.setLieu_remise_permis(permisRemiseDTO.getLieu_remise_permis());

        return permisDTO;
    }

    public static Permis DTOToPermis(PermisDTO permisDTO) {
        Permis permis = new Permis();
        BeanUtils.copyProperties(permisDTO, permis);

        // Additional mapping if needed
        return permis;
    }



}
