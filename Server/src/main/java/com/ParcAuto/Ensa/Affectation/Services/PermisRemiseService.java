package com.ParcAuto.Ensa.Affectation.Services;

import com.ParcAuto.Ensa.Affectation.Dto.DriverDTO;
import com.ParcAuto.Ensa.Affectation.Dto.PermisRemiseDTO;
import com.ParcAuto.Ensa.Affectation.Entities.Driver;
import com.ParcAuto.Ensa.Affectation.Entities.Permis;
import com.ParcAuto.Ensa.Affectation.Entities.PermisRemise;
import com.ParcAuto.Ensa.Affectation.Repositories.DriverRepository;
import com.ParcAuto.Ensa.Affectation.Repositories.PermisRemiseRepository;
import com.ParcAuto.Ensa.Affectation.Repositories.PermisRepository;
import com.ParcAuto.Ensa.Affectation.mappers.PermisRemiseMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

import static com.ParcAuto.Ensa.Affectation.mappers.PermisRemiseMappers.DTOToPermisRemise;

@Service
public class PermisRemiseService {

    @Autowired
    private PermisRemiseRepository permisRemiseRepository;

    @Autowired
    private PermisRepository permisRepository;

    @Autowired
    private DriverRepository driverRepository;

    public PermisRemiseDTO createPermisRemise(PermisRemiseDTO permisRemiseDTO) {
        Optional<Driver> driverOptional = driverRepository.findByCin(permisRemiseDTO.getCin());
        Optional<Permis> permisOptional = permisRepository.findByNumPermis(permisRemiseDTO.getNumPermis());
        Driver driver = driverOptional.orElseThrow(() -> new NoSuchElementException("Driver not found"));
        Permis permis = permisOptional.orElseThrow(() -> new NoSuchElementException("Permis not found"));
        PermisRemise permisRemise = DTOToPermisRemise(permisRemiseDTO, permis, driver);
        permisRemise = permisRemiseRepository.save(permisRemise);
        return PermisRemiseMappers.PermisRemiseToDTO(permisRemise);
    }




    public PermisRemiseDTO getPermisRemiseById(Long id) throws Exception {
        PermisRemise permisRemise = permisRemiseRepository.findById(id)
                .orElseThrow(() -> new Exception("Driver not found with ID: " + id));
        return PermisRemiseMappers.PermisRemiseToDTO(permisRemise);
    }
}