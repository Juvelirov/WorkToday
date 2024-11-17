package com.example.worktodayproject.exception.custom;

/**
 * Ошибка не нахождения id стажировки
 */
public class InternshipNotFoundException extends RuntimeException {

    public static final String CODE = "400";

    public InternshipNotFoundException(Long id) {
        super("Стажировка с таким id " + id + " не найдена");
    }
}
