package com.example.worktodayproject.security.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Date;

/**
 * Дто пользователя
 * @param password пароль
 * @param create дата создания
 * @param email почта
 * @param role роль
 */
public record UserDto(@NotNull(message = "ФИО не может быть пустым")
                      @NotBlank(message = "ФИО не может быть пустым")
                      String fio,
                      @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$",
                              message = "Некорректный адрес электронной почты")
                      @NotNull(message = "Почта не может быть пустой")
                      @NotBlank(message = "Почта не может быть пустой")
                      String email,
                      @NotNull(message = "Пароль не может быть пустым")
                      @NotBlank(message = "Пароль не может быть пустым")
                      @Size(min = 5, max = 30, message = "Пароль должен быть больше 5 символов")
                      String password,
                      Date create,
                      String role) {
}
