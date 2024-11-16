package com.example.worktodayproject.exception.custom;

/**
 * Ошибка не нахождения портфолио
 */
public class PortfolioNotFoundException extends RuntimeException {

    public static final String CODE = "400";

    public PortfolioNotFoundException(Long id) {
        super("Портфолио с таким id " + id + " не найдено");
    }
}
