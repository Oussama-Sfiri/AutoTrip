package com.ParcAuto.Ensa.Affectation.Services;

import com.ParcAuto.Ensa.Affectation.Dto.VacationDTO;
import com.ParcAuto.Ensa.Affectation.Entities.Driver;
import com.ParcAuto.Ensa.Affectation.Entities.Vacation;
import com.ParcAuto.Ensa.Affectation.Repositories.DriverRepository;
import com.ParcAuto.Ensa.Affectation.Repositories.VacationRepository;
import com.ParcAuto.Ensa.Affectation.mappers.VacationMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VacationService {
    private final VacationRepository vacationRepository;
    private final DriverRepository driverRepository;

    @Autowired
    public VacationService(VacationRepository vacationRepository,DriverRepository driverRepository) {
        this.vacationRepository = vacationRepository;
        this.driverRepository = driverRepository;
    }

    public List<VacationDTO> getAllVacations() {
        List<Vacation> vacations = vacationRepository.findAll();
        return vacations.stream()
                .map(VacationMappers::VacationTODto)
                .collect(Collectors.toList());
    }

    public ResponseEntity<?> createVacation(VacationDTO vacationDTO) {
        if (vacationDTO.getStart().after(vacationDTO.getEnd())) {
            return ResponseEntity.badRequest().body("Start date must be before the end date");
        }
        Long driverId = vacationDTO.getDriverId();
        Driver driver = driverRepository.findById(driverId)
                .orElse(null);
        if (driver == null) {
            return ResponseEntity.badRequest().body("Driver not found with ID: " + driverId);
        }
        Vacation vacation = VacationMappers.DtoTOVacation(vacationDTO);
        vacation.setDriver(driver);
        Vacation savedVacation = vacationRepository.save(vacation);
        VacationDTO savedVacationDTO = VacationMappers.VacationTODto(savedVacation);
        return ResponseEntity.ok().body(savedVacationDTO);
    }
}

