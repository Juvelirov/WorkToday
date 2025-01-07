package com.example.worktodayproject.dto.response;

import java.time.LocalDateTime;

/**
 * Ответ портфолио
 * @param id идентификатор
 * @param filePath путь до файла проекта
 * @param uploadDate дата загрузки
 */
public record PortfolioResponse(Long id,
                                Long userId,
                                String filePath,
                                LocalDateTime uploadDate) {
}
