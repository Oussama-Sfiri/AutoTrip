package com.ParcAuto.Ensa.Affectation.AspectsAOP.Logging.Trips;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingTripAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @AfterReturning(pointcut = "execution(* com.ParcAuto.Ensa.Affectation.Services.TripService.getTripById(..)) && args(id)",
            returning = "result")
    public void logGetTripById(Long id, Object result) {
        logger.info("Method getTripById execution with id: {} returned result: {}", id, result);
    }

    @AfterReturning("execution(* com.ParcAuto.Ensa.Affectation.Services.TripService.getAllTrips())")
    public void logGetAllTrips() {
        logger.info("Method getAllTrips execution");
    }

    @AfterReturning(pointcut = "execution(* com.ParcAuto.Ensa.Affectation.Services.TripService.createTrip(..))",
            returning = "result")
    public void logCreateTrip(Object result) {
        logger.info("Method createTrip execution returned result: {}", result);
    }
}

