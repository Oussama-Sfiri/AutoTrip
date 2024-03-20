package com.ParcAuto.Ensa.Affectation.mappers;

import com.ParcAuto.Ensa.Affectation.Dto.CarteGriseDTO;
import com.ParcAuto.Ensa.Affectation.Dto.VehiculeDTO;
import com.ParcAuto.Ensa.Affectation.Entities.CarteGrise;
import com.ParcAuto.Ensa.Affectation.Entities.Vehicule;
import org.springframework.beans.BeanUtils;

public class VehiculeMappers {

    public static Vehicule DTOToVehicule(VehiculeDTO vehiculeDTO) {
        Vehicule vehicule = new Vehicule();
        BeanUtils.copyProperties(vehiculeDTO, vehicule);
        if (vehiculeDTO.getCarteGrise() != null) {
            CarteGriseDTO carteGriseDTO = vehiculeDTO.getCarteGrise();
            CarteGrise carteGrise = new CarteGrise();
            BeanUtils.copyProperties(carteGriseDTO, carteGrise);
            vehicule.setCarteGrise(carteGrise);
        }
        return vehicule;
    }

    public static VehiculeDTO VehiculeToDTO(Vehicule vehicule) {
        VehiculeDTO vehiculeDTO = new VehiculeDTO();
        BeanUtils.copyProperties(vehicule, vehiculeDTO);
        if (vehicule.getCarteGrise() != null) {
            CarteGrise carteGrise = vehicule.getCarteGrise();
            CarteGriseDTO carteGriseDTO = new CarteGriseDTO();
            BeanUtils.copyProperties(carteGrise, carteGriseDTO);
            vehiculeDTO.setCarteGrise(carteGriseDTO);
        }
        return vehiculeDTO;
    }
}
