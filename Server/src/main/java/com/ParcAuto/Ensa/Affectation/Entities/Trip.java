package com.ParcAuto.Ensa.Affectation.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String departure;

    @Column()
    private String destination;

    @Column()
    private Date departureDate;

    @Column()
    private Time departureTime;

    @Column()
    private Date arrivalDate;

    @Column()
    private Time arrivalTime;

    @Column()
    private int nbrOfPassengers;

    @Column()
    private String statusConfirmation;

    @Enumerated(EnumType.STRING)
    private VehiculeType vehiculType;

    @ManyToOne()
    @JoinColumn(name = "fk_vehicule")
    private Vehicule vehicule;

    @ManyToOne
    @JoinColumn(name = "fk_driver")
    private Driver driver;

}
