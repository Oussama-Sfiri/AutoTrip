package com.ParcAuto.Ensa.Affectation.Repositories;

import com.ParcAuto.Ensa.Affectation.Dto.TripDTO;
import com.ParcAuto.Ensa.Affectation.Entities.Driver;
import com.ParcAuto.Ensa.Affectation.Entities.PermisType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByCin(String cin);



    @Query("SELECT DISTINCT d FROM Driver d " +
            "JOIN FETCH d.permis p " +
            "JOIN FETCH p.permisRemises pr " +
            "WHERE pr.type = :permitType " +
            "AND d.disponibility = true")
    List<Driver> getAvailableDriversForTrip(PermisType permitType);

}

