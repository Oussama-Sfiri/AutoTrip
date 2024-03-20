package com.ParcAuto.Ensa.Affectation.Services;

import com.ParcAuto.Ensa.Affectation.Dto.CarteGriseDTO;
import com.ParcAuto.Ensa.Affectation.Dto.VehiculeDTO;
import com.ParcAuto.Ensa.Affectation.Entities.CarteGrise;
import com.ParcAuto.Ensa.Affectation.Entities.Vehicule;
import com.ParcAuto.Ensa.Affectation.Repositories.VehiculeRepository;
import com.ParcAuto.Ensa.Affectation.mappers.CarteGriseMappers;
import com.ParcAuto.Ensa.Affectation.mappers.VehiculeMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehiculeService {

    private final VehiculeRepository vehiculeRepository;

    @Autowired
    public VehiculeService(VehiculeRepository vehiculeRepository) {
        this.vehiculeRepository = vehiculeRepository;
    }

    public VehiculeDTO createVehicule(VehiculeDTO vehiculeDTO) {
        CarteGriseDTO carteGriseDTO = vehiculeDTO.getCarteGrise();
        CarteGrise carteGrise = CarteGriseMappers.DTOToCarteGrise(carteGriseDTO);
        Vehicule vehicule = VehiculeMappers.DTOToVehicule(vehiculeDTO, carteGrise);
        vehicule = vehiculeRepository.save(vehicule);
        return VehiculeMappers.VehiculeToDTO(vehicule);
    }

    public List<VehiculeDTO> getAllVehicles() {
        List<Vehicule> allVehicles = vehiculeRepository.findAll();
        return allVehicles.stream().map(VehiculeMappers::VehiculeToDTO).collect(Collectors.toList());
    }

    public VehiculeDTO getVehiculeById(Long id) {
        Vehicule vehicule = vehiculeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));
        return VehiculeMappers.VehiculeToDTO(vehicule);
    }
}
