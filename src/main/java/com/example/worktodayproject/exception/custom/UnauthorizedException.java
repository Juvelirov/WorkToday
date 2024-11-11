package com.example.worktodayproject.exception.custom;

/**
 * Ошибка авторизации стажировки
 */
public class UnauthorizedException extends RuntimeException {

    public static final String CODE = "400";

    /**
     * Конструктор, который инициализирует класс родителя
     * @param text текст ошибки
     */
    public UnauthorizedException(String text) {
        super(text);
    }
}
