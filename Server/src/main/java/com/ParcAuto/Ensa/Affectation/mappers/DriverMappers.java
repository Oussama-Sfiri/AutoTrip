package com.ParcAuto.Ensa.Affectation.mappers;

import com.ParcAuto.Ensa.Affectation.Dto.DriverDTO;
import com.ParcAuto.Ensa.Affectation.Dto.PermisRemiseDTO;
import com.ParcAuto.Ensa.Affectation.Entities.Driver;
import com.ParcAuto.Ensa.Affectation.Entities.Permis;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.stream.Collectors;

public class DriverMappers {


    public static DriverDTO DriverToDTO(Driver driver) {
        DriverDTO driverDTO = new DriverDTO();
        BeanUtils.copyProperties(driver, driverDTO);
        if (driver.getPermis() != null) {
            driverDTO.setPermis(PermisMappers.PermisToDTO(driver.getPermis()));
        }
        return driverDTO;
    }


    public static Driver DTOToDriver(DriverDTO driverDTO) {
        Driver driver = new Driver();
        driver.setCin(driverDTO.getCin());
        driver.setNom(driverDTO.getNom());
        driver.setPrenom(driverDTO.getPrenom());
        driver.setDate_naissance(driverDTO.getDate_naissance());
        driver.setAddresse(driverDTO.getAddresse());
        driver.setDisponibility(driverDTO.isDisponibility());
        if (driverDTO.getPermis() != null) {
            Permis permis = PermisMappers.DTOToPermis(driverDTO.getPermis());
            driver.setPermis(permis);
        }
        return driver;
    }


}
