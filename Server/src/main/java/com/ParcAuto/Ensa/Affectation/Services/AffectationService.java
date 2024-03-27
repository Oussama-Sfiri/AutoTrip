package com.ParcAuto.Ensa.Affectation.Services;

import com.ParcAuto.Ensa.Affectation.Dto.DriverDTO;
import com.ParcAuto.Ensa.Affectation.Dto.TripDTO;
import com.ParcAuto.Ensa.Affectation.Dto.VehiculeDTO;
import com.ParcAuto.Ensa.Affectation.Entities.*;
import com.ParcAuto.Ensa.Affectation.Repositories.DriverRepository;
import com.ParcAuto.Ensa.Affectation.Repositories.TripRepository;
import com.ParcAuto.Ensa.Affectation.Repositories.VehiculeRepository;
import com.ParcAuto.Ensa.Affectation.Utils.PermitUtils;
import com.ParcAuto.Ensa.Affectation.mappers.DriverMappers;
import com.ParcAuto.Ensa.Affectation.mappers.TripMappers;
import com.ParcAuto.Ensa.Affectation.mappers.VehiculeMappers;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AffectationService {
    private final TripService tripService;
    private final TripRepository tripRepository;
    private final DriverRepository driverRepository;
    private final VehiculeRepository vehiculeRepository;
    private  final VehiculeService vehiculeService;

    private final DriverService driverService;

    @Autowired
    public AffectationService( VehiculeService vehiculeService,TripService tripService , DriverRepository driverRepository , VehiculeRepository vehiculeRepository, DriverService driverService,TripRepository tripRepository) {
        this.tripService = tripService;
        this.tripRepository  = tripRepository;
        this.driverRepository = driverRepository;
        this.vehiculeRepository = vehiculeRepository;
        this.driverService = driverService;
        this.vehiculeService = vehiculeService;
    }

    public List<DriverDTO> getConducteursDisponibles(Long tripId) {
        TripDTO tripDTO = this.tripService.getTripById(tripId);
        System.out.println(tripDTO.getId()+""+tripDTO.getVehiculType()+""+tripDTO.getDeparture());
        VehiculeType vehiculeType = tripDTO.getVehiculType();
        PermisType permitType = PermitUtils.getPermisForVehiculeType(vehiculeType);
        Date departureDate = tripDTO.getDepartureDate();
        Date arrivalDate = tripDTO.getArrivalDate();
        Time departureTime = tripDTO.getDepartureTime();
        Time arrivalTime = tripDTO.getArrivalTime();

        // Searching for Driver :
        List<Driver> availableDrivers = this.findDriversByPermisTypeAndDisponibilityTrueAndVacationsNotOverlapping(permitType, departureDate, arrivalDate, departureTime, arrivalTime);
        if (availableDrivers == null || availableDrivers.isEmpty()) {
            return Collections.emptyList();
        }
        return availableDrivers.stream().map(DriverMappers::DriverToDTO).collect(Collectors.toList());
    }

    public List<VehiculeDTO> getVehiculesDisponibles(Long tripId) {
        TripDTO tripDTO = this.tripService.getTripById(tripId);
        VehiculeType vehiculeType = tripDTO.getVehiculType();
        PermisType permitType = PermitUtils.getPermisForVehiculeType(vehiculeType);
        Date departureDate = tripDTO.getDepartureDate();
        Date arrivalDate = tripDTO.getArrivalDate();
        // Searching for Vehicule :
        List<Vehicule> availableVehicles = vehiculeRepository.getAvailableVehiclesForTrip(permitType, departureDate, arrivalDate);
        if (availableVehicles.isEmpty()) {
            return null;
        }
        return availableVehicles.stream().map(VehiculeMappers::VehiculeToDTO).collect(Collectors.toList());
    }

    public String affecterTrip(Long driverId, Long vehiculeId, Long tripId) throws Exception {
        Optional<Trip> tripOptional = tripRepository.findById(tripId);
        Optional<Driver> driverOptional = driverRepository.findById(driverId);
        Optional<Vehicule> vehiculeOptional = vehiculeRepository.findById(vehiculeId);

        if (tripOptional.isEmpty() || driverOptional.isEmpty() || vehiculeOptional.isEmpty()) {
            return "Trip, driver, or vehicle not found!";
        }

        Trip trip = tripOptional.get();
        Driver driver = driverOptional.get();
        Vehicule vehicle = vehiculeOptional.get();

        if (!driver.isDisponibility() || !vehicle.isDisponibilite()) {
            return "already assigned trip!";
        }

        trip.setDriver(driver);
        trip.setVehicule(vehicle);

        driver.setDisponibility(false);
        vehicle.setDisponibilite(false);

        tripRepository.save(trip);
        driverRepository.save(driver);
        vehiculeRepository.save(vehicle);

        return "Trip assigned successfully!";
    }





    public List<Driver> findDriversByPermisTypeAndDisponibilityTrueAndVacationsNotOverlapping(
            PermisType permitType, Date departureDate, Date arrivalDate, Time departureTime, Time arrivalTime) {

        // Fetch all drivers from the repository
        List<Driver> allDrivers = driverRepository.findAll();


        return allDrivers.stream()
                .filter(driver -> {
                    // Check if the driver has the required permit type
                    Permis driverPermis = driver.getPermis();
                    if (driverPermis != null) {
                        boolean permitTypeMatches = driverPermis.getPermisRemises().stream()
                                .anyMatch(pr -> pr.getType() == permitType);

                        if (permitTypeMatches) {
                            // Check if the driver is available
                            boolean disponibility = driver.isDisponibility();

                            // Check if the driver's vacations do not overlap with the trip dates
                            boolean vacationsNotOverlapping = driver.getVacations().isEmpty() ||
                                    driver.getVacations().stream().noneMatch(vacation ->
                                            !(arrivalDate.before(vacation.getStart()) || departureDate.after(vacation.getEnd())));


                            // Check if the driver's trips do not overlap with the trip dates
                            boolean tripsNotOverlapping = driver.getTrips().stream()
                                    .noneMatch(trip -> (departureDate.after(trip.getArrivalDate())
                                            || arrivalDate.before(trip.getDepartureDate())
                                            || trip.getDepartureDate().after(departureDate)
                                            && trip.getDepartureDate().before(arrivalDate)
                                            || trip.getArrivalDate().after(departureDate)
                                            && trip.getArrivalDate().before(arrivalDate)
                                            || departureTime.after(trip.getArrivalTime())
                                            || arrivalTime.before(trip.getDepartureTime())
                                            || trip.getDepartureTime().after(departureTime)
                                            && trip.getDepartureTime().before(arrivalTime)
                                            || trip.getArrivalTime().after(departureTime)
                                            && trip.getArrivalTime().before(arrivalTime)));

                            return disponibility  && vacationsNotOverlapping && tripsNotOverlapping  ;

                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());


    }





}

