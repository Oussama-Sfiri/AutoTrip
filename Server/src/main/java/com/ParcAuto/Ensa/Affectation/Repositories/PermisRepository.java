package com.ParcAuto.Ensa.Affectation.Repositories;

import com.ParcAuto.Ensa.Affectation.Entities.Permis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermisRepository extends JpaRepository<Permis, Long> {
    Optional<Permis> findByNumPermis(Long numPermis);
}
