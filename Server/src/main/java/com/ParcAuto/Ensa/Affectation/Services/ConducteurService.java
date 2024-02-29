package com.ParcAuto.Ensa.Affectation.Services;

import com.ParcAuto.Ensa.Affectation.Dto.ConducteurDTO;
import com.ParcAuto.Ensa.Affectation.Entities.Conducteur;
import com.ParcAuto.Ensa.Affectation.Repositories.ConducteurRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ConducteurService {

    private final ModelMapper modelMapper;

    @Autowired
    private ConducteurRepository conducteurRepository;

    @Autowired
    public ConducteurService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ConducteurDTO getConducteurById(Long id) {
        Optional<Conducteur> conducteurOptional = conducteurRepository.findById(id);

        if (conducteurOptional.isPresent()) {
            Conducteur conducteur = conducteurOptional.get();
            return mapConducteurToDTO(conducteur);
        } else {
            // Gérer le cas où le conducteur n'est pas trouvé
            return null; // Ou lancez une exception
        }
    }

    public ConducteurDTO createConducteur(ConducteurDTO conducteurDTO) {
        Conducteur conducteur = mapDTOToConducteur(conducteurDTO);
        conducteur.setId(null); // Assurez-vous que l'ID est null pour la création
        Conducteur savedConducteur = conducteurRepository.save(conducteur);
        return mapConducteurToDTO(savedConducteur);
    }

    public List<ConducteurDTO> getAllConducteurs() {
        List<Conducteur> conducteurs = conducteurRepository.findAll();
        return conducteurs.stream()
                .map(this::mapConducteurToDTO)
                .collect(Collectors.toList());
    }

    public void deleteConducteur(Long id) {
        conducteurRepository.deleteById(id);
    }

    // Ajoutez d'autres méthodes pour les opérations CRUD sur les conducteurs

    private ConducteurDTO mapConducteurToDTO(Conducteur conducteur) {
        return modelMapper.map(conducteur, ConducteurDTO.class);
    }

    private Conducteur mapDTOToConducteur(ConducteurDTO conducteurDTO) {
        return modelMapper.map(conducteurDTO, Conducteur.class);
    }
}
