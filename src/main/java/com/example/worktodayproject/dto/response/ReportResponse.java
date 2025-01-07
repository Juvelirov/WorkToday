package com.example.worktodayproject.dto.response;

/**
 * Ответ отчета
 * @param id id
 * @param userInfoId id профиля
 * @param internshipId id стажировки
 */
public record ReportResponse(Long id,
                             Long userInfoId,
                             Long internshipId,
                             String filePath) {
}
