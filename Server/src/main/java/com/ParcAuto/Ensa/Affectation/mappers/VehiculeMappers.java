package com.ParcAuto.Ensa.Affectation.mappers;

import com.ParcAuto.Ensa.Affectation.Dto.VehiculeDTO;
import com.ParcAuto.Ensa.Affectation.Entities.Vehicule;
import org.springframework.beans.BeanUtils;


public class VehiculeMappers {

    public static VehiculeDTO VehiculeToDTO(Vehicule vehicule) {
        VehiculeDTO vehiculeDTO = new VehiculeDTO();
        BeanUtils.copyProperties(vehicule, vehiculeDTO);
        return vehiculeDTO;
    }

    public static Vehicule DTOToVehicule(VehiculeDTO vehiculeDTO) {
        Vehicule vehicule = new Vehicule();
        BeanUtils.copyProperties(vehiculeDTO, vehicule);
        return vehicule;
    }
}