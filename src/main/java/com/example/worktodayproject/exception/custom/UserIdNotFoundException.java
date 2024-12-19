package com.example.worktodayproject.exception.custom;

/**
 * Ошибка не нахождения id пользователя
 */
public class UserIdNotFoundException extends RuntimeException {

    public static final String CODE = "400";

    public UserIdNotFoundException(Long id) {
        super("Пользователь с таким id " + id + " не найден");
    }
}
