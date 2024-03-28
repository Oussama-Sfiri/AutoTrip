package com.ParcAuto.Ensa.Affectation.Entities;

import jakarta.persistence.*;
import lombok.Data;
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
    private Date premiere_mise_Circulation;

    @Column()
    private Date mc_maroc;

    @Column
    private Date mutation;

    @Column()
    private String usageCG;

    @Column()
    private String proprietaire;

    @Column()
    private String adresse;

    @Column()
    private Date fin_validite;

    @OneToOne(mappedBy = "carteGrise", cascade = CascadeType.ALL)
    private Vehicule vehicule;

}
