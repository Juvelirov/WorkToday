package com.example.worktodayproject.dto;

/**
 * Дто профиля
 * @param name имя
 * @param surname фамилия
 * @param patronymic отчество
 * @param phoneNumber номер телефона
 * @param town город
 */
public record UsersInfoDto(String name,
                           String surname,
                           String patronymic,
                           String phoneNumber,
                           String town) {
}
