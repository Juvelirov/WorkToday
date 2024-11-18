package com.example.worktodayproject.dto.request;

import com.example.worktodayproject.database.enums.TaskGrade;

/**
 * Дто проверки задания
 * @param grade оценка
 */
public record CheckTaskDto(TaskGrade grade) {
}
