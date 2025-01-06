package com.example.worktodayproject.dto.request;

import org.springframework.web.multipart.MultipartFile;

/**
 * Дто профиля
 * @param phoneNumber номер телефона
 * @param town город
 */
public record UsersInfoDto(String fio,
                           String phoneNumber,
                           String town,
                           MultipartFile avatarUrl) {
}
