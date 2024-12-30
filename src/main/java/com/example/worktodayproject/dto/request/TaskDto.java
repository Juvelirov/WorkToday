package com.example.worktodayproject.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

/**
 * Дто задачи
 * @param title название
 * @param info информация о задаче
 * @param deadline дедлайн
 * @param url ссылка на доп источник
 * @param filePath файл с доп источником
 * @param result результат в виде ссылки
 */
public record TaskDto(@NotNull(message = "Название не может быть пустым")
                      @NotBlank(message = "Название не может быть пустым")
                      String title,
                      @NotNull(message = "Информация не может быть пустой")
                      @NotBlank(message = "Информация не может быть пустой")
                      String info,
                      Date deadline,
                      String url,
                      String filePath,
                      String result) {
}
