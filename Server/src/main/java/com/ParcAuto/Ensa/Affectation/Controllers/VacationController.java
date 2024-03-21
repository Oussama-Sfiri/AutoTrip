package com.ParcAuto.Ensa.Affectation.Controllers;

import com.ParcAuto.Ensa.Affectation.Dto.VacationDTO;
import com.ParcAuto.Ensa.Affectation.Services.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacations")
public class VacationController {

    private final VacationService vacationService;

    @Autowired
    public VacationController(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    @GetMapping
    public ResponseEntity<List<VacationDTO>> getAllVacations() {
        List<VacationDTO> vacations = vacationService.getAllVacations();
        return ResponseEntity.ok().body(vacations);
    }

    @PostMapping
    public ResponseEntity<?> createVacation(@RequestBody VacationDTO vacationDTO) {
        ResponseEntity<?> createdVacation = vacationService.createVacation(vacationDTO);
        return ResponseEntity.ok().body(createdVacation).getBody();
    }
}
