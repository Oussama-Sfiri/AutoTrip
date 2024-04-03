package com.ParcAuto.Ensa.services;


import com.ParcAuto.Ensa.Affectation.Dto.TripDTO;
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
import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


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
        tripDTO.setDepartureDate(LocalDate.now().plusDays(1));
        tripDTO.setDepartureTime(Time.valueOf(LocalTime.of(9, 0)));
        tripDTO.setArrivalDate(tripDTO.getDepartureDate().plusDays(1));
        tripDTO.setArrivalTime(Time.valueOf(LocalTime.of(8, 0)));
        tripDTO.setNbrOfPassengers(5);
        tripDTO.setVehiculType(VehiculeType.Fourgonette);

        // Performing the test
        ResponseEntity<?> responseEntity = tripService.createTrip(tripDTO);

        // Assertions
        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCode().value());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void testDepartureDateBeforeCurrentDate() {
        // Mocking tripDTO with departure date before the current date
        TripDTO tripDTO = new TripDTO();
        tripDTO.setDeparture("Agadir");
        tripDTO.setDestination("Madrid");
        tripDTO.setDepartureDate(LocalDate.now().minusDays(1)); // Setting departure date to yesterday
        tripDTO.setDepartureTime(Time.valueOf(LocalTime.of(9, 0)));
        tripDTO.setArrivalDate(tripDTO.getDepartureDate().plusDays(1));
        tripDTO.setArrivalTime(Time.valueOf(LocalTime.of(8, 0)));
        tripDTO.setNbrOfPassengers(5);
        tripDTO.setVehiculType(VehiculeType.Fourgonette);

        // Performing the test
        ResponseEntity<?> responseEntity = tripService.createTrip(tripDTO);

        // Assertions
        assertNotNull(responseEntity);
        assertEquals(400, responseEntity.getStatusCode().value());
    }

    @Test
    public void testArrivalDateBeforeDepartureDate() {
        // Mocking tripDTO with arrival date before departure date
        TripDTO tripDTO = new TripDTO();
        tripDTO.setDeparture("Agadir");
        tripDTO.setDestination("Madrid");
        tripDTO.setDepartureDate(LocalDate.now().plusDays(1));
        tripDTO.setDepartureTime(Time.valueOf(LocalTime.of(9, 0)));
        tripDTO.setArrivalDate(tripDTO.getDepartureDate().minusDays(1)); // Setting arrival date before departure date
        tripDTO.setArrivalTime(Time.valueOf(LocalTime.of(8, 0)));
        tripDTO.setNbrOfPassengers(5);
        tripDTO.setVehiculType(VehiculeType.Fourgonette);

        // Performing the test
        ResponseEntity<?> responseEntity = tripService.createTrip(tripDTO);

        // Assertions
        assertNotNull(responseEntity);
        assertEquals(400, responseEntity.getStatusCode().value());
    }

    @Test
    public void testArrivalTimeBeforeDepartureTime() {
        // Mocking tripDTO with arrival time before departure time on the same day
        TripDTO tripDTO = new TripDTO();
        tripDTO.setDeparture("Agadir");
        tripDTO.setDestination("Madrid");
        tripDTO.setDepartureDate(LocalDate.now()); // Setting departure date to the current date
        tripDTO.setDepartureTime(Time.valueOf(LocalTime.of(9, 0)));
        tripDTO.setArrivalDate(tripDTO.getDepartureDate());
        tripDTO.setArrivalTime(Time.valueOf(LocalTime.of(8, 0))); // Setting arrival time before departure time
        tripDTO.setNbrOfPassengers(5);
        tripDTO.setVehiculType(VehiculeType.Fourgonette);

        // Performing the test
        ResponseEntity<?> responseEntity = tripService.createTrip(tripDTO);

        // Assertions
        assertNotNull(responseEntity);
        assertEquals(400, responseEntity.getStatusCode().value());
    }





}
