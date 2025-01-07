package com.example.worktodayproject.dto.request;

import org.springframework.web.multipart.MultipartFile;

/**
 * дто резюме
 * @param filePath путь до файла
 */
public record ResumeDto(MultipartFile filePath) {
}
