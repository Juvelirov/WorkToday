package com.example.worktodayproject.dto.response;

import java.time.LocalDateTime;

/**
 * Ответ портфолио
 * @param id идентификатор
 * @param title название
 * @param description описание
 * @param filePath путь до файла проекта
 * @param url ссылка на проект
 * @param uploadDate дата загрузки
 */
public record PortfolioResponse(Long id,
                                Long userId,
                                String title,
                                String description,
                                String filePath,
                                String url,
                                LocalDateTime uploadDate) {
}
