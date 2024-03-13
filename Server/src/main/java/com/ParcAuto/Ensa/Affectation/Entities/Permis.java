package com.ParcAuto.Ensa.Affectation.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Permis {
    @Id
    private String numeroPermis;
    private LocalDate dateRemisePermis;
    private String typePermis;

    @JsonManagedReference
    @OneToOne(mappedBy = "permis")
    private Conducteur conducteur;
}
