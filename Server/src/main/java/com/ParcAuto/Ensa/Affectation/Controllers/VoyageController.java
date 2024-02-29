package com.ParcAuto.Ensa.Affectation.Controllers;

import com.ParcAuto.Ensa.Affectation.Dto.VoyageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voyages")
public class VoyageController {

    @Autowired
    private VoyageService voyageService;

    @GetMapping("/{id}")
    public ResponseEntity<VoyageDTO> getVoyageById(@PathVariable Long id) {
        VoyageDTO voyageDTO = voyageService.getVoyageById(id);
        return ResponseEntity.ok(voyageDTO);
    }

    @PostMapping
    public ResponseEntity<VoyageDTO> createVoyage(@RequestBody VoyageDTO voyageDTO) {
        VoyageDTO createdVoyage = voyageService.createVoyage(voyageDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVoyage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VoyageDTO> updateVoyage(@PathVariable Long id, @RequestBody VoyageDTO voyageDTO) {
        VoyageDTO updatedVoyage = voyageService.updateVoyage(id, voyageDTO);
        return ResponseEntity.ok(updatedVoyage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoyage(@PathVariable Long id) {
        voyageService.deleteVoyage(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<VoyageDTO>> getAllVoyages() {
        List<VoyageDTO> voyages = voyageService.getAllVoyages();
        return ResponseEntity.ok(voyages);
    }
}

