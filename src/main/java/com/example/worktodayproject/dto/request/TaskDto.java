package com.example.worktodayproject.dto.request;

import com.example.worktodayproject.database.enums.TaskGrade;
import com.example.worktodayproject.database.enums.TaskStatus;

import java.util.Date;

/**
 * Дто задачи
 * @param title название
 * @param info информация о задаче
 * @param deadline дедлайн
 * @param url ссылка на доп источник
 * @param filePath файл с доп источником
 * @param status статус выполнения
 * @param grade оценка задачи
 * @param result результат в виде ссылки
 */
public record TaskDto(String title,
                      String info,
                      Date deadline,
                      String url,
                      String filePath,
                      TaskStatus status,
                      TaskGrade grade,
                      String result) {
}
