package com.ParcAuto.Ensa.Affectation.Repositories;

import com.ParcAuto.Ensa.Affectation.Entities.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {
}