package com.example.worktodayproject.exception;

import com.example.worktodayproject.exception.custom.RoleNotFoundException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Отлов всех ошибок в приложении
 */
@RestControllerAdvice
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExceptionAdvice {

    /**
     * Олтов ошибки не нахождения пользователя
     * @param ex ошибка
     * @return овтет ошибки
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleRoleNotFound(RoleNotFoundException ex) {
        log.info(ex.getMessage());
        return new ExceptionResponse(ex.getMessage(), RoleNotFoundException.CODE);
    }
}
