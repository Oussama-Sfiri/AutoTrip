package com.ParcAuto.Ensa.Affectation.mappers;

import com.ParcAuto.Ensa.Affectation.Dto.CarteGriseDTO;
import com.ParcAuto.Ensa.Affectation.Dto.VehiculeDTO;
import com.ParcAuto.Ensa.Affectation.Entities.CarteGrise;
import com.ParcAuto.Ensa.Affectation.Entities.Vehicule;
import org.springframework.beans.BeanUtils;

public class VehiculeMappers {

    public static Vehicule DTOToVehicule(VehiculeDTO vehiculeDTO, CarteGrise carteGrise) {
        Vehicule vehicule = new Vehicule();
        BeanUtils.copyProperties(vehiculeDTO, vehicule);
        vehicule.setCarteGrise(carteGrise); // Set the corresponding CarteGrise
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
