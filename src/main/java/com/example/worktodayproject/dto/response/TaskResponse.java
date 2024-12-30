package com.example.worktodayproject.dto.response;

import com.example.worktodayproject.database.enums.TaskGrade;
import com.example.worktodayproject.database.enums.ResultStatus;

import java.util.Date;

public record TaskResponse(Long id,
                           Long intershipId,
                           String title,
                           String info,
                           Date deadline,
                           String url,
                           String filePath,
                           ResultStatus status,
                           TaskGrade grade,
                           String result) {
}
