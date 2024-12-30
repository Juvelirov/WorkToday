package com.example.worktodayproject.dto.response;

import com.example.worktodayproject.database.enums.ResultStatus;

public record AnalyticResponse(Long id,
                               String fio,
                               ResultStatus status,
                               Double mark,
                               Boolean recommendation,
                               UsersInfoResponse usersInfo) {
}
