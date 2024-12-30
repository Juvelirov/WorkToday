package com.example.worktodayproject.security.dto.response;

/**
 * Ответ пользователя
 * @param fio фамилия, имя, отчество
 * @param email почта
 */
public record UserResponse(Long id,
                           String fio,
                           String email,
                           String avatarPath) {
}
