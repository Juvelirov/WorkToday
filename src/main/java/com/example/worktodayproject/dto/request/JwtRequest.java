package com.example.worktodayproject.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record JwtRequest(@NotNull(message = "Почта не может быть пустым")
                         @NotBlank(message = "Почта не может быть пустым")
                         String email,
                         @NotNull(message = "Пароль не может быть пустым")
                         @NotBlank(message = "Пароль не может быть пустым")
                         String password) {
}
