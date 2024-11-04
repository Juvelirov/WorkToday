package com.example.worktodayproject.exception;

/**
 * Ответ ошибки
 * @param message сообщение ошибки
 * @param code код ошибки
 */
public record ExceptionResponse(String message,
                                String code) {
}
