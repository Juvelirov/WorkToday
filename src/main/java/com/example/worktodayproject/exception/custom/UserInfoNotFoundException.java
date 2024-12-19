package com.example.worktodayproject.exception.custom;

/**
 * Ошибка не нахождения профиля
 */
public class UserInfoNotFoundException extends RuntimeException {

    public static final String CODE = "400";

    public UserInfoNotFoundException(String username) {
        super("Профиль пользователя " + username + " не найден");
    }
}
