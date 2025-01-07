package com.example.worktodayproject.dto.response;

import com.example.worktodayproject.database.enums.EnrollStatus;

public record EnrollResponse(Long enrollId,
                             Long internshipId,
                             UsersInfoResponse userInfo,
                             EnrollStatus enrollStatus) {
}
