package com.example.worktodayproject.security.dto.request;

import java.util.Date;

/**
 * Дто пользователя
 * @param login логин
 * @param password пароль
 * @param create дата создания
 * @param email почта
 * @param role роль
 */
public record UserDto(String fio,
                      String login,
                      String password,
                      Date create,
                      String email,
                      String role) {
}
