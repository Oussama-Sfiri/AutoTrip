//package com.ParcAuto.Ensa.Affectation.Controllers;
//
//import com.ParcAuto.Ensa.Affectation.Dto.TripDTO;
//import com.ParcAuto.Ensa.Affectation.Entities.Trip;
//import com.ParcAuto.Ensa.Affectation.Services.TripService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/trips")
//public class TripController {
//
//    private final TripService tripService;
//
//    @Autowired
//    public TripController(TripService tripService) {
//        this.tripService = tripService;
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<TripDTO> getTripById(@PathVariable("id") Long id) {
//        TripDTO tripDTO = tripService.getTripById(id);
//        return ResponseEntity.ok(tripDTO);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<TripDTO>> getAllTrips() {
//        List<TripDTO> tripDTOList = tripService.getAllTrips();
//        return ResponseEntity.ok(tripDTOList);
//    }
//
//    @PostMapping
//    public ResponseEntity<TripDTO> createTrip(@RequestBody TripDTO tripDTO) {
//        TripDTO createdTripDTO = tripService.createTrip(tripDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdTripDTO);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<TripDTO> updateTrip(@PathVariable("id") Long id, @RequestBody TripDTO tripDTO) {
//        TripDTO updatedTripDTO = tripService.updateTrip(id, tripDTO);
//        return ResponseEntity.ok(updatedTripDTO);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteTrip(@PathVariable("id") Long id) {
//        tripService.deleteTrip(id);
//        return ResponseEntity.noContent().build();
//    }
//}
