package com.example.worktodayproject.web;

import com.example.worktodayproject.exception.custom.AuthorizedUserException;
import com.example.worktodayproject.service.EnrollmentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * Контроллер Enrollment
 */
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/public")
public class EnrollController {

    EnrollmentService enrollmentService;

    /**
     * Записать пользователя на стажировку
     * @param id идентификатор стажировки
     * @return ответ
     */
    @PostMapping("/{id}/enroll")
    public ResponseEntity<Map<String, Object>> enrollUser(@PathVariable Long id, Principal principal) {
        if (principal == null) {
            throw new AuthorizedUserException("Только авторизованные пользователи могут записаться на стажировку");
        }
        String username = principal.getName();
        enrollmentService.enrollUser(username, id);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", String.format("User: %s enrolled to %d internship", username, id));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
