package com.example.worktodayproject.dto.response;

import java.time.LocalDateTime;

/**
 * Ответ резюме
 * @param id идентификатор
 * @param url ссылка
 * @param filePath путь до файла
 * @param uploadDate дата создания
 */
public record ResumeResponse(Long id,
                             Long userId,
                             String url,
                             String filePath,
                             LocalDateTime uploadDate) {
}
