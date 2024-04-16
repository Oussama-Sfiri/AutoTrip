package com.ParcAuto.Ensa.Affectation.AspectsAOP.Logging.Affectation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAffectationAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("execution(* com.ParcAuto.Ensa.Affectation.Services.AffectationService.*(..))")
    public Object logAffectationServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        // Nom de la méthode en cours d'exécution
        String methodName = joinPoint.getSignature().getName();
        // Arguments de la méthode en cours d'exécution
        Object[] methodArgs = joinPoint.getArgs();

        // Log avant l'exécution de la méthode
        logger.info("Before execution of method: {} with arguments: {}", methodName, methodArgs);

        // Exécution de la méthode
        Object result = joinPoint.proceed();

        // Log après l'exécution de la méthode
        logger.info("After execution of method: {} with arguments: {}, returned result: {}", methodName, methodArgs, result);

        return result;
    }
}
