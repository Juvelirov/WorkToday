package com.example.worktodayproject.dto.response;

import com.example.worktodayproject.security.dto.response.UserResponse;

import java.util.List;

/**
 * Ответ стажировки
 * @param title название
 * @param description описание
 * @param fields -
 * @param tags теги
 * @param creator создатель
 */
public record IntershipInfoResponse(Long id,
                                    String title,
                                    String description,
                                    String fields,
                                    List<String> tags,
                                    UserResponse creator) {
}
