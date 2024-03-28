package com.ParcAuto.Ensa.services;


import com.ParcAuto.Ensa.Affectation.Dto.TripDTO;
import com.ParcAuto.Ensa.Affectation.Entities.Trip;
import com.ParcAuto.Ensa.Affectation.Entities.VehiculeType;
import com.ParcAuto.Ensa.Affectation.Repositories.TripRepository;
import com.ParcAuto.Ensa.Affectation.Services.TripService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TripServiceTest {

    @Mock
    private TripRepository tripRepository;

    @InjectMocks
    private TripService tripService;

    @BeforeEach
    public void setUp() {
        Mockito.reset(tripRepository);
    }

    @Test
    public void testCreateValidTrip() {
        // Mocking tripDTO
        TripDTO tripDTO = new TripDTO();
        tripDTO.setDeparture("Agadir");
        tripDTO.setDestination("Madrid");
        tripDTO.setDepartureDate(new Date(124, 3, 1));
        tripDTO.setDepartureTime(Time.valueOf(LocalTime.of(9, 0)));
        tripDTO.setArrivalDate(new Date(124, 3, 2));
        tripDTO.setArrivalTime(Time.valueOf(LocalTime.of(8, 0)));
        tripDTO.setNbrOfPassengers(5);
        tripDTO.setVehiculType(VehiculeType.valueOf("Fourgonette"));

        // Mocking trip entity
        Trip tripEntity = new Trip();
        tripEntity.setId(1L);
        when(tripRepository.save(Mockito.any(Trip.class))).thenReturn(tripEntity);


        // Performing the test
        ResponseEntity<?> responseEntity = tripService.createTrip(tripDTO);
        // Assertions
        assertNotNull(responseEntity);
        assertEquals(200,  responseEntity.getStatusCode().value());
        assertNotNull(responseEntity.getBody());
        TripDTO savedTripDTO = (TripDTO) responseEntity.getBody();
        assertEquals(tripDTO.getDeparture(), savedTripDTO.getDeparture());
        assertEquals(tripDTO.getDestination(), savedTripDTO.getDestination());
    }




}
