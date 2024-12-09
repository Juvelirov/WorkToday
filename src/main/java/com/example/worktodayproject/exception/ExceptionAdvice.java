package com.example.worktodayproject.exception;

import com.example.worktodayproject.exception.custom.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;

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
    public ExceptionResponse handleAuthorizedUserEnrollException(AuthorizedUserException ex) {
        log.info(ex.getMessage());
        log.error(Arrays.toString(ex.getStackTrace()));
        return new ExceptionResponse(ex.getMessage(), AuthorizedUserException.CODE);
    }

    /**
     * Отлов ошибки не нахождения портфолио
     * @param ex ошибка
     * @return ответ ошибки
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handlePortfolioNotFoundException(PortfolioNotFoundException ex) {
        log.info(ex.getMessage());
        log.error(Arrays.toString(ex.getStackTrace()));
        return new ExceptionResponse(ex.getMessage(), PortfolioNotFoundException.CODE);
    }

    /**
     * Отлов ошибки не нахождения профиля
     * @param ex ошибка
     * @return ответ ошибки
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleUserInfoNotFoundException(UserInfoNotFoundException ex) {
        log.info(ex.getMessage());
        log.error(Arrays.toString(ex.getStackTrace()));
        return new ExceptionResponse(ex.getMessage(), UserInfoNotFoundException.CODE);
    }

    /**
     * Отлов ошибки не нахождения резюме
     * @param ex ошибка
     * @return ответ ошибки
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleResumeNotFoundException(ResumeNotFoundException ex) {
        log.info(ex.getMessage());
        log.error(Arrays.toString(ex.getStackTrace()));
        return new ExceptionResponse(ex.getMessage(), ResumeNotFoundException.CODE);
    }

    /**
     * Отлов ошибки не нахождения id стажировки
     * @param ex ошибка
     * @return ответ ошибки
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleInternshipNotFoundException(InternshipNotFoundException ex) {
        log.info(ex.getMessage());
        log.error(Arrays.toString(ex.getStackTrace()));
        return new ExceptionResponse(ex.getMessage(), InternshipNotFoundException.CODE);
    }

    /**
     * Отлов ошибки не нахождения id пользователя
     * @param ex ошибка
     * @return ответ ошибки
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleUserIdNotFoundException(UserIdNotFoundException ex) {
        log.info(ex.getMessage());
        log.error(Arrays.toString(ex.getStackTrace()));
        return new ExceptionResponse(ex.getMessage(), UserIdNotFoundException.CODE);
    }

    /**
     * Отлов ошибки, когда пользователь не записан на стажировку
     * @param ex ошибка
     * @return ответ ошибки
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleUserNotEnrolledException(UserNotEnrolledException ex) {
        log.info(ex.getMessage());
        log.error(Arrays.toString(ex.getStackTrace()));
        return new ExceptionResponse(ex.getMessage(), UserNotEnrolledException.CODE);
    }

    /**
     * Отлов ошибки, которая появляется когда нельзя поставить оценку
     * @param ex ошибка
     * @return ответ ошибки
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleGradeNotSetException(GradeNotSetException ex) {
        log.info(ex.getMessage());
        log.error(Arrays.toString(ex.getStackTrace()));
        return new ExceptionResponse(ex.getMessage(), GradeNotSetException.CODE);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(x -> new ExceptionResponse(x.getDefaultMessage(), "400"))
                .toList();
    }
}
