package com.ParcAuto.Ensa.Affectation.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String marque;

    @Column()
    private String type;

    @Column()
    private int kilemotrage;

    @Enumerated(EnumType.STRING)
    private PermisType typePermisRequis;

    @Column()
    private String assurance;

    @Column()
    private Date visiteTech;

    @Column()
    private Date vignette;

    @Column()
    private boolean disponibilite;

    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL)
    private List<Trip> trip;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_carteGrise", referencedColumnName = "id")
    private CarteGrise carteGrise;


}
