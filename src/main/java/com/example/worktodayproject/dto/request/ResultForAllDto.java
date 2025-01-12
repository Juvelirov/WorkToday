package com.example.worktodayproject.dto.request;

import com.example.worktodayproject.database.enums.ResultStatus;
import jakarta.validation.constraints.Max;

public record ResultForAllDto(Long internshipResultId,
                              ResultStatus status,
                              @Max(value = 100, message = "Оценка до 100")
                              double mark,
                              boolean recommendation) {
}
