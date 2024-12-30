package com.example.worktodayproject.dto.response;

import com.example.worktodayproject.database.enums.ResultStatus;

public record InternshipStatusResponse(Long id,
                                       ResultStatus status,
                                       Double mark,
                                       Boolean recommendation) {
}
