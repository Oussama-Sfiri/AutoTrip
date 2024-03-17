package com.ParcAuto.Ensa.Affectation.Services;

import com.ParcAuto.Ensa.Affectation.Dto.PermisDTO;
import com.ParcAuto.Ensa.Affectation.Entities.Permis;
import com.ParcAuto.Ensa.Affectation.Repositories.PermisRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermisService {

    @Autowired
    private PermisRepository permisRepository;

    public PermisDTO createPermis(PermisDTO permisDTO) {
        Permis permis = convertToEntity(permisDTO);
        permis = permisRepository.save(permis);
        return convertToDTO(permis);
    }

    public List<PermisDTO> getAllPermis() {
        List<Permis> permisList = permisRepository.findAll();
        return permisList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public PermisDTO getPermisById(Long id) throws Exception {
        Permis permis = permisRepository.findById(id)
                .orElseThrow(() -> new Exception("Permis not found with ID: " ));
        return convertToDTO(permis);
    }

    public void deletePermis(Long id) {
        permisRepository.deleteById(id);
    }

    private Permis convertToEntity(PermisDTO permisDTO) {
        Permis permis = new Permis();
        BeanUtils.copyProperties(permisDTO, permis);
        return permis;
    }

    private PermisDTO convertToDTO(Permis permis) {
        PermisDTO permisDTO = new PermisDTO();
        BeanUtils.copyProperties(permis, permisDTO);
        return permisDTO;
    }
}
