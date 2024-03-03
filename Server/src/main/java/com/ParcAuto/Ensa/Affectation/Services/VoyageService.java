package com.ParcAuto.Ensa.Affectation.Services;

import com.ParcAuto.Ensa.Affectation.Dto.VoyageDTO;
import com.ParcAuto.Ensa.Affectation.Entities.Voyage;
import com.ParcAuto.Ensa.Affectation.Repositories.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VoyageService {

    @Autowired
    private VoyageRepository voyageRepository;

    public VoyageDTO getVoyageById(Long id) {
        Optional<Voyage> voyageOptional = voyageRepository.findById(id);

        if (voyageOptional.isPresent()) {
            Voyage voyage = voyageOptional.get();
            return voyageToDTO(voyage);
        } else {
            throw new RuntimeException("Voyage non trouvé avec l'ID : " + id);
        }
    }

    public VoyageDTO createVoyage(VoyageDTO voyageDTO) {
        Voyage voyage = dtoToVoyage(voyageDTO);
        Voyage savedVoyage = voyageRepository.save(voyage);
        return voyageToDTO(savedVoyage);
    }

    public List<VoyageDTO> getAllVoyages() {
        List<Voyage> voyages = voyageRepository.findAll();
        return voyages.stream().map(this::voyageToDTO).collect(Collectors.toList());
    }

    public VoyageDTO updateVoyage(Long id, VoyageDTO voyageDTO) {
        Optional<Voyage> voyageOptional = voyageRepository.findById(id);

        if (voyageOptional.isPresent()) {
            Voyage existingVoyage = voyageOptional.get();
            // Update properties of the existing voyage with those from the DTO
            existingVoyage.setDateDepart(voyageDTO.getDateDepart());
            existingVoyage.setHeureDepart(voyageDTO.getHeureDepart());
            existingVoyage.setDateArriveePrev(voyageDTO.getDateArriveePrev());
            existingVoyage.setHeureArriveePrev(voyageDTO.getHeureArriveePrev());
            existingVoyage.setDepart(voyageDTO.getDepart());
            existingVoyage.setDestination(voyageDTO.getDestination());
            existingVoyage.setTypeVehicule(voyageDTO.getTypeVehicule());
            existingVoyage.setNombrePassagers(voyageDTO.getNombrePassagers());
            existingVoyage.setAutresDetails(voyageDTO.getAutresDetails());
            existingVoyage.setStatus(voyageDTO.getStatus());

            Voyage updatedVoyage = voyageRepository.save(existingVoyage);
            return voyageToDTO(updatedVoyage);
        } else {
            throw new RuntimeException("Voyage non trouvé avec l'ID : " + id);
        }
    }

    public void deleteVoyage(Long id) {
        if (voyageRepository.existsById(id)) {
            voyageRepository.deleteById(id);
        } else {
            throw new RuntimeException("Voyage non trouvé avec l'ID : " + id);
        }
    }

    private VoyageDTO voyageToDTO(Voyage voyage) {
        VoyageDTO voyageDTO = new VoyageDTO();
        voyageDTO.setId(voyage.getId());
        voyageDTO.setDateDepart(voyage.getDateDepart());
        voyageDTO.setHeureDepart(voyage.getHeureDepart());
        voyageDTO.setDateArriveePrev(voyage.getDateArriveePrev());
        voyageDTO.setHeureArriveePrev(voyage.getHeureArriveePrev());
        voyageDTO.setDepart(voyage.getDepart());
        voyageDTO.setDestination(voyage.getDestination());
        voyageDTO.setTypeVehicule(voyage.getTypeVehicule());
        voyageDTO.setNombrePassagers(voyage.getNombrePassagers());
        voyageDTO.setAutresDetails(voyage.getAutresDetails());
        voyageDTO.setStatus(voyage.getStatus());
        return voyageDTO;
    }

    private Voyage dtoToVoyage(VoyageDTO voyageDTO) {
        Voyage voyage = new Voyage();
        voyage.setId(voyageDTO.getId());
        voyage.setDateDepart(voyageDTO.getDateDepart());
        voyage.setHeureDepart(voyageDTO.getHeureDepart());
        voyage.setDateArriveePrev(voyageDTO.getDateArriveePrev());
        voyage.setHeureArriveePrev(voyageDTO.getHeureArriveePrev());
        voyage.setDepart(voyageDTO.getDepart());
        voyage.setDestination(voyageDTO.getDestination());
        voyage.setTypeVehicule(voyageDTO.getTypeVehicule());
        voyage.setNombrePassagers(voyageDTO.getNombrePassagers());
        voyage.setAutresDetails(voyageDTO.getAutresDetails());
        voyage.setStatus(voyageDTO.getStatus());
        return voyage;
    }
}
