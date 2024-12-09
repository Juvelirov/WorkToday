package com.example.worktodayproject.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * Дто стажировки
 * @param title название
 * @param description описание
 * @param fields -
 * @param tags теги
 */
public record IntershipInfoDto(@NotBlank(message = "Название не может быть пустым")
                               @NotNull(message = "Название не может быть пустым")
                               String title,
                               @NotBlank(message = "Описание не может быть пустым")
                               @NotNull(message = "Описание не может быть пустым")
                               String description,
                               String fields,
                               List<String> tags) {
}
