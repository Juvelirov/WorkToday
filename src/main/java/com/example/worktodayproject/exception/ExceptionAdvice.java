package com.example.worktodayproject.exception;

import com.example.worktodayproject.exception.custom.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

/**
 * Отлов всех ошибок в приложении
 */
@RestControllerAdvice
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExceptionAdvice {

    /**
     * Отлов ошибки не нахождения пользователя
     * @param ex ошибка
     * @return ответ ошибки
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleRoleNotFound(RoleNotFoundException ex) {
        log.info(ex.getMessage());
        log.error(Arrays.toString(ex.getStackTrace()));
        return new ExceptionResponse(ex.getMessage(), RoleNotFoundException.CODE);
    }

    /**
     * Отлов ошибки не нахождения стажировки по ее названию
     * @param ex ошибка
     * @return ответ ошибки
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleIntershipTitleNotFound(IntershipTitleNotFoundException ex) {
        log.info(ex.getMessage());
        log.error(Arrays.toString(ex.getStackTrace()));
        return new ExceptionResponse(ex.getMessage(), IntershipTitleNotFoundException.CODE);
    }

    /**
     * Отлов ошибки авторизации стажировки
     * @param ex ошибка
     * @return ответ ошибки
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleUnauthorizedException(UnauthorizedException ex) {
        log.info(ex.getMessage());
        log.error(Arrays.toString(ex.getStackTrace()));
        return new ExceptionResponse(ex.getMessage(), UnauthorizedException.CODE);
    }

    /**
     * Отлов ошибки записи на стажировку из-за того, что пользователь уже записан на нее
     * @param ex ошибка
     * @return ответ ошибки
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleUserAlreadyEnrolledException(UserAlreadyEnrolledException ex) {
        log.info(ex.getMessage());
        log.error(Arrays.toString(ex.getStackTrace()));
        return new ExceptionResponse(ex.getMessage(), UserAlreadyEnrolledException.CODE);
    }

    /**
     * Отлов ошибки записи на стажировку не авторизованных пользователей
     * @param ex ошибка
     * @return ответ ошибки
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleAuthorizedUserEnrollException(AuthorizedUserEnrollException ex) {
        log.info(ex.getMessage());
        log.error(Arrays.toString(ex.getStackTrace()));
        return new ExceptionResponse(ex.getMessage(), AuthorizedUserEnrollException.CODE);
    }
}
