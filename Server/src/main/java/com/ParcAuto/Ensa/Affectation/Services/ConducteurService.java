package com.ParcAuto.Ensa.Affectation.Services;

import com.ParcAuto.Ensa.Affectation.Dto.ConducteurDTO;
import com.ParcAuto.Ensa.Affectation.Entities.Conducteur;
import com.ParcAuto.Ensa.Affectation.Repositories.ConducteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConducteurService {

    @Autowired
    private ConducteurRepository conducteurRepository;

    public ConducteurDTO getConducteurById(Long id) {
        Optional<Conducteur> conducteurOptional = conducteurRepository.findById(id);

        if (conducteurOptional.isPresent()) {
            Conducteur conducteur = conducteurOptional.get();
            return conducteurToDTO(conducteur);
        } else {
            throw new RuntimeException("Conducteur non trouvé avec l'ID : " + id);
        }
    }

    public ConducteurDTO createConducteur(ConducteurDTO conducteurDTO) {
        Conducteur conducteur = dtoToConducteur(conducteurDTO);
        conducteur.setMatricule(null); // Assurez-vous que l'ID est null pour la création
        Conducteur savedConducteur = conducteurRepository.save(conducteur);
        ConducteurDTO createdConducteurDTO = conducteurToDTO(savedConducteur);
        return createdConducteurDTO;
    }

    public List<ConducteurDTO> getAllConducteurs() {
        List<Conducteur> conducteurs = conducteurRepository.findAll();
        List<ConducteurDTO> conducteurDTOs = new ArrayList<>();
        for (Conducteur conducteur : conducteurs) {
            conducteurDTOs.add(conducteurToDTO(conducteur));
        }
        return conducteurDTOs;
    }

    public ConducteurDTO updateConducteur(Long id, ConducteurDTO conducteurDTO) {
        Optional<Conducteur> conducteurOptional = conducteurRepository.findById(id);

        if (conducteurOptional.isPresent()) {
            Conducteur existingConducteur = conducteurOptional.get();
            // Mettez à jour les propriétés du conducteur existant avec celles du DTO
            existingConducteur.setNom(conducteurDTO.getNom());
            existingConducteur.setPrenom(conducteurDTO.getPrenom());
            existingConducteur.setMatricule(conducteurDTO.getMatricule());
            // Mettez à jour d'autres propriétés selon vos besoins

            Conducteur updatedConducteur = conducteurRepository.save(existingConducteur);
            return conducteurToDTO(updatedConducteur);
        } else {
            // Lancez une exception personnalisée si le conducteur n'est pas trouvé
            return null;
        }
    }

    public void deleteConducteur(Long id) {
        if (conducteurRepository.existsById(id)) {
            conducteurRepository.deleteById(id);
        } else {
            throw new RuntimeException("Conducteur non trouvé avec l'ID : " + id);
        }
    }

    // Ajoutez d'autres méthodes pour les opérations CRUD sur les conducteurs

    private ConducteurDTO conducteurToDTO(Conducteur conducteur) {
        ConducteurDTO conducteurDTO = new ConducteurDTO();
        conducteurDTO.setMatricule(conducteur.getMatricule());
        conducteurDTO.setNom(conducteur.getNom());
        conducteurDTO.setPrenom(conducteur.getPrenom());
        // ... définissez d'autres propriétés
        return conducteurDTO;
    }

    private Conducteur dtoToConducteur(ConducteurDTO conducteurDTO) {
        Conducteur conducteur = new Conducteur();
        conducteur.setNom(conducteurDTO.getNom());
        conducteur.setPrenom(conducteurDTO.getPrenom());
        // ... définissez d'autres propriétés
        return conducteur;
    }
}
