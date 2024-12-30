package com.example.worktodayproject.dto.response;

import com.example.worktodayproject.database.enums.TaskGrade;
import com.example.worktodayproject.database.enums.TaskStatus;

import java.util.Date;

public record TaskResponse(Long id,
                           Long intershipId,
                           String title,
                           String info,
                           Date deadline,
                           String url,
                           String filePath,
                           TaskStatus status,
                           TaskGrade grade,
                           String result) {
}
