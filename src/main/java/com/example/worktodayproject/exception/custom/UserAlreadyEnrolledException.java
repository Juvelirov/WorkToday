package com.example.worktodayproject.exception.custom;

/**
 * Ошибка записи польззователя на стажировку из-за того, что он уже записан на нее
 */
public class UserAlreadyEnrolledException extends RuntimeException {

    public final static String CODE = "400";

    public UserAlreadyEnrolledException(String username) {
        super("Пользователь " + username + " уже записан на эту стажировку");
    }
}
