package com.ParcAuto.Ensa.Affectation.Entities;

import lombok.Data;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Permis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column()
    private int num_permis;

    @Column()
    private Date fin_validite;

    @OneToOne(mappedBy = "permis", cascade = CascadeType.ALL)
    private Driver driver;

    @OneToMany(mappedBy = "permis", cascade = CascadeType.ALL)
    private List<PermisRemise> permisTypes;
}
