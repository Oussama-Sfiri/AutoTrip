package com.ParcAuto.Ensa.Affectation.Services;

import com.ParcAuto.Ensa.Affectation.Dto.DriverDTO;
import com.ParcAuto.Ensa.Affectation.Dto.TripDTO;
import com.ParcAuto.Ensa.Affectation.Entities.*;
import com.ParcAuto.Ensa.Affectation.Repositories.DriverRepository;
import com.ParcAuto.Ensa.Affectation.Repositories.TripRepository;
import com.ParcAuto.Ensa.Affectation.Repositories.VehiculeRepository;
import com.ParcAuto.Ensa.Affectation.Utils.PermitUtils;
import com.ParcAuto.Ensa.Affectation.mappers.TripMappers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class TripService {

    private final TripRepository tripRepository;
    private final DriverRepository driverRepository;
    private final VehiculeRepository vehiculeRepository;


    @Autowired
    public TripService(TripRepository tripRepository , DriverRepository driverRepository , VehiculeRepository vehiculeRepository) {
        this.tripRepository = tripRepository;
        this.driverRepository = driverRepository;
        this.vehiculeRepository = vehiculeRepository;

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
        String errorMessage = validateTrip(tripDTO);
        if (errorMessage != null) {
            return ResponseEntity.badRequest().body(errorMessage);
        }

        VehiculeType vehiculeType = tripDTO.getVehiculType();
        PermisType permitType = PermitUtils.getPermisForVehiculeType(vehiculeType);

        // Searching for Driver :
        List<Driver> availableDrivers = driverRepository.getAvailableDriversForTrip(permitType);
        if (availableDrivers.isEmpty()) {
            return ResponseEntity.badRequest().body("No available driver for this trip");
        }
        // Searching for Vehicule :
        List<Vehicule> availableVehicles = vehiculeRepository.getAvailableVehiclesForTrip(permitType);
        if (availableVehicles.isEmpty()) {
            return ResponseEntity.badRequest().body("No available vehicle for this trip");
        }

        Driver driver = availableDrivers.get(0);
        Vehicule vehicule = availableVehicles.get(0);

        Trip trip = TripMappers.dtoToTrip(tripDTO);
        trip.setDriver(driver);
        trip.setVehicule(vehicule);

        driver.setDisponibility(false);
        vehicule.setDisponibilite(false);
        driverRepository.save(driver);
        vehiculeRepository.save(vehicule);



        // Save the trip
        Trip savedTrip = tripRepository.save(trip);
        return ResponseEntity.ok().body(TripMappers.tripToDTO(savedTrip));

    }

    private String validateTrip(TripDTO tripDTO) {
        // Check if departure date is in the future
        LocalDate departureDate = tripDTO.getDepartureDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (departureDate.isBefore(LocalDate.now())) {
            return "Departure date must be in the future";
        }

        // Check if arrival date is after departure date
        LocalDate arrivalDate = tripDTO.getArrivalDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (arrivalDate.isBefore(departureDate)) {
            return "Arrival date must be after departure date";
        }

        // Check if departure time is in the future
        LocalTime departureTime = tripDTO.getDepartureTime().toLocalTime();
        if (departureDate.equals(LocalDate.now()) && departureTime.isBefore(LocalTime.now())) {
            return "Departure time must be in the future";
        }

        // Check if arrival time is after departure time (if on the same day)
        LocalTime arrivalTime = tripDTO.getArrivalTime().toLocalTime();
        if (arrivalDate.equals(departureDate) && arrivalTime.isBefore(departureTime)) {
            return "Arrival time must be after departure time";
        }

        return null; // No validation error
    }




}
