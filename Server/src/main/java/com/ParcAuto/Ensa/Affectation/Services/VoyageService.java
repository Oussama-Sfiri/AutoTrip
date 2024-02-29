package com.ParcAuto.Ensa.Affectation.Services;

import com.ParcAuto.Ensa.Affectation.Dto.VoyageDTO;
import com.ParcAuto.Ensa.Affectation.Repositories.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoyageService {

    @Autowired
    private VoyageRepository voyageRepository;

    public VoyageDTO getVoyageById(Long id) {
        // Implémentez la logique pour récupérer un voyage par ID depuis le repository
    }

    public VoyageDTO createVoyage(VoyageDTO voyageDTO) {
        // Implémentez la logique pour créer un voyage en utilisant le repository
    }

    // Ajoutez les méthodes pour les opérations CRUD sur les voyages
}

