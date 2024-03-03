
package com.ParcAuto.Ensa.Affectation.Controllers;

import com.ParcAuto.Ensa.Affectation.Dto.ConducteurDTO;
import com.ParcAuto.Ensa.Affectation.Entities.Vehicule;
import com.ParcAuto.Ensa.Affectation.Services.VehiculeService; // Import the VehiculeService
import com.ParcAuto.Ensa.Affectation.Dto.VehiculeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicules")
public class VehiculeController {

    @Autowired
    private VehiculeService vehiculeService;

    @GetMapping("/{immatriculation}")
    public ResponseEntity<VehiculeDTO> getVehiculeByImmatriculation(@PathVariable String immatriculation) {
        VehiculeDTO vehiculeDTO = vehiculeService.getVehiculeByImmatriculation(immatriculation);
        return ResponseEntity.ok(vehiculeDTO);
    }

    @PostMapping
    public ResponseEntity<VehiculeDTO> createVehicule(@RequestBody VehiculeDTO vehiculeDTO) {
        VehiculeDTO createdVehicule = vehiculeService.createVehicule(vehiculeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVehicule);
    }

    @PutMapping("/{immatriculation}")
    public ResponseEntity<VehiculeDTO> updateVehicule(@PathVariable String immatriculation, @RequestBody VehiculeDTO vehiculeDTO) {
        VehiculeDTO updatedVehicule = vehiculeService.updateVehicule(immatriculation, vehiculeDTO);
        return ResponseEntity.ok(updatedVehicule);
    }

    @DeleteMapping("/{immatriculation}")
    public ResponseEntity<VehiculeDTO> deleteVehicule(@PathVariable String immatriculation) {
        VehiculeDTO deletedVehicule = vehiculeService.deleteVehicule(immatriculation);
        return ResponseEntity.ok(deletedVehicule);
    }

    @GetMapping
    public ResponseEntity<List<VehiculeDTO>> getAllVehicules() {
        List<VehiculeDTO> vehiculeDTOS = vehiculeService.getAllVehicules();
        return ResponseEntity.ok(vehiculeDTOS);
    }
}
