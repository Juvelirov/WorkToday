package com.example.worktodayproject.dto.request;

import java.util.Date;

/**
 *
 * @param title
 * @param info
 * @param deadline
 */
public record TaskDto(String title,
                      String info,
                      Date deadline) {
}
