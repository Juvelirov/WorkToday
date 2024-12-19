package com.example.worktodayproject.dto.request;

import java.time.LocalDateTime;

/**
 * дто резюме
 * @param url ссылка
 * @param filePath путь до файла
 */
public record ResumeDto(String url,
                        String filePath) {
}
