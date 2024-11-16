package com.example.worktodayproject.dto.request;

/**
 * Дто портфолио
 * @param title название
 * @param description описание
 * @param filePath путь до файла проекта
 * @param url ссылка на проект
 */
public record PortfolioDto(String title,
                           String description,
                           String filePath,
                           String url) {
}
