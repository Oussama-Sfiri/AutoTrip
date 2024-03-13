package com.ParcAuto.Ensa.Affectation.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Conducteur {

    @Id
    private String matricule;

    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String cin;
    private String permis_id;

//    @OneToMany(targetEntity = Voyage.class, mappedBy = "conducteur", cascade = CascadeType.ALL)
//    private List<Voyage> voyages;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "permis_id", insertable = false, updatable = false)
    private Permis permis;

    @Override
    public String toString() {
        return "Conducteur{" +
                "matricule='" + matricule + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", cin='" + cin + '\'' +
                // Exclude Permis to avoid infinite loop
                '}';
    }

}
