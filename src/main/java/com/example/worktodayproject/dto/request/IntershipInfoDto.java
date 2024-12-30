package com.example.worktodayproject.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * Дто стажировки
 * @param title название
 * @param fields -
 * @param tags теги
 */
public record IntershipInfoDto(@NotBlank(message = "Название стажировки не может быть пустым")
                               @NotNull(message = "Название стажировки не может быть пустым")
                               String title,
                               @NotBlank(message = "Название компании не может быть пустым")
                               @NotNull(message = "Название компании не может быть пустым")
                               String company,
                               @NotBlank(message = "Обязанности не могут быть пустыми")
                               @NotNull(message = "Обязанности не могут быть пустыми")
                               String duties,
                               @NotBlank(message = "Требования не могут быть пустыми")
                               @NotNull(message = "Требования не могут быть пустыми")
                               String requirements,
                               @NotBlank(message = "Задание не может быть пустым")
                               @NotNull(message = "Задание не может быть пустым")
                               String task,
                               @NotBlank(message = "Город не может быть пустым")
                               @NotNull(message = "Город не может быть пустым")
                               String town,
                               String fields,
                               List<String> tags) {
}
