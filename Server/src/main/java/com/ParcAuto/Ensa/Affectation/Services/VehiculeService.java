package com.ParcAuto.Ensa.Affectation.Services;

import com.ParcAuto.Ensa.Affectation.Dto.ConducteurDTO;
import com.ParcAuto.Ensa.Affectation.Dto.VehiculeDTO;
import com.ParcAuto.Ensa.Affectation.Entities.Conducteur;
import com.ParcAuto.Ensa.Affectation.Entities.Vehicule;
import com.ParcAuto.Ensa.Affectation.Repositories.VehiculeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehiculeService {

    private final VehiculeRepository vehiculeRepository;

    @Autowired
    public VehiculeService(VehiculeRepository vehiculeRepository) {
        this.vehiculeRepository = vehiculeRepository;
    }

    public VehiculeDTO getVehiculeByImmatriculation(String immatriculation) {
        Optional<Vehicule> vehiculeOptional = vehiculeRepository.findByImmatriculation(immatriculation);

        if (vehiculeOptional.isPresent()) {
            Vehicule vehicule = vehiculeOptional.get();
            return vehiculeToDTO(vehicule);
        } else {
            throw new RuntimeException("Vehicule non trouvée avec l'immatriculation : " + immatriculation);
        }
    }

    public List<VehiculeDTO> getAllVehicules() {
        List<Vehicule> vehicules = vehiculeRepository.findAll();
        List<VehiculeDTO> vehiculeDTOS = new ArrayList<>();
        for (Vehicule vehicule : vehicules) {
            vehiculeDTOS.add(vehiculeToDTO(vehicule));
        }
        return vehiculeDTOS;
    }

    public VehiculeDTO createVehicule(VehiculeDTO vehiculeDTO) {
        Vehicule vehicule = dtoToVehicule(vehiculeDTO);
        Vehicule savedVehicule = vehiculeRepository.save(vehicule);
        VehiculeDTO createdVehiculeDTO = vehiculeToDTO(savedVehicule);
        return createdVehiculeDTO;
    }

    public VehiculeDTO updateVehicule(String immatriculation, VehiculeDTO vehiculeDTO) {
        Optional<Vehicule> vehiculeOptional = vehiculeRepository.findByImmatriculation(immatriculation);

        if (vehiculeOptional.isPresent()) {
            Vehicule existingVehicule = vehiculeOptional.get();
            // Mettez à jour les propriétés du conducteur existant avec celles du DTO
            existingVehicule.setImmatriculation(vehiculeDTO.getImmatriculation() != null ? vehiculeDTO.getImmatriculation() : existingVehicule.getImmatriculation());
            existingVehicule.setMarque(vehiculeDTO.getMarque() != null ? vehiculeDTO.getMarque() : existingVehicule.getMarque());
            existingVehicule.setModele(vehiculeDTO.getModele() != null ? vehiculeDTO.getModele() : existingVehicule.getModele());
            existingVehicule.setType(vehiculeDTO.getType() != null ? vehiculeDTO.getType() : existingVehicule.getType());
            existingVehicule.setTypePermisRequis(vehiculeDTO.getTypePermisRequis() != null ? vehiculeDTO.getTypePermisRequis() : existingVehicule.getTypePermisRequis());
            existingVehicule.setEquipementsSpeciaux(vehiculeDTO.getEquipementsSpeciaux() != null ? vehiculeDTO.getEquipementsSpeciaux() : existingVehicule.getEquipementsSpeciaux());
            existingVehicule.setKilometrage(vehiculeDTO.getKilometrage() != null ? vehiculeDTO.getKilometrage() : existingVehicule.getKilometrage());
//            existingVehicule.setDisponible(vehiculeDTO.getDisponible() != null ? vehiculeDTO.getDisponible() : existingVehicule.isDisponible());


            Vehicule updatedVehicule = vehiculeRepository.save(existingVehicule);
            return vehiculeToDTO(updatedVehicule);
        } else {
            throw new RuntimeException("Vehicule non trouvé avec l'immatriculation : " + immatriculation);
        }
    }

    @Transactional
    public VehiculeDTO deleteVehicule(String immatriculation) {
        Optional<Vehicule> vehiculeDeletedOptional;
        if (vehiculeRepository.existsByImmatriculation(immatriculation)) {
            vehiculeDeletedOptional = vehiculeRepository.findByImmatriculation(immatriculation);
            if (vehiculeDeletedOptional.isPresent()){
                Vehicule deletedVehicule = vehiculeDeletedOptional.get();
                VehiculeDTO deletedVehiculeDTO = vehiculeToDTO(deletedVehicule);
                Long deletedCount = vehiculeRepository.deleteByImmatriculation(immatriculation);
                return deletedCount > 0 ? deletedVehiculeDTO : null;
            }else {
                return null;
            }
        } else {
            throw new RuntimeException("Vehicule non trouvé avec l'immatriculation : " + immatriculation);
        }
    }

    // Utility method to convert Vehicule to VehiculeDTO
    private VehiculeDTO vehiculeToDTO(Vehicule vehicule) {
        VehiculeDTO vehiculeDTO = new VehiculeDTO();
        vehiculeDTO.setImmatriculation(vehicule.getImmatriculation());
        vehiculeDTO.setMarque(vehicule.getMarque());
        vehiculeDTO.setModele(vehicule.getModele());
        vehiculeDTO.setType(vehicule.getType());
        vehiculeDTO.setKilometrage(vehicule.getKilometrage());
//        vehiculeDTO.setDisponible(vehicule.isDisponible());
        vehiculeDTO.setTypePermisRequis(vehicule.getTypePermisRequis());
        vehiculeDTO.setEquipementsSpeciaux(vehicule.getEquipementsSpeciaux());
        return vehiculeDTO;
    }

    // Utility method to convert VehiculeDTO to Vehicule
    private Vehicule dtoToVehicule(VehiculeDTO vehiculeDTO) {
        Vehicule vehicule = new Vehicule();
        vehicule.setImmatriculation(vehiculeDTO.getImmatriculation());
        vehicule.setMarque(vehiculeDTO.getMarque());
        vehicule.setModele(vehiculeDTO.getModele());
        vehicule.setType(vehiculeDTO.getType());
        vehicule.setKilometrage(vehiculeDTO.getKilometrage());
//        vehicule.setDisponible(vehiculeDTO.getDisponible());
        vehicule.setTypePermisRequis(vehiculeDTO.getTypePermisRequis());
        vehicule.setEquipementsSpeciaux(vehiculeDTO.getEquipementsSpeciaux());
        return vehicule;
    }
}
