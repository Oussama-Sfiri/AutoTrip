package com.ParcAuto.Ensa.Affectation.mappers;

import com.ParcAuto.Ensa.Affectation.Dto.CarteGriseDTO;
import com.ParcAuto.Ensa.Affectation.Entities.CarteGrise;
import org.springframework.beans.BeanUtils;

public class CarteGriseMappers {
    public static CarteGrise DTOToCarteGrise(CarteGriseDTO carteGriseDTO) {
        CarteGrise carteGrise = new CarteGrise();
        BeanUtils.copyProperties(carteGriseDTO, carteGrise);
        return carteGrise;
    }

    public static CarteGriseDTO CarteGriseToDTO(CarteGrise carteGrise) {
        CarteGriseDTO carteGriseDTO = new CarteGriseDTO();
        BeanUtils.copyProperties(carteGrise, carteGriseDTO);
        return carteGriseDTO;
    }
}
