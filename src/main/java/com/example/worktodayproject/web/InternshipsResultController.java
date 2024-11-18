package com.example.worktodayproject.web;

import com.example.worktodayproject.dto.request.ResultDto;
import com.example.worktodayproject.service.InternshipResultService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Контроллер результата стажировки
 */
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/private/hr/result")
public class InternshipsResultController {

    InternshipResultService internshipResultService;

    /**
     * Создать результат по стажировке
     * @param username имя пользователя
     * @param internshipId id стажировки
     * @param principal текущий пользователь
     * @param resultDto дто результата
     * @return
     */
    @PostMapping("/create/{internshipId}/{username}")
    public ResponseEntity<String> createResult(@PathVariable String username,
                                               @PathVariable Long internshipId,
                                               Principal principal,
                                               @RequestBody ResultDto resultDto) {
        internshipResultService.createInternshipResult(username,
                principal.getName(),
                internshipId,
                resultDto);
        return ResponseEntity.ok("success");
    }
}
