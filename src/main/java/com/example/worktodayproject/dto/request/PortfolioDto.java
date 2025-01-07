package com.example.worktodayproject.dto.request;

import org.springframework.web.multipart.MultipartFile;

/**
 * Дто портфолио
 * @param filePath путь до файла проекта
 */
public record PortfolioDto(MultipartFile filePath) {
}
