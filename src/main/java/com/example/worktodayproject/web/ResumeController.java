package com.example.worktodayproject.web;

import com.example.worktodayproject.dto.request.ResumeDto;
import com.example.worktodayproject.dto.response.ResumeResponse;
import com.example.worktodayproject.service.ResumeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Контроллер резюме
 */
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/private/student/resume")
public class ResumeController {

    ResumeService resumeService;

    /**
     * Создать резюме для пользователя
     * @param resumeDto дто резюме
     * @param principal текущий пользователь
     * @return ответ
     */
    @PostMapping("/create")
    public ResponseEntity<String> createResume(@RequestBody ResumeDto resumeDto, Principal principal) {
        resumeService.createResume(principal.getName(), resumeDto);
        return ResponseEntity.ok("success");
    }

    /**
     * Получить резюме пользователя по его id
     * @param username имя пользователя
     * @param id идентификатор резюме
     * @return ответ резюме
     */
    @GetMapping("/{username}/{id}")
    public ResumeResponse getUserResume(@PathVariable String username, @PathVariable Long id) {
        return resumeService.getUserResume(username, id);
    }

    /**
     * Получить все резюме пользователя
     * @param username имя пользователя
     * @return список резюме
     */
    @GetMapping("/{username}")
    public List<ResumeResponse> getAllUserResumes(@PathVariable String username) {
        return resumeService.getAllUserResume(username);
    }

    /**
     * Получить все резюме текущего пользователя
     * @param principal текущий пользователь
     * @return список резюме
     */
    @GetMapping("/my-resume")
    public List<ResumeResponse> getCurrentUserPortfolios(Principal principal) {
        return resumeService.getAllUserResume(principal.getName());
    }

    /**
     * Удалить резюме по его id
     * @param id идентификатор резюме
     * @param principal текущий пользователь
     * @return успешный ответ
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePortfolio(@PathVariable Long id, Principal principal) {
        resumeService.deleteResume(principal.getName(), id);
        return ResponseEntity.ok("success");
    }
}
