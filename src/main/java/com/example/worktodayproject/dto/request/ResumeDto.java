package com.example.worktodayproject.dto.request;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

/**
 * дто резюме
 * @param url ссылка
 * @param filePath путь до файла
 */
public record ResumeDto(String url,
                        MultipartFile filePath) {
}
