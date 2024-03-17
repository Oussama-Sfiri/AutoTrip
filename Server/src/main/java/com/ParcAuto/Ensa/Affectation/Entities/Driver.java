package com.ParcAuto.Ensa.Affectation.Entities;

import com.ParcAuto.Ensa.Affectation.Entities.Permis;
import com.ParcAuto.Ensa.Affectation.Entities.Trip;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String cin;

    @Column()
    private String nom;

    @Column()
    private String prenom;

    @Column()
    private Date date_naissance;

    @Column()
    private String addresse;

    @Column()
    private boolean disponibility;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Trip> trip;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_permis")
    private Permis permis;

}
