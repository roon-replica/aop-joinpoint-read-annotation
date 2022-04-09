package org.roon.aopjoinpointreadannotation.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class CheckAspect {

    @Before("@annotation(CheckRole)")
    public void checkRoleBefore(JoinPoint joinPoint) {
        System.out.println("Before execution. Roles: " + formatRoles(joinPoint));
    }

    @AfterReturning("@annotation(CheckRole)")
    public void checkRoleAfterSuccess(JoinPoint joinPoint) {
        System.out.println("After normally executed. Roles: " + formatRoles(joinPoint));
    }

    @AfterThrowing("@annotation(CheckRole)")
    public void checkRoleAfterFailed(JoinPoint joinPoint) {
        System.out.println("After exceptionally executed. Roles: " + formatRoles(joinPoint));
    }

    // @Around is special
    // @Around는 언제 메서드가 실행될 지 설정 가능.
    // 그래서 단순한 JoinPoint 대신 ProceesingJoinPoint 사용
    @Around("@annotation(CheckRole)")
    public void checkRoleAround(ProceedingJoinPoint pJoinPoint) throws Throwable {
        System.out.println("Before in @Around " + formatRoles(pJoinPoint));
        Object result = pJoinPoint.proceed();
        System.out.println("After in @Around");
    }

    private String[] getRoles(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        CheckRole checkRoleAnnotation = method.getAnnotation(CheckRole.class);
        return checkRoleAnnotation.roles();
    }

    private String formatRoles(JoinPoint joinPoint) {
        String[] roles = getRoles(joinPoint);
        return Arrays.toString(roles);
    }
}
