package com.ParcAuto.Ensa.Affectation.Services;

import com.ParcAuto.Ensa.Affectation.Dto.DriverDTO;
import com.ParcAuto.Ensa.Affectation.Entities.Driver;
import com.ParcAuto.Ensa.Affectation.Repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import com.ParcAuto.Ensa.Affectation.mappers.DriverMappers;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    public DriverDTO createDriver(DriverDTO driverDTO) {
        Driver driver = DriverMappers.DTOToDriver(driverDTO);
        driver = driverRepository.save(driver);
        return DriverMappers.DriverToDTO(driver);
    }

    public List<DriverDTO> getAllDrivers() {
        List<Driver> drivers = driverRepository.findAll();
        return drivers.stream().map(DriverMappers::DriverToDTO).collect(Collectors.toList());
    }


    public DriverDTO getDriverById(String id) throws Exception {
        Driver driver = driverRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new Exception("Driver not found with ID: " + id));
        return DriverMappers.DriverToDTO(driver);
    }

    public void deleteDriver(String id) {
        driverRepository.deleteById(Long.valueOf(id));
    }
}