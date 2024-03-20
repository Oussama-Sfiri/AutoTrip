package com.ParcAuto.Ensa.Affectation.Controllers;

import com.ParcAuto.Ensa.Affectation.Dto.CarteGriseDTO;
import com.ParcAuto.Ensa.Affectation.Services.CarteGriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carteGrise")
public class CarteGriseController {
    private final CarteGriseService carteGriseService;

    @Autowired
    public CarteGriseController(CarteGriseService carteGriseService) {
        this.carteGriseService = carteGriseService;
    }

    @PostMapping()
    public ResponseEntity<CarteGriseDTO> createCarteGrise(@RequestBody CarteGriseDTO carteGriseDTO) {
        CarteGriseDTO createdCarteGrise = carteGriseService.createCarteGrise(carteGriseDTO);
        return new ResponseEntity<>(createdCarteGrise, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<CarteGriseDTO>> getAllCarteGrise() {
        List<CarteGriseDTO> carteGriseList = carteGriseService.getAllCarteGrise();
        return new ResponseEntity<>(carteGriseList, HttpStatus.OK);
    }

}
