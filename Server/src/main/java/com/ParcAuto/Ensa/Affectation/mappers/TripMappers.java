package com.ParcAuto.Ensa.Affectation.mappers;

import com.ParcAuto.Ensa.Affectation.Dto.TripDTO;
import com.ParcAuto.Ensa.Affectation.Entities.Trip;
import org.springframework.beans.BeanUtils;


public class TripMappers {

    public static TripDTO tripToDTO(Trip trip) {
        TripDTO tripDTO = new TripDTO();
        BeanUtils.copyProperties(trip, tripDTO);
        return tripDTO;
    }

    public static Trip dtoToTrip(TripDTO tripDTO) {
        Trip trip = new Trip();
        BeanUtils.copyProperties(tripDTO, trip);
        return trip;
    }


}
