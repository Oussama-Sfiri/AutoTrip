package com.ParcAuto.Ensa.Affectation.Services;

import com.ParcAuto.Ensa.Affectation.Dto.ConducteurDTO;
import com.ParcAuto.Ensa.Affectation.Entities.Conducteur;
import com.ParcAuto.Ensa.Affectation.Repositories.ConducteurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConducteurService {

    @Autowired
    private ConducteurRepository conducteurRepository;

    public ConducteurDTO getConducteurByMatricule(String matricule) {
        Optional<Conducteur> conducteurOptional = conducteurRepository.findByMatricule(matricule);

        if (conducteurOptional.isPresent()) {
            Conducteur conducteur = conducteurOptional.get();
            return conducteurToDTO(conducteur);
        } else {
            throw new RuntimeException("Conducteur non trouvé avec le matricule : " + matricule);
        }
    }

    public ConducteurDTO createConducteur(ConducteurDTO conducteurDTO) {
        Conducteur conducteur = dtoToConducteur(conducteurDTO);
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

    public ConducteurDTO updateConducteur(String matricule, ConducteurDTO conducteurDTO) {
        Optional<Conducteur> conducteurOptional = conducteurRepository.findByMatricule(matricule);

        if (conducteurOptional.isPresent()) {
            Conducteur existingConducteur = conducteurOptional.get();
            // Mettez à jour les propriétés du conducteur existant avec celles du DTO
            existingConducteur.setNom(conducteurDTO.getNom() != null ? conducteurDTO.getNom() : existingConducteur.getNom());
            existingConducteur.setPrenom(conducteurDTO.getPrenom() != null ? conducteurDTO.getPrenom() : existingConducteur.getPrenom());
            existingConducteur.setMatricule(conducteurDTO.getMatricule() != null ? conducteurDTO.getMatricule() : existingConducteur.getMatricule());
            existingConducteur.setDateNaissance(conducteurDTO.getDateNaissance() != null ? conducteurDTO.getDateNaissance() : existingConducteur.getDateNaissance());
            existingConducteur.setCin(conducteurDTO.getCin() != null ? conducteurDTO.getCin() : existingConducteur.getCin());

            Conducteur updatedConducteur = conducteurRepository.save(existingConducteur);
            return conducteurToDTO(updatedConducteur);
        } else {
            throw new RuntimeException("Conducteur non trouvé avec le matricule : " + matricule);
        }
    }

    @Transactional
    public ConducteurDTO deleteConducteur(String matricule) {
        Optional<Conducteur> conducteurDeletedOptional;
        if (conducteurRepository.existsByMatricule(matricule)) {
            conducteurDeletedOptional = conducteurRepository.findByMatricule(matricule);
            if (conducteurDeletedOptional.isPresent()){
                Conducteur deletedConducteur = conducteurDeletedOptional.get();
                ConducteurDTO deletedConducteurDTO = conducteurToDTO(deletedConducteur);
                Long deletedCount = conducteurRepository.deleteByMatricule(matricule);
                return deletedCount > 0 ? deletedConducteurDTO : null;
            }else {
                return null;
            }
        } else {
            throw new RuntimeException("Conducteur non trouvé avec le matricule : " + matricule);
        }
    }

    private ConducteurDTO conducteurToDTO(Conducteur conducteur) {
        ConducteurDTO conducteurDTO = new ConducteurDTO();
        conducteurDTO.setMatricule(conducteur.getMatricule());
        conducteurDTO.setNom(conducteur.getNom());
        conducteurDTO.setPrenom(conducteur.getPrenom());
        conducteurDTO.setCin(conducteur.getCin());
        conducteurDTO.setDateNaissance(conducteur.getDateNaissance());
        return conducteurDTO;
    }

    private Conducteur dtoToConducteur(ConducteurDTO conducteurDTO) {
        Conducteur conducteur = new Conducteur();
        conducteur.setMatricule(conducteurDTO.getMatricule() );
        conducteur.setNom(conducteurDTO.getNom());
        conducteur.setPrenom(conducteurDTO.getPrenom());
        conducteur.setCin(conducteurDTO.getCin());
        conducteur.setDateNaissance(conducteurDTO.getDateNaissance());
        return conducteur;
    }
}
