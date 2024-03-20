package com.ParcAuto.Ensa.Affectation.Services;

import com.ParcAuto.Ensa.Affectation.Dto.TripDTO;
import com.ParcAuto.Ensa.Affectation.Entities.Trip;
import com.ParcAuto.Ensa.Affectation.Entities.VehiculeType;
import com.ParcAuto.Ensa.Affectation.Repositories.TripRepository;
import com.ParcAuto.Ensa.Affectation.mappers.TripMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class TripService {

    private final TripRepository tripRepository;


    @Autowired
    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;

    }

    public TripDTO getTripById(Long id) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Trip not found with id: " + id));
        return TripMappers.tripToDTO(trip);
    }

    public List<TripDTO> getAllTrips() {
        List<Trip> trips = tripRepository.findAll();
        return trips.stream()
                .map(TripMappers::tripToDTO)
                .collect(Collectors.toList());
    }



    public ResponseEntity<?> createTrip(TripDTO tripDTO) {


        // Check if departure date is in the future
        Date departureDate = tripDTO.getDepartureDate();
        if (departureDate != null) {
            LocalDate departureLocalDate = departureDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (departureLocalDate.isBefore(LocalDate.now())) {
                return ResponseEntity.badRequest().body("Departure date must be in the future");
            }
        }

        // Check if arrival date is after departure date
        Date arrivalDate = tripDTO.getArrivalDate();
        if (arrivalDate != null && departureDate != null) {
            LocalDate arrivalLocalDate = arrivalDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate departureLocalDate = departureDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (arrivalLocalDate.isBefore(departureLocalDate)) {
                return ResponseEntity.badRequest().body("Arrival date must be after departure date");
            }
        }

        // Check if departure time is in the future
        LocalTime departureTime = tripDTO.getDepartureTime().toLocalTime();
        if (departureTime != null && departureDate != null &&
                departureDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().equals(LocalDate.now()) &&
                departureTime.isBefore(LocalTime.now())) {
            return ResponseEntity.badRequest().body("Departure time must be in the future");
        }

        // Check if arrival time is after departure time (if on the same day)
        LocalTime arrivalTime = tripDTO.getArrivalTime().toLocalTime();
        if (arrivalTime != null && departureDate != null && arrivalDate != null &&
                departureDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().equals(arrivalDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()) &&
                arrivalTime.isBefore(departureTime)) {
            return ResponseEntity.badRequest().body("Arrival time must be after departure time");
        }

        Trip trip = TripMappers.dtoToTrip(tripDTO);
        Trip savedTrip = tripRepository.save(trip);
        return ResponseEntity.ok().body(TripMappers.tripToDTO(savedTrip));
    }







}
