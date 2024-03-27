package com.ParcAuto.Ensa.Affectation.Controllers;

import com.ParcAuto.Ensa.Affectation.Dto.AffecterTripRequestDTO;
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
    public ResponseEntity<?> getConducteursDisponibles(@PathVariable Long tripId) {
        List<DriverDTO> conducteursDisponibles = affectationService.getConducteursDisponibles(tripId);
        if (conducteursDisponibles == null || conducteursDisponibles.isEmpty()) {
            return ResponseEntity.badRequest().body("No available drivers for this trip");
        }
        return ResponseEntity.ok(conducteursDisponibles);
    }


    @GetMapping("/vehicules-disponibles/{tripId}")
    public ResponseEntity<List<VehiculeDTO>> getVehiculesDisponibles(@PathVariable Long tripId) {
        List<VehiculeDTO> vehiculesDisponibles = affectationService.getVehiculesDisponibles(tripId);
        return ResponseEntity.ok(vehiculesDisponibles);
    }

    @PostMapping("/affecter-trip/{tripId}")
    public ResponseEntity<String> affecterTrip(@PathVariable Long tripId, @RequestBody AffecterTripRequestDTO request) throws Exception {
        Long driverId = request.getDriverId();
        Long vehiculeId = request.getVehiculeId();
        if (driverId == null || vehiculeId == null) {
            return ResponseEntity.ok("DriverId and VehiculeId must not be null");
        }
        String message = affectationService.affecterTrip(driverId, vehiculeId, tripId);
        return ResponseEntity.ok(message);
    }




}
