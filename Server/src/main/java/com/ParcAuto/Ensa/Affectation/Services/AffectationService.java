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
import com.ParcAuto.Ensa.Affectation.mappers.VehiculeMappers;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AffectationService {
    private final TripService tripService;
    private final DriverRepository driverRepository;
    private final VehiculeRepository vehiculeRepository;

    private final DriverService driverService;

    @Autowired
    public AffectationService(TripService tripService , DriverRepository driverRepository , VehiculeRepository vehiculeRepository, DriverService driverService) {
        this.tripService = tripService;
        this.driverRepository = driverRepository;
        this.vehiculeRepository = vehiculeRepository;
        this.driverService = driverService;
    }

    public List<DriverDTO> getConducteursDisponibles(Long tripId) {
        TripDTO tripDTO = this.tripService.getTripById(tripId);
//        System.out.println(tripDTO.getId()+""+tripDTO.getVehiculType()+""+tripDTO.getDeparture());
        VehiculeType vehiculeType = tripDTO.getVehiculType();
        PermisType permitType = PermitUtils.getPermisForVehiculeType(vehiculeType);
        Date departureDate = tripDTO.getDepartureDate();
        Date arrivalDate = tripDTO.getArrivalDate();
        Time departureTime = tripDTO.getDepartureTime();
        Time arrivalTime = tripDTO.getArrivalTime();

        System.out.println(permitType+" ----- "+departureDate+" ----- "+arrivalDate+" ----- "+departureTime+" ----- "+arrivalTime);

        // Searching for Driver :
        List<Driver> availableDrivers = this.findDriversByPermisTypeAndDisponibilityTrueAndVacationsNotOverlapping(permitType, departureDate, arrivalDate, departureTime, arrivalTime);
        System.out.println(availableDrivers);
        if (availableDrivers.isEmpty()) {
            return null;
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

        // Implémentez la logique pour récupérer les véhicules disponibles en fonction du type de permis
        // Utilisez vehiculeRepository pour accéder à la base de données
        // Retournez la liste des véhicules disponibles
        return availableVehicles.stream().map(VehiculeMappers::VehiculeToDTO).collect(Collectors.toList());
    }

    public String affecterTrip(Long conducteurId, Long vehiculeId, Long tripId) {
        // Implémentez la logique pour affecter le conducteur et le véhicule au voyage spécifié par tripId
        // Utilisez conducteurRepository et vehiculeRepository pour accéder à la base de données
        // Mettez à jour les attributs nécessaires du conducteur et du véhicule pour indiquer qu'ils sont affectés au voyage
        // Retournez un message indiquant le succès ou l'échec de l'affectation
        return null;
    }

    public List<Driver> findDriversByPermisTypeAndDisponibilityTrueAndVacationsNotOverlapping(
            PermisType permitType, Date departureDate, Date arrivalDate, Time departureTime, Time arrivalTime) {

        List<Driver> allDrivers = driverRepository.findAll();

        return allDrivers.stream()
                .filter(driver -> {
                    // Filtrer par type de permis
                    Permis driverPermis = driver.getPermis();
                    if (driverPermis != null) {
                        boolean permitTypeMatches = driverPermis.getPermisRemises().stream()
                                .anyMatch(pr -> pr.getType() == permitType);

                        if (permitTypeMatches) {
                            // Filtrer par disponibilité
                            boolean disponibility = driver.isDisponibility();

                            // Filtrer par vacances ne se chevauchant pas
                            boolean vacationsNotOverlapping = driver.getVacations().stream()
                                    .noneMatch(vacation -> (departureDate.after(vacation.getEnd())
                                            || arrivalDate.before(vacation.getStart())));

                            // Filtrer par voyages ne se chevauchant pas
                            boolean tripsNotOverlapping = driver.getTrip().stream()
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

                            return disponibility && vacationsNotOverlapping && tripsNotOverlapping;
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

}

