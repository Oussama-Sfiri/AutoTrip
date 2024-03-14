package com.ParcAuto.Ensa.Affectation.Entities;

import lombok.Data;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Data
public class PermisRemise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PermisType type;

    @Column()
    private Date date_remise_permis;

    @Column()
    private String lieu_remise_permis;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_permis")
    private Permis permis;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_driver")
    private Driver driver;
}
