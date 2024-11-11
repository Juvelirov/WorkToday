package com.example.worktodayproject.dto.request;

import java.util.List;

/**
 * Дто стажировки
 * @param title название
 * @param description описание
 * @param fields -
 * @param tags теги
 */
public record IntershipInfoDto(String title,
                               String description,
                               String fields,
                               List<String> tags) {
}
