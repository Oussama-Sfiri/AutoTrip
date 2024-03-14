package com.ParcAuto.Ensa.Affectation.mappers;

import com.ParcAuto.Ensa.Affectation.Dto.PermisRemiseDTO;
import com.ParcAuto.Ensa.Affectation.Entities.PermisRemise;
import com.ParcAuto.Ensa.Affectation.Entities.PermisType;
import org.springframework.beans.BeanUtils;

public class PermisRemiseMappers {
    public static PermisRemiseDTO PermisRemiseToDTO(PermisRemise permisRemise) {
        PermisRemiseDTO permisRemiseDTO = new PermisRemiseDTO();
        permisRemiseDTO.setId(permisRemise.getId());
        permisRemiseDTO.setType(permisRemise.getType().toString());
        permisRemiseDTO.setDate_remise_permis(permisRemise.getDate_remise_permis());
        permisRemiseDTO.setLieu_remise_permis(permisRemise.getLieu_remise_permis());
        permisRemiseDTO.setPermisId(permisRemise.getPermis().getId());
        permisRemiseDTO.setDriverId(permisRemise.getDriver().getId());
        return permisRemiseDTO;
    }

    public static PermisRemise DTOToPermisRemise(PermisRemiseDTO permisRemiseDTO) {
        PermisRemise permisRemise = new PermisRemise();
        permisRemise.setId(permisRemiseDTO.getId());
        permisRemise.setType(PermisType.valueOf(permisRemiseDTO.getType()));
        permisRemise.setDate_remise_permis(permisRemiseDTO.getDate_remise_permis());
        permisRemise.setLieu_remise_permis(permisRemiseDTO.getLieu_remise_permis());
        // Set permis and driver if needed
        return permisRemise;
    }
}
