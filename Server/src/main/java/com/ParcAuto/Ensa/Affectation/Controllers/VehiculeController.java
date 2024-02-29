package com.ParcAuto.Ensa.Affectation.Controllers;

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

    @GetMapping("/{id}")
    public ResponseEntity<VehiculeDTO> getVehiculeById(@PathVariable Long id) {
        VehiculeDTO vehiculeDTO = vehiculeService.getVehiculeById(id);
        return ResponseEntity.ok(vehiculeDTO);
    }

    @PostMapping
    public ResponseEntity<VehiculeDTO> createVehicule(@RequestBody VehiculeDTO vehiculeDTO) {
        VehiculeDTO createdVehicule = vehiculeService.createVehicule(vehiculeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVehicule);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehiculeDTO> updateVehicule(@PathVariable Long id, @RequestBody VehiculeDTO vehiculeDTO) {
        VehiculeDTO updatedVehicule = vehiculeService.updateVehicule(id, vehiculeDTO);
        return ResponseEntity.ok(updatedVehicule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicule(@PathVariable Long id) {
        vehiculeService.deleteVehicule(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<VehiculeDTO>> getAllVehicules() {
        List<VehiculeDTO> vehicules = vehiculeService.getAllVehicules();
        return ResponseEntity.ok(vehicules);
    }
}

