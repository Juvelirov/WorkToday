package com.example.worktodayproject.dto.response;

import com.example.worktodayproject.security.dto.response.UserResponse;

import java.util.List;

/**
 * Ответ стажировки
 * @param title название
 * @param fields -
 * @param tags теги
 * @param creator создатель
 */
public record IntershipInfoResponse(Long id,
                                    String title,
                                    String company,
                                    String duties,
                                    String requirements,
                                    String task,
                                    String town,
                                    String fields,
                                    List<String> tags,
                                    UserResponse creator) {
}
