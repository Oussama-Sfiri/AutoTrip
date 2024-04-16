package com.ParcAuto.Ensa.Affectation.Services;

import com.ParcAuto.Ensa.Affectation.Dto.DriverDTO;
import com.ParcAuto.Ensa.Affectation.Entities.Driver;
import com.ParcAuto.Ensa.Affectation.Repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.ParcAuto.Ensa.Affectation.mappers.DriverMappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    private static final Logger logger = LoggerFactory.getLogger(DriverService.class);

    @Cacheable("drivers")
    public ResponseEntity<?> createDriver(DriverDTO driverDTO) {
        try {
            Optional<Driver> existingDriverOptional = driverRepository.findByCin(driverDTO.getCin());
            if (existingDriverOptional.isPresent()) {
                String errorMessage = "A driver with the same cin already exists.";
                return ResponseEntity.badRequest().body(errorMessage);
            }
            Driver driver = DriverMappers.DTOToDriver(driverDTO);
            driver = driverRepository.save(driver);
            return ResponseEntity.status(HttpStatus.CREATED).body(DriverMappers.DriverToDTO(driver));
        } catch (IllegalArgumentException ex) {
            String errorMessage = "Invalid input data. Please check your request.";
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }


    @Cacheable("drivers")
    public List<DriverDTO> getAllDrivers() {
        logger.info("Fetching all drivers from database. This log is for verifiying the cache");
        List<Driver> drivers = driverRepository.findAll();
        return drivers.stream().map(DriverMappers::DriverToDTO).collect(Collectors.toList());
    }

    @Cacheable(value = "drivers", key = "#id")
    public DriverDTO getDriverById(Long id) throws Exception {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new Exception("Driver not found with ID: " + id));
        return DriverMappers.DriverToDTO(driver);
    }

    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }
}