package com.ParcAuto.Ensa.Affectation.Controllers;

import com.ParcAuto.Ensa.Affectation.Dto.PermisDTO;
import com.ParcAuto.Ensa.Affectation.Services.PermisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permis")
public class PermisController {

    @Autowired
    private PermisService permisService;

    @GetMapping("/{numeroPermis}")
    public ResponseEntity<PermisDTO> getPermisByNumero(@PathVariable String numeroPermis) {
        PermisDTO permisDTO = permisService.getPermisByNumero(numeroPermis);
        if (permisDTO != null) {
            return ResponseEntity.ok(permisDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PermisDTO>> getAllPermis() {
        List<PermisDTO> permisDTOs = permisService.getAllPermis();
        return ResponseEntity.ok(permisDTOs);
    }

    @PostMapping
    public ResponseEntity<PermisDTO> createPermis(@RequestBody PermisDTO permisDTO) {
        PermisDTO createdPermis = permisService.createPermis(permisDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPermis);
    }

    @PutMapping("/{numeroPermis}")
    public ResponseEntity<PermisDTO> updatePermis(@PathVariable String numeroPermis, @RequestBody PermisDTO permisDTO) {
        PermisDTO updatedPermis = permisService.updatePermis(numeroPermis, permisDTO);
        if (updatedPermis != null) {
            return ResponseEntity.ok(updatedPermis);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{numeroPermis}")
    public ResponseEntity<Void> deletePermis(@PathVariable String numeroPermis) {
        permisService.deletePermis(numeroPermis);
        return ResponseEntity.noContent().build();
    }
}
