package com.ParcAuto.Ensa.Affectation.Entities;

import lombok.Data;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Permis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "numPermis")
    private Long numPermis;

    @Column()
    private LocalDate fin_validite;

    @OneToOne(mappedBy = "permis", cascade = CascadeType.ALL)
    private Driver driver;

    @OneToMany(mappedBy = "permis", cascade = CascadeType.ALL)
    private List<PermisRemise> permisRemises;


    @Override
    public String toString() {
        return "Permis{" +
                "id=" + id +
                ", numPermis=" + numPermis +
                ", fin_validite=" + fin_validite +

                '}';
    }
}
