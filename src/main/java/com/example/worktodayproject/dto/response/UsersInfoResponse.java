package com.example.worktodayproject.dto.response;

import java.util.List;
import java.util.Optional;

/**
 * Ответ профиля пользователя
 * @param id его идентификатор
 * @param username имя пользователя
 * @param name имя
 * @param surname фамилия
 * @param patronymic отчество
 * @param recommendation рекомендация на стажировку
 * @param phoneNumber номер телефона
 * @param town город
 * @param portfolios портфолио пользователя
 * @param resumes резюме пользователя
 */
public record UsersInfoResponse(Long id,
                                String username,
                                String name,
                                String surname,
                                String patronymic,
                                boolean recommendation,
                                String phoneNumber,
                                String town,
                                Optional<PortfolioResponse> portfolios,
                                Optional<ResumeResponse> resumes,
                                Optional<List<TaskResponse>> tasks,
                                Optional<List<ReportResponse>> result) {
}
