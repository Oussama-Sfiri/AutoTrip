package com.ParcAuto.Ensa.Affectation.mappers;

import com.ParcAuto.Ensa.Affectation.Dto.DriverDTO;
import com.ParcAuto.Ensa.Affectation.Dto.PermisDTO;
import com.ParcAuto.Ensa.Affectation.Entities.Driver;
import com.ParcAuto.Ensa.Affectation.Entities.Permis;
import com.ParcAuto.Ensa.Affectation.Entities.PermisRemise;
import com.ParcAuto.Ensa.Affectation.Entities.PermisType;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class DriverMappers {


    public static DriverDTO DriverToDTO(Driver driver) {
        DriverDTO driverDTO = new DriverDTO();
        BeanUtils.copyProperties(driver, driverDTO);

        if (driver.getPermis() != null) {
            PermisDTO permisDTO = PermisMappers.PermisToDTO(driver.getPermis());
            driverDTO.setPermis(permisDTO);
        }

        // Additional mapping if needed
        return driverDTO;
    }

    public static Driver DTOToDriver(DriverDTO driverDTO) {
        Driver driver = new Driver();
        BeanUtils.copyProperties(driverDTO, driver);

        if (driverDTO.getPermis() != null) {
            Permis permis = PermisMappers.DTOToPermis(driverDTO.getPermis());
            driver.setPermis(permis);
        }

        // Additional mapping if needed
        return driver;
    }

}
