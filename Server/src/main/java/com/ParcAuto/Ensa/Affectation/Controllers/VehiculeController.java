package com.ParcAuto.Ensa.Affectation.Controllers;

import com.ParcAuto.Ensa.Affectation.Dto.VehiculeDTO;
import com.ParcAuto.Ensa.Affectation.Entities.Vehicule;
import com.ParcAuto.Ensa.Affectation.Services.VehiculeService;
import com.ParcAuto.Ensa.Affectation.mappers.VehiculeMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicules")
public class VehiculeController {

    private final VehiculeService vehiculeService;
    @Autowired
    public VehiculeController(VehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    @PostMapping
    public ResponseEntity<VehiculeDTO> createVehicule(@RequestBody VehiculeDTO vehiculeDTO) {
        VehiculeDTO createdVehicule = vehiculeService.createVehicule(vehiculeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVehicule);
    }

    @GetMapping
    public ResponseEntity<List<VehiculeDTO>> getAllVehicles() {
        List<VehiculeDTO> allVehicles = vehiculeService.getAllVehicles();
        return ResponseEntity.ok(allVehicles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculeDTO> getVehiculeById(@PathVariable Long id) {
        VehiculeDTO vehiculeDTO = vehiculeService.getVehiculeById(id);
        if (vehiculeDTO != null) {
            return ResponseEntity.ok(vehiculeDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
