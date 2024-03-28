package com.ParcAuto.Ensa.Affectation.mappers;

import com.ParcAuto.Ensa.Affectation.Dto.TripDTO;
import com.ParcAuto.Ensa.Affectation.Entities.Trip;
import org.springframework.beans.BeanUtils;

public class TripMappers {

    public static TripDTO tripToDTO(Trip trip) {
        TripDTO tripDTO = new TripDTO();
        BeanUtils.copyProperties(trip, tripDTO);
        if (trip.getDriver() != null) {
            tripDTO.setDriver(DriverMappers.DriverToDTO(trip.getDriver()));
        }
        if (trip.getVehicule() != null) {
            tripDTO.setVehicule(VehiculeMappers.VehiculeToDTO(trip.getVehicule()));
        }
        return tripDTO;
    }

    public static Trip dtoToTrip(TripDTO tripDTO) {
        Trip trip = new Trip();
        BeanUtils.copyProperties(tripDTO, trip);
        if (tripDTO.getDriver() != null) {
            trip.setDriver(DriverMappers.DTOToDriver(tripDTO.getDriver()));
        }
        if (tripDTO.getVehicule() != null) {
            trip.setVehicule(VehiculeMappers.DTOToVehicule(tripDTO.getVehicule()));
        }
        return trip;
    }



}
