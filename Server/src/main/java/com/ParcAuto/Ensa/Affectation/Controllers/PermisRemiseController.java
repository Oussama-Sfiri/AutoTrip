package com.ParcAuto.Ensa.Affectation.Controllers;

import com.ParcAuto.Ensa.Affectation.Dto.PermisRemiseDTO;
import com.ParcAuto.Ensa.Affectation.Services.PermisRemiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permisremise")
public class PermisRemiseController {

    private final PermisRemiseService permisRemiseService;

    @Autowired
    public PermisRemiseController(PermisRemiseService permisRemiseService) {
        this.permisRemiseService = permisRemiseService;
    }

    @PostMapping
    public ResponseEntity<PermisRemiseDTO> createPermisRemise(@RequestBody PermisRemiseDTO permisRemiseDTO) {
        PermisRemiseDTO createdPermisRemise = permisRemiseService.createPermisRemise(permisRemiseDTO);
        return new ResponseEntity<>(createdPermisRemise, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermisRemiseDTO> getPermisRemiseById(@PathVariable Long id) throws Exception {
        PermisRemiseDTO permisRemiseDTO = permisRemiseService.getPermisRemiseById(id);
        return ResponseEntity.ok(permisRemiseDTO);
    }



}
