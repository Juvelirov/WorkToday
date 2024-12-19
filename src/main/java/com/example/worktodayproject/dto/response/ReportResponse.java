package com.example.worktodayproject.dto.response;

/**
 * Ответ отчета
 * @param id id
 * @param userInfoId id профиля
 * @param internshipId id стажировки
 * @param title название
 * @param description описание
 */
public record ReportResponse(Long id,
                             Long userInfoId,
                             Long internshipId,
                             String title,
                             String description) {
}
