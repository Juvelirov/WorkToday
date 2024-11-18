package com.example.worktodayproject.dto.response;

import java.time.LocalDateTime;

/**
 *
 * @param id id
 * @param mark оценка
 * @param recommendation рекомендация
 * @param report отчет
 * @param finalDate дата окончания
 */
public record ResultResponse(Long id,
                             double mark,
                             boolean recommendation,
                             LocalDateTime finalDate,
                             ReportResponse report) {
}
