package com.ParcAuto.Ensa.Affectation.mappers;

import com.ParcAuto.Ensa.Affectation.Dto.DriverDTO;
import com.ParcAuto.Ensa.Affectation.Dto.PermisRemiseDTO;
import com.ParcAuto.Ensa.Affectation.Dto.TripDTO;
import com.ParcAuto.Ensa.Affectation.Dto.VacationDTO;
import com.ParcAuto.Ensa.Affectation.Entities.Driver;
import com.ParcAuto.Ensa.Affectation.Entities.Permis;
import com.ParcAuto.Ensa.Affectation.Entities.Vacation;
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
        if (driver.getVacations() != null) {
            List<VacationDTO> vacationDTOs = driver.getVacations().stream()
                    .map(VacationMappers::VacationTODto)
                    .collect(Collectors.toList());
            driverDTO.setVacations(vacationDTOs);
        }
        if (driver.getTrips() != null) {
            List<TripDTO> tripDTOs = driver.getTrips().stream()
                    .map(TripMappers::tripToDTO)
                    .collect(Collectors.toList());
            driverDTO.setTrips(tripDTOs);
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
        if (driverDTO.getVacations() != null && !driverDTO.getVacations().isEmpty()) {
            List<Vacation> vacations = driverDTO.getVacations().stream()
                    .map(VacationMappers::DtoTOVacation)
                    .collect(Collectors.toList());
            driver.setVacations(vacations);
        }
        return driver;
    }


}
