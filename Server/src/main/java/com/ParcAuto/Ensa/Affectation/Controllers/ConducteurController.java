package com.ParcAuto.Ensa.Affectation.Controllers;

import com.ParcAuto.Ensa.Affectation.Dto.ConducteurDTO;
import com.ParcAuto.Ensa.Affectation.Services.ConducteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conducteurs")
public class ConducteurController {

    @Autowired
    private ConducteurService conducteurService;

    @GetMapping("/{id}")
    public ResponseEntity<ConducteurDTO> getConducteurById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        ConducteurDTO conducteurDTO = conducteurService.getConducteurById(id);
        return ResponseEntity.ok(conducteurDTO);
    }

    @PostMapping
    public ResponseEntity<ConducteurDTO> createConducteur(@RequestBody ConducteurDTO conducteurDTO) {
        ConducteurDTO createdConducteur = conducteurService.createConducteur(conducteurDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdConducteur);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConducteurDTO> updateConducteur(@PathVariable Long id, @RequestBody ConducteurDTO conducteurDTO) {
        ConducteurDTO updatedConducteur = conducteurService.updateConducteur(id, conducteurDTO);
        return ResponseEntity.ok(updatedConducteur);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConducteur(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        conducteurService.deleteConducteur(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ConducteurDTO>> getAllConducteurs() {
        List<ConducteurDTO> conducteurs = conducteurService.getAllConducteurs();
        return ResponseEntity.ok(conducteurs);
    }
}

