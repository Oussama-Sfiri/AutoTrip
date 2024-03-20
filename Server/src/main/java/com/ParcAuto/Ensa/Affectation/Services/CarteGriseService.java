package com.ParcAuto.Ensa.Affectation.Services;

import com.ParcAuto.Ensa.Affectation.Dto.CarteGriseDTO;
import com.ParcAuto.Ensa.Affectation.Entities.CarteGrise;
import com.ParcAuto.Ensa.Affectation.Repositories.CarteGriseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarteGriseService {
    private final CarteGriseRepository carteGriseRepository;

    @Autowired
    public CarteGriseService(CarteGriseRepository carteGriseRepository) {
        this.carteGriseRepository = carteGriseRepository;
    }

    public CarteGriseDTO createCarteGrise(CarteGriseDTO carteGriseDTO) {
        CarteGrise carteGrise = new CarteGrise();
        BeanUtils.copyProperties(carteGriseDTO, carteGrise);
        CarteGrise savedCarteGrise = carteGriseRepository.save(carteGrise);
        return convertToDto(savedCarteGrise);
    }

    public List<CarteGriseDTO> getAllCarteGrise() {
        List<CarteGrise> carteGriseList = carteGriseRepository.findAll();
        return carteGriseList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private CarteGriseDTO convertToDto(CarteGrise carteGrise) {
        CarteGriseDTO carteGriseDTO = new CarteGriseDTO();
        BeanUtils.copyProperties(carteGrise, carteGriseDTO);
        return carteGriseDTO;
    }

}
