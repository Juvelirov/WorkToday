package com.example.worktodayproject.dto.request;

import com.example.worktodayproject.database.enums.ResultStatus;
import jakarta.validation.constraints.Max;

/**
 * Дто результатов стажировки
 * @param mark оценка
 * @param recommendation рекомендация
 */
public record ResultDto(ResultStatus status,
                        @Max(value = 100, message = "Оценка до 100")
                        double mark,
                        boolean recommendation) {
}
