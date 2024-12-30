package com.example.worktodayproject.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

/**
 * Дто профиля
 * @param name имя
 * @param surname фамилия
 * @param patronymic отчество
 * @param phoneNumber номер телефона
 * @param town город
 */
public record UsersInfoDto(@NotNull(message = "Имя не может быть пустым")
                           @NotBlank(message = "Имя не может быть пустым")
                           String name,
                           @NotNull(message = "Фамилия не может быть пустой")
                           @NotBlank(message = "Фамилия не может быть пустой")
                           String surname,
                           @NotNull(message = "Отчество не может быть пустым")
                           @NotBlank(message = "Отчество не может быть пустым")
                           String patronymic,
                           String phoneNumber,
                           String town,
                           MultipartFile avatarUrl) {
}
