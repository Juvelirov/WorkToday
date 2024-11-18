package com.example.worktodayproject.dto.request;

/**
 * Дто результатов стажировки
 * @param mark оценка
 * @param recommendation рекомендация
 * @param report дто отчетов
 */
public record ResultDto(double mark,
                        boolean recommendation,
                        ReportDto report) {
}
