package com.ParcAuto.Ensa.Affectation.Entities;

import com.ParcAuto.Ensa.Affectation.Entities.Permis;
import com.ParcAuto.Ensa.Affectation.Entities.Trip;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(mappedBy = "driver", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Trip> trips;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_permis")
    private Permis permis;

    @OneToMany(mappedBy = "driver",fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vacation> vacations;



    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", cin='" + cin + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", date_naissance=" + date_naissance +
                ", addresse='" + addresse + '\'' +
                ", disponibility=" + disponibility +
                // Omitting trip and permis fields to avoid circular reference
                '}';
    }

}
