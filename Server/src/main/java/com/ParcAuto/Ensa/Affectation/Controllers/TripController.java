package com.ParcAuto.Ensa.Affectation.Controllers;

import com.ParcAuto.Ensa.Affectation.Dto.TripDTO;
import com.ParcAuto.Ensa.Affectation.Services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController {

  private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping
    public ResponseEntity<?> createTrip(@RequestBody TripDTO tripDTO) {
        ResponseEntity<?> createdTrip = tripService.createTrip(tripDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTrip).getBody();
    }

    @GetMapping
    public ResponseEntity<List<TripDTO>> getAllTrips() {
        List<TripDTO> tripDTOs = tripService.getAllTrips();
        return ResponseEntity.ok(tripDTOs);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TripDTO> getTripById(@PathVariable Long id) {
        TripDTO tripDTO = tripService.getTripById(id);
        return ResponseEntity.ok(tripDTO);
    }


}
