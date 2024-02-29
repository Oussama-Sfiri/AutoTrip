package com.ParcAuto.Ensa.Affectation.Services;

import com.ParcAuto.Ensa.Affectation.Dto.VehiculeDTO;
import com.ParcAuto.Ensa.Affectation.Entities.Vehicule;
import com.ParcAuto.Ensa.Affectation.Repositories.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculeService {

    private final VehiculeRepository vehiculeRepository;

    @Autowired
    public VehiculeService(VehiculeRepository vehiculeRepository) {
        this.vehiculeRepository = vehiculeRepository;
    }

    public List<Vehicule> getAllVehicules() {
        return vehiculeRepository.findAll();
    }

    public Vehicule getVehiculeById(Long id) {
        return vehiculeRepository.findById(id).orElse(null);
    }

    public Vehicule saveVehicule(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    public void deleteVehicule(Long id) {
        vehiculeRepository.deleteById(id);
    }

    public VehiculeDTO createVehicule(VehiculeDTO vehiculeDTO) {
        Vehicule vehicule = new Vehicule();
        vehicule.setModele(vehiculeDTO.getModele());
        // Set other properties accordingly

        Vehicule savedVehicule = vehiculeRepository.save(vehicule);

        // Convert savedVehicule back to VehiculeDTO and return
        return convertToDTO(savedVehicule);
    }

    public VehiculeDTO updateVehicule(Long id, VehiculeDTO vehiculeDTO) {
        Optional<Vehicule> optionalVehicule = vehiculeRepository.findById(id);
        if (optionalVehicule.isPresent()) {
            Vehicule vehicule = optionalVehicule.get();
            vehicule.setModele(vehiculeDTO.getModele());
            // Update other properties accordingly

            Vehicule updatedVehicule = vehiculeRepository.save(vehicule);

            // Convert updatedVehicule back to VehiculeDTO and return
            return convertToDTO(updatedVehicule);
        } else {
            // Handle the case where the Vehicule with the given id is not found
            return null;
        }
    }

    // Utility method to convert Vehicule to VehiculeDTO
    private VehiculeDTO convertToDTO(Vehicule vehicule) {
        VehiculeDTO vehiculeDTO = new VehiculeDTO();
        vehiculeDTO.setImmatriculation(vehicule.getImmatriculation());
        // Convert other properties accordingly

        return vehiculeDTO;
    }
}
