package com.ParcAuto.Ensa.Affectation.mappers;

import com.ParcAuto.Ensa.Affectation.Dto.PermisRemiseDTO;
import com.ParcAuto.Ensa.Affectation.Entities.Driver;
import com.ParcAuto.Ensa.Affectation.Entities.Permis;
import com.ParcAuto.Ensa.Affectation.Entities.PermisRemise;

public class PermisRemiseMappers {



    public static PermisRemiseDTO PermisRemiseToDTO(PermisRemise permisRemise) {
        PermisRemiseDTO permisRemiseDTO = new PermisRemiseDTO();
        permisRemiseDTO.setId(permisRemise.getId());
        permisRemiseDTO.setType(permisRemise.getType());
        permisRemiseDTO.setDate_remise_permis(permisRemise.getDate_remise_permis());
        permisRemiseDTO.setLieu_remise_permis(permisRemise.getLieu_remise_permis());
        Permis permis = permisRemise.getPermis();
        if (permis != null) {
            permisRemiseDTO.setNumPermis(permis.getNumPermis());
        }
        Driver driver = permisRemise.getDriver();
        if (driver != null) {
            permisRemiseDTO.setCin(driver.getCin());
        }
        return permisRemiseDTO;
    }


    public static PermisRemise DTOToPermisRemise(PermisRemiseDTO permisRemiseDTO, Permis permis, Driver driver) {
        PermisRemise permisRemise = new PermisRemise();
        permisRemise.setType(permisRemiseDTO.getType());
        permisRemise.setDate_remise_permis(permisRemiseDTO.getDate_remise_permis());
        permisRemise.setLieu_remise_permis(permisRemiseDTO.getLieu_remise_permis());
        permisRemise.setPermis(permis);
        permisRemise.setDriver(driver);
        return permisRemise;
    }





}
