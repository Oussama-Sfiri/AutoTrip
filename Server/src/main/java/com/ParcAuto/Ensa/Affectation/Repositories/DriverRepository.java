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

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByCin(String cin);

//    @Query("SELECT DISTINCT d FROM Driver d " +
//            "JOIN FETCH d.permis p " +
//            "JOIN FETCH p.permisRemises pr " +
//            "JOIN d.vacations v " +
//            "WHERE pr.type = :permitType " +
//            "AND d.disponibility = true " +
//            "AND (:departureDate NOT BETWEEN v.start AND v.end " +
//            "AND :arrivalDate NOT BETWEEN v.start AND v.end) " +
//            "AND (v.start NOT BETWEEN :departureDate AND :arrivalDate " +
//            "AND v.end NOT BETWEEN :departureDate AND :arrivalDate) " +
//            "AND NOT EXISTS (SELECT t FROM Trip t WHERE t.driver = d AND " +
//            "(:departureDate BETWEEN t.departureDate AND t.arrivalDate " +
//            "OR :arrivalDate BETWEEN t.departureDate AND t.arrivalDate " +
//            "OR t.departureDate BETWEEN :departureDate AND :arrivalDate " +
//            "OR t.arrivalDate BETWEEN :departureDate AND :arrivalDate) " +
//            "AND (:departureTime BETWEEN t.departureTime AND t.arrivalTime " +
//            "OR :arrivalTime BETWEEN t.departureTime AND t.arrivalTime " +
//            "OR t.departureTime BETWEEN :departureTime AND :arrivalTime " +
//            "OR t.arrivalTime BETWEEN :departureTime AND :arrivalTime))")
//    List<Driver> getAvailableDriversForTrip(@Param("permitType") PermisType permitType,
//                                            @Param("departureDate") Date departureDate,
//                                            @Param("arrivalDate") Date arrivalDate,
//                                            @Param("departureTime") Time departureTime,
//                                            @Param("arrivalTime") Time arrivalTime);


}





