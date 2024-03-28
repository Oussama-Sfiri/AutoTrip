package com.ParcAuto.Ensa.Affectation.Repositories;

import com.ParcAuto.Ensa.Affectation.Entities.PermisType;
import com.ParcAuto.Ensa.Affectation.Entities.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {



}