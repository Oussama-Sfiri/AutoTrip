package com.ParcAuto.Ensa.Affectation.Services;

import com.ParcAuto.Ensa.Affectation.Dto.PermisDTO;
import com.ParcAuto.Ensa.Affectation.Entities.Permis;
import com.ParcAuto.Ensa.Affectation.Repositories.PermisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PermisService {

    @Autowired
    private PermisRepository permisRepository;

    public PermisDTO getPermisByNumero(String numeroPermis) {
        Optional<Permis> permisOptional = permisRepository.findById(numeroPermis);

        if (permisOptional.isPresent()) {
            Permis permis = permisOptional.get();
            return permisToDTO(permis);
        } else {
            return null;
        }
    }

    public List<PermisDTO> getAllPermis() {
        List<Permis> permisList = permisRepository.findAll();
        return permisList.stream().map(this::permisToDTO).collect(Collectors.toList());
    }

    public PermisDTO createPermis(PermisDTO permisDTO) {
        Permis permis = dtoToPermis(permisDTO);
        Permis savedPermis = permisRepository.save(permis);
        return permisToDTO(savedPermis);
    }

    public PermisDTO updatePermis(String numeroPermis, PermisDTO permisDTO) {
        Optional<Permis> permisOptional = permisRepository.findById(numeroPermis);

        if (permisOptional.isPresent()) {
            Permis existingPermis = permisOptional.get();
            // Update properties of existing Permis with those from DTO
            existingPermis.setDateRemisePermis(permisDTO.getDateRemisePermis());
            existingPermis.setTypePermis(permisDTO.getTypePermis());

            Permis updatedPermis = permisRepository.save(existingPermis);
            return permisToDTO(updatedPermis);
        } else {
            return null;
        }
    }

    public void deletePermis(String numeroPermis) {
        permisRepository.deleteById(numeroPermis);
    }

    private PermisDTO permisToDTO(Permis permis) {
        if (permis == null) {
            return null;
        }
        PermisDTO permisDTO = new PermisDTO();
        permisDTO.setNumeroPermis(permis.getNumeroPermis());
        permisDTO.setDateRemisePermis(permis.getDateRemisePermis());
        permisDTO.setTypePermis(permis.getTypePermis());
        return permisDTO;
    }

    private Permis dtoToPermis(PermisDTO permisDTO) {
        if (permisDTO == null) {
            return null;
        }
        Permis permis = new Permis();
        permis.setNumeroPermis(permisDTO.getNumeroPermis());
        permis.setDateRemisePermis(permisDTO.getDateRemisePermis());
        permis.setTypePermis(permisDTO.getTypePermis());
        return permis;
    }
}
