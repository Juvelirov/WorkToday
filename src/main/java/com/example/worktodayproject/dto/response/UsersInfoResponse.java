package com.example.worktodayproject.dto.response;

import java.util.Optional;

/**
 * Ответ профиля пользователя
 * @param id его идентификатор
 * @param name имя
 * @param surname фамилия
 * @param patronymic отчество
 * @param recommendation рекомендация на стажировку
 * @param phoneNumber номер телефона
 * @param town город
 * @param portfolios портфолио пользователя
 */
public record UsersInfoResponse(Long id,
                                String name,
                                String surname,
                                String patronymic,
                                boolean recommendation,
                                String phoneNumber,
                                String town,
                                Optional<PortfolioResponse> portfolios) {
}
