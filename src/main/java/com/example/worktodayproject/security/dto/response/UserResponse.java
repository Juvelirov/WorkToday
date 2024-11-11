package com.example.worktodayproject.security.dto.response;

/**
 * Ответ пользователя
 * @param fio фамилия, имя, отчество
 * @param login лгин
 * @param email почта
 */
public record UserResponse(String fio,
                           String login,
                           String email) {
}
