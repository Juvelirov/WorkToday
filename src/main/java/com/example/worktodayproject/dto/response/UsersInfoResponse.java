package com.example.worktodayproject.dto.response;


import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Ответ профиля пользователя
 * @param id его идентификатор
 * @param recommendation рекомендация на стажировку
 * @param phoneNumber номер телефона
 * @param town город
 */
public record UsersInfoResponse(Long id,
                                String email,
                                String fio,
                                boolean recommendation,
                                String phoneNumber,
                                String town,
                                String avatarPath,
                                Optional<PortfolioResponse> portfolio,
                                Optional<ResumeResponse> resume,
                                Optional<Set<IntershipInfoResponse>> internships,
                                Optional<Set<InternshipStatusResponse>> internshipStatus,
                                Optional<Set<ReportResponse>> reports) {
}
