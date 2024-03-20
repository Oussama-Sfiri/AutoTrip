package com.ParcAuto.Ensa.Affectation.Services;

import com.ParcAuto.Ensa.Affectation.Dto.TripDTO;
import com.ParcAuto.Ensa.Affectation.Entities.Trip;
import com.ParcAuto.Ensa.Affectation.Repositories.TripRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TripService {

//    private final TripRepository tripRepository;


//    @Autowired
//    public TripService(TripRepository tripRepository, ModelMapper modelMapper) {
//        this.tripRepository = tripRepository;
//        this.modelMapper = modelMapper;
//    }
//
//    public TripDTO getTripById(Long id) {
//        Trip trip = tripRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Trip not found with id: " + id));
//        return convertToDTO(trip);
//    }
//
//    public List<TripDTO> getAllTrips() {
//        List<Trip> trips = tripRepository.findAll();
//        return trips.stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }
//
//    public TripDTO createTrip(TripDTO tripDTO) {
//        Trip trip = convertToEntity(tripDTO);
//        Trip savedTrip = tripRepository.save(trip);
//        return convertToDTO(savedTrip);
//    }
//
//    public TripDTO updateTrip(Long id, TripDTO tripDTO) {
//        if (!tripRepository.existsById(id)) {
//            throw new IllegalArgumentException("Trip not found with id: " + id);
//        }
//        tripDTO.setId(id); // Ensure the correct ID is set
//        Trip trip = convertToEntity(tripDTO);
//        Trip updatedTrip = tripRepository.save(trip);
//        return convertToDTO(updatedTrip);
//    }
//
//    public void deleteTrip(Long id) {
//        if (!tripRepository.existsById(id)) {
//            throw new IllegalArgumentException("Trip not found with id: " + id);
//        }
//        tripRepository.deleteById(id);
//    }
//
//    private TripDTO convertToDTO(Trip trip) {
//        return modelMapper.map(trip, TripDTO.class);
//    }
//
//    private Trip convertToEntity(TripDTO tripDTO) {
//        return modelMapper.map(tripDTO, Trip.class);
//    }
}
