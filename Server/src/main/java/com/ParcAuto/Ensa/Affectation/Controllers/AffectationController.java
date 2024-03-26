package com.ParcAuto.Ensa.Affectation.Controllers;

import com.ParcAuto.Ensa.Affectation.Dto.DriverDTO;
import com.ParcAuto.Ensa.Affectation.Dto.VehiculeDTO;
import com.ParcAuto.Ensa.Affectation.Services.AffectationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/affectation")
public class AffectationController {
    @Autowired
    private AffectationService affectationService;

    @GetMapping("/conducteurs-disponibles/{tripId}")
    public ResponseEntity<List<DriverDTO>> getConducteursDisponibles(@PathVariable Long tripId) {
        List<DriverDTO> conducteursDisponibles = affectationService.getConducteursDisponibles(tripId);
        return ResponseEntity.ok(conducteursDisponibles);
    }

    @GetMapping("/vehicules-disponibles/{tripId}")
    public ResponseEntity<List<VehiculeDTO>> getVehiculesDisponibles(@PathVariable Long tripId) {
        List<VehiculeDTO> vehiculesDisponibles = affectationService.getVehiculesDisponibles(tripId);
        return ResponseEntity.ok(vehiculesDisponibles);
    }

    @PostMapping("/affecter-trip/{tripId}")
    public ResponseEntity<String> affecterTrip(@RequestParam Long conducteurId, @RequestParam Long vehiculeId, @PathVariable Long tripId) {
        String message = affectationService.affecterTrip(conducteurId, vehiculeId, tripId);
        return ResponseEntity.ok(message);
    }
}
