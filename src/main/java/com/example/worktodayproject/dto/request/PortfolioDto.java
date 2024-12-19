package com.example.worktodayproject.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Дто портфолио
 * @param title название
 * @param description описание
 * @param filePath путь до файла проекта
 * @param url ссылка на проект
 */
public record PortfolioDto(@NotNull(message = "Название не может быть пустым")
                           @NotBlank(message = "Название не может быть пустым")
                           String title,
                           @NotNull(message = "Описание не может быть пустым")
                           @NotBlank(message = "Описание не может быть пустым")
                           String description,
                           String filePath,
                           String url) {
}
