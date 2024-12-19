package com.example.worktodayproject.exception.custom;

/**
 * Ошибка не нахождения стажировки по имени
 */
public class IntershipTitleNotFoundException extends RuntimeException {

    public static final String CODE = "400";

    /**
     * Конструктор, который инициализирует родительский класс
     * @param id идентификатор
     */
    public IntershipTitleNotFoundException(Long id) {
        super("Стажировка с таким id: " + id + " не найдена");
    }
}
