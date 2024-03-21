package com.ParcAuto.Ensa.Affectation.mappers;

import com.ParcAuto.Ensa.Affectation.Dto.VacationDTO;
import com.ParcAuto.Ensa.Affectation.Entities.Vacation;
import org.springframework.beans.BeanUtils;

public class VacationMappers {
    public static Vacation DtoTOVacation(VacationDTO vacationDTO) {
        Vacation vacation = new Vacation();
        BeanUtils.copyProperties(vacationDTO, vacation);
        return vacation;
    }

    public static VacationDTO VacationTODto(Vacation vacation) {
        VacationDTO vacationDTO = new VacationDTO();
        BeanUtils.copyProperties(vacation, vacationDTO);
        if (vacation.getDriver() != null) {
            vacationDTO.setDriverId(vacation.getDriver().getId());
        }
        return vacationDTO;
    }

}
