package com.ParcAuto.Ensa.Affectation.Repositories;

import com.ParcAuto.Ensa.Affectation.Entities.Driver;
import com.ParcAuto.Ensa.Affectation.Entities.Permis;
import com.ParcAuto.Ensa.Affectation.Entities.PermisRemise;
import com.ParcAuto.Ensa.Affectation.Entities.PermisType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface PermisRemiseRepository extends JpaRepository<PermisRemise, Long> {

}
