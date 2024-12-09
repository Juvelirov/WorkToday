package com.example.worktodayproject.dto.request;

import jakarta.validation.constraints.Max;

/**
 * Дто результатов стажировки
 * @param mark оценка
 * @param recommendation рекомендация
 * @param report дто отчетов
 */
public record ResultDto(@Max(value = 5, message = "Оценка до 5")
                        double mark,
                        boolean recommendation,
                        ReportDto report) {
}
