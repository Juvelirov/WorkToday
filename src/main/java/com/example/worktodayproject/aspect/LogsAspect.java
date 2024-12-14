package com.example.worktodayproject.aspect;

import com.example.worktodayproject.dto.request.JwtRequest;
import com.example.worktodayproject.security.dto.request.UserDto;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
@Aspect
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LogsAspect {

    /**
     * Логгирование методов PublicController
     * @param joinPoint jointpoint
     * @return result
     */
    @Around("execution(public * com.example.worktodayproject.web.PublicController.*(..))")
    public Object logPublicMethodsExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        UserDto userDto = null;
        JwtRequest jwtRequest = null;

        for (Object arg : args) {
            if (arg instanceof UserDto) {
                userDto = (UserDto) arg;
            }
            if (arg instanceof JwtRequest) {
                jwtRequest = (JwtRequest) arg;
            }
        }

        log.info("Метод {} выполнился", methodName);
        Object result = joinPoint.proceed();

        switch (methodName) {
            case "signUp" -> log.info("Пользователь {} зарегистрирован",
                    userDto.email());
            case "createAuthToken" -> log.info("Пользователь {} авторизован",
                    jwtRequest.email());
        }
        return result;
    }

    @Around("execution(public * com.example.worktodayproject.web.AdminController.*(..))")
    public Object logAdminMethodsExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String login = null;

        for (Object arg : args) {
            if (arg instanceof String) {
                login = (String) arg;
            }
        }

        log.info("Метод {} выполнился", methodName);
        Object result = joinPoint.proceed();

        switch (methodName) {
            case "deleteUser" -> log.info("Пользователь {} удален",
                    login);
        }
        return result;
    }

//    @Around("execution(public * com.example.worktodayproject.web.EnrollController.*(..))")
//    public Object logEnrollMethodsExecution(ProceedingJoinPoint joinPoint) throws Throwable {
//        String methodName = joinPoint.getSignature().getName();
//        Object[] args = joinPoint.getArgs();
//        Long id = null;
//        String username = null;
//
//        for (Object arg : args) {
//            if (arg instanceof Long) {
//                id = (Long) arg;
//            }
//            if (arg instanceof String) {
//                username = (String) arg;
//            }
//        }
//
//        log.info("Метод {} выполнился", methodName);
//        Object result = joinPoint.proceed();
//
//        switch (methodName) {
//            case "enrollUser" -> log.info("Пользователь {} записан на стажировку {}",
//                    username, id);
//        }
//        return result;
//    }
}
