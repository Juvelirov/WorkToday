package com.example.worktodayproject.exception.custom;

/**
 * Ошибка записи на стажировку не авторизованных пользователей
 */
public class AuthorizedUserEnrollException extends RuntimeException {

    public final static String CODE = "400";

    public AuthorizedUserEnrollException() {
        super("Только авторизованные пользователи могут записаться на стажировку");
    }
}
