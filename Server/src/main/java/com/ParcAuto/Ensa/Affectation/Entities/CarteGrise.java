package com.ParcAuto.Ensa.Affectation.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class CarteGrise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String num_Immatriculation;

    @Column()
    private String imm_anterieure;

    @Column()
    private LocalDate premiere_mise_Circulation;

    @Column()
    private LocalDate mc_maroc;

    @Column
    private LocalDate mutation;

    @Column()
    private String usageCG;

    @Column()
    private String proprietaire;

    @Column()
    private String adresse;

    @Column()
    private LocalDate fin_validite;

    @OneToOne(mappedBy = "carteGrise", cascade = CascadeType.ALL)
    private Vehicule vehicule;

}
