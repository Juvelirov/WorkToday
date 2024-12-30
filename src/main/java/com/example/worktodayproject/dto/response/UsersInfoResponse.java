package com.example.worktodayproject.dto.response;


import java.util.List;
import java.util.Optional;

/**
 * Ответ профиля пользователя
 * @param id его идентификатор
 * @param recommendation рекомендация на стажировку
 * @param phoneNumber номер телефона
 * @param town город
 * @param portfolios портфолио пользователя
 * @param resumes резюме пользователя
 */
public record UsersInfoResponse(Long id,
                                String email,
                                String fio,
                                boolean recommendation,
                                String phoneNumber,
                                String town,
                                String avatarPath,
                                Optional<PortfolioResponse> portfolios,
                                Optional<ResumeResponse> resumes,
                                Optional<List<IntershipInfoResponse>> internships,
                                Optional<List<InternshipStatusResponse>> internshipStatus,
                                Optional<List<ReportResponse>> reports) {
}
