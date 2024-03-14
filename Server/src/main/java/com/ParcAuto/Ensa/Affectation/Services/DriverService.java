package com.ParcAuto.Ensa.Affectation.Services;

import com.ParcAuto.Ensa.Affectation.Dto.DriverDTO;
import com.ParcAuto.Ensa.Affectation.Dto.PermisDTO;
import com.ParcAuto.Ensa.Affectation.Entities.Driver;
import com.ParcAuto.Ensa.Affectation.Entities.Permis;
import com.ParcAuto.Ensa.Affectation.Entities.PermisRemise;
import com.ParcAuto.Ensa.Affectation.Entities.PermisType;
import com.ParcAuto.Ensa.Affectation.Repositories.DriverRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    public DriverDTO createDriver(DriverDTO driverDTO) {
        Driver driver = convertToEntity(driverDTO);
        driver = driverRepository.save(driver);
        return convertToDTO(driver);
    }

    public List<DriverDTO> getAllDrivers() {
        List<Driver> drivers = driverRepository.findAll();
        return drivers.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public DriverDTO getDriverById(String id) throws Exception {
        Driver driver = driverRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new Exception("Driver not found with ID: " + id));
        return convertToDTO(driver);
    }

    public void deleteDriver(String id) {
        driverRepository.deleteById(Long.valueOf(id));
    }

    public static Driver convertToEntity(DriverDTO driverDTO) {
        Driver driver = new Driver();
        BeanUtils.copyProperties(driverDTO, driver);

        // Convert PermisDTO to Permis entity
        if (driverDTO.getPermis() != null) {
            PermisDTO permisDTO = driverDTO.getPermis();
            Permis permis = new Permis();
            BeanUtils.copyProperties(permisDTO, permis);

            // Convert types List<String> to List<PermisType> and create PermisRemise instances
            if (permisDTO.getTypes() != null) {
                List<PermisRemise> permisRemises = new ArrayList<>();
                for (String type : permisDTO.getTypes()) {
                    PermisRemise permisRemise = new PermisRemise();
                    permisRemise.setType(PermisType.valueOf(type));
                    permisRemise.setDate_remise_permis(permisDTO.getDateRemisePermis());
                    permisRemise.setLieu_remise_permis(permisDTO.getLieuRemisePermis());
                    permisRemises.add(permisRemise);
                }
            }

            driver.setPermis(permis);
        }

        return driver;
    }



    private DriverDTO convertToDTO(Driver driver) {
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setId(driver.getId());
        driverDTO.setCin(driver.getCin());
        driverDTO.setNom(driver.getNom());
        driverDTO.setPrenom(driver.getPrenom());
        driverDTO.setDateNaissance(driver.getDate_naissance());
        driverDTO.setAdresse(driver.getAddresse());
        driverDTO.setDisponibility(driver.isDisponibility());

        // Convert Permis to PermisDTO
        if (driver.getPermis() != null) {
            PermisDTO permisDTO = new PermisDTO();
            permisDTO.setId(driver.getPermis().getId());
            permisDTO.setNumPermis(driver.getPermis().getNum_permis());
            permisDTO.setFinValidite(driver.getPermis().getFin_validite());

            // Fetch permisRemise list from Permis entity
            List<PermisRemise> permisRemiseList = driver.getPermis().getPermisTypes();
            if (!permisRemiseList.isEmpty()) {
                // Assuming only one PermisRemise associated with a Permis
                PermisRemise permisRemise = permisRemiseList.get(0);
                permisDTO.setDateRemisePermis(permisRemise.getDate_remise_permis());
                permisDTO.setLieuRemisePermis(permisRemise.getLieu_remise_permis());
            }

            // Fetching types from all permisRemise associated with Permis
            List<String> types = new ArrayList<>();
            for (PermisRemise permisRemise : permisRemiseList) {
                types.add(permisRemise.getType().toString());
            }
            permisDTO.setTypes(types);

            driverDTO.setPermis(permisDTO);
        }

        return driverDTO;
    }




}
