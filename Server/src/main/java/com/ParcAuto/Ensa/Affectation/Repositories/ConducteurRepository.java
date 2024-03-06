package com.ParcAuto.Ensa.Affectation.Repositories;

import com.ParcAuto.Ensa.Affectation.Entities.Conducteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConducteurRepository extends JpaRepository<Conducteur, Long> {
    Optional<Conducteur> findByMatricule(String matricule);
    Boolean existsByMatricule(String matricule);
    Long deleteByMatricule(String matricule);
}

