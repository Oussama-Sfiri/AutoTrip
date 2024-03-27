package com.ParcAuto.Ensa.Affectation.Dto;


import lombok.*;

@Builder
@Data
public class AffecterTripRequestDTO {
    private Long DriverId;
    private Long VehiculeId;
}
