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

    @PostMapping()
    public ResponseEntity<PermisDTO> createPermis(@RequestBody PermisDTO permisDTO) {
        PermisDTO createdPermis = permisService.createPermis(permisDTO);
        return new ResponseEntity<>(createdPermis, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<PermisDTO>> getAllPermis() {
        List<PermisDTO> permisList = permisService.getAllPermis();
        return new ResponseEntity<>(permisList, HttpStatus.OK);
    }

    @GetMapping("/{numPermis}")
    public ResponseEntity<PermisDTO> getPermisById(@PathVariable int numPermis) throws Exception {
        PermisDTO permisDTO = permisService.getPermisById(numPermis);
        return new ResponseEntity<>(permisDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{numPermis}")
    public ResponseEntity<Void> deletePermis(@PathVariable int numPermis) {
        permisService.deletePermis(numPermis);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
