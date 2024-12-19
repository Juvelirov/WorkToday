package com.example.worktodayproject.exception.custom;

/**
 * Ошибка, когда пользователь не записан на стажировку
 */
public class UserNotEnrolledException extends RuntimeException {

    public static final String CODE = "400";

    public UserNotEnrolledException() {
        super("Пользователь не записан на стажировку");
    }
}
