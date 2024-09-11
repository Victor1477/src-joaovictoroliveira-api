package com.joaovictoroliveira.api.taskmanagement.aspect;

import com.joaovictoroliveira.api.core.dto.ErrorHandlerDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TaskManagementControllersErrorHandlerAspect {

    @Pointcut("execution(public * com.joaovictoroliveira.api.controllers.*Controller.*(..))")
    public void forAllControllers() {
    }

    @Around("forAllControllers()")
    public ResponseEntity handleError(ProceedingJoinPoint joinPoint) {
        try {
            return (ResponseEntity) joinPoint.proceed();
        } catch (Throwable e) {
            return ResponseEntity.badRequest().body(new ErrorHandlerDTO(e.getLocalizedMessage()));
        }
    }
}
