package com.example.worktodayproject.exception.custom;

/**
 * Ошибка записи на стажировку не авторизованных пользователей
 */
public class AuthorizedUserException extends RuntimeException {

    public final static String CODE = "400";

    public AuthorizedUserException(String text) {
        super(text);
    }
}
