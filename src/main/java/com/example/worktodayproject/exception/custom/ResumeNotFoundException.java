package com.example.worktodayproject.exception.custom;

/**
 * Ошибка не нахождения портфолио
 */
public class ResumeNotFoundException extends RuntimeException {

    public static final String CODE = "400";

    public ResumeNotFoundException(Long id) {
        super("Портфолио " + id + " не найдено");
    }
}
