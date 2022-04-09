package org.roon.aopjoinpointreadannotation.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Around("execution(* org.roon.aopjoinpointreadannotation.controller.SampleController*.*(..))")
    public void logBefore(ProceedingJoinPoint pJoinPoint) throws Throwable {
        long s = System.nanoTime();
        System.out.println("Before in LogAspect.logBefore");

        Object result = pJoinPoint.proceed();

        long e = System.nanoTime();
        System.out.println("processing time = " + (e - s) / 1_000_000);

        System.out.println("After in LogAspect.logAfter");
    }
}
