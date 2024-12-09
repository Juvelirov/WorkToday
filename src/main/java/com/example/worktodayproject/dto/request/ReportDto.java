package com.example.worktodayproject.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Дто отчета
 * @param title название
 * @param description описание
 */
public record ReportDto(@NotNull(message = "Название не может быть пустым")
                        @NotBlank(message = "Название не может быть пустым")
                        String title,
                        @NotNull(message = "Описание не может быть пустым")
                        @NotBlank(message = "Название не может быть пустым")
                        String description) {
}
