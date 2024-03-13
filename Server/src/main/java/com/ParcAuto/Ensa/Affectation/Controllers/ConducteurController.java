package com.ParcAuto.Ensa.Affectation.Controllers;

import com.ParcAuto.Ensa.Affectation.Dto.ConducteurDTO;
import com.ParcAuto.Ensa.Affectation.Entities.Conducteur;
import com.ParcAuto.Ensa.Affectation.Repositories.ConducteurRepository;
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
    @Autowired
    private ConducteurRepository conducteurRepo;

    @GetMapping("/{matricule}")
    public ResponseEntity<ConducteurDTO> getConducteurById(@PathVariable String matricule) throws ChangeSetPersister.NotFoundException {
        ConducteurDTO conducteurDTO = conducteurService.getConducteurByMatricule(matricule);
        return ResponseEntity.ok(conducteurDTO);
    }

    @PostMapping
    public ResponseEntity<ConducteurDTO> createConducteur(@RequestBody ConducteurDTO conducteurDTO) {
        ConducteurDTO createdConducteur = conducteurService.createConducteur(conducteurDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdConducteur);
    }

    @PutMapping("/{matricule}")
    public ResponseEntity<ConducteurDTO> updateConducteur(@PathVariable String matricule, @RequestBody ConducteurDTO conducteurDTO) {
        ConducteurDTO updatedConducteur = conducteurService.updateConducteur(matricule, conducteurDTO);
        return ResponseEntity.ok(updatedConducteur);
    }

    @DeleteMapping("/{matricule}")
    public ResponseEntity<ConducteurDTO> deleteConducteur(@PathVariable String matricule) throws ChangeSetPersister.NotFoundException {
        ConducteurDTO deletedConducteur = conducteurService.deleteConducteur(matricule);
        return ResponseEntity.ok(deletedConducteur);
    }

    @GetMapping
    public List<Conducteur> getAllConducteurs() {
//        List<ConducteurDTO> conducteurs = conducteurService.getAllConducteurs();
//        return ResponseEntity.ok(conducteurs);
            return conducteurRepo.findAll();
    }
}

