package com.ParcAuto.Ensa.Affectation.Utils;

import com.ParcAuto.Ensa.Affectation.Entities.PermisType;
import com.ParcAuto.Ensa.Affectation.Entities.VehiculeType;

public class PermitUtils {

    public static PermisType getPermisForVehiculeType(VehiculeType vehiculeType) {
        switch (vehiculeType) {
            case Car:
                return PermisType.B;
            case Bus:
                return PermisType.D;
            case Fourgonette:
                return PermisType.C;
            case Truck:
                return PermisType.EC;
            default:
                throw new IllegalArgumentException("No corresponding permit type for the provided vehicle type");
        }
    }
}
