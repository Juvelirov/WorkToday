package com.example.worktodayproject.dto.response;

import com.example.worktodayproject.database.enums.EnrollStatus;

public record EnrollResponse(Long id,
                             UsersInfoResponse userInfo,
                             EnrollStatus enrollStatus) {
}
