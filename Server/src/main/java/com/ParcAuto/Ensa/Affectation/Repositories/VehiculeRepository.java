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


    @Query("SELECT v FROM Vehicule v WHERE v.disponibilite = true AND v.typePermisRequis = :permitType " +
            "AND NOT EXISTS (SELECT t FROM Trip t WHERE t.vehicule = v AND " +
            "(:departureDate BETWEEN t.departureDate AND t.arrivalDate " +
            "OR :arrivalDate BETWEEN t.departureDate AND t.arrivalDate " +
            "OR t.departureDate BETWEEN :departureDate AND :arrivalDate " +
            "OR t.arrivalDate BETWEEN :departureDate AND :arrivalDate))")
    List<Vehicule> getAvailableVehiclesForTrip(@Param("permitType") PermisType permitType,
                                               @Param("departureDate") Date departureDate,
                                               @Param("arrivalDate") Date arrivalDate);

}