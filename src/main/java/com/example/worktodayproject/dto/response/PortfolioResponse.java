package com.example.worktodayproject.dto.response;

import java.time.LocalDateTime;

/**
 * Ответ портфолио
 * @param id идентификатор
 * @param filePath путь до файла проекта
 * @param url ссылка на проект
 * @param uploadDate дата загрузки
 */
public record PortfolioResponse(Long id,
                                Long userId,
                                String filePath,
                                String url,
                                LocalDateTime uploadDate) {
}
