package com.ParcAuto.Ensa.Affectation.Repositories;

import com.ParcAuto.Ensa.Affectation.Entities.CarteGrise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteGriseRepository extends JpaRepository<CarteGrise, String> {
    // Add custom query methods if needed
}
