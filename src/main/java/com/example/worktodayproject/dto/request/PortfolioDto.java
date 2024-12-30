package com.example.worktodayproject.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

/**
 * Дто портфолио
 * @param filePath путь до файла проекта
 * @param url ссылка на проект
 */
public record PortfolioDto(MultipartFile filePath,
                           String url) {
}
