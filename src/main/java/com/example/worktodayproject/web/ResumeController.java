package com.example.worktodayproject.web;

import com.dropbox.core.DbxException;
import com.example.worktodayproject.dto.request.ResumeDto;
import com.example.worktodayproject.dto.response.ResumeResponse;
import com.example.worktodayproject.service.ResumeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Контроллер резюме
 */
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/private/student/resume")
@Validated
public class ResumeController {

    ResumeService resumeService;

    /**
     * Создать резюме для пользователя
     * @param principal текущий пользователь
     * @return ответ
     */
    @PostMapping(value = "/create", consumes = { "multipart/form-data" })
    public ResponseEntity<Map<String, Object>> createResume(@RequestParam(value = "url", required = false) String url,
                                                            @RequestParam(value = "filePath", required = false) MultipartFile filePath,
                                                            Principal principal) throws IOException, DbxException {
        ResumeDto resumeDto = new ResumeDto(url, filePath);
        resumeService.createResume(principal.getName(), resumeDto);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Получить резюме пользователя по его id
     * @param username имя пользователя
     * @param id идентификатор резюме
     * @return ответ резюме
     */
//    @GetMapping("/{username}/{id}")
//    public ResumeResponse getUserResume(@PathVariable String username, @PathVariable Long id) {
//        return resumeService.getUserResume(username, id);
//    }
//
//    /**
//     * Получить все резюме пользователя
//     * @param username имя пользователя
//     * @return список резюме
//     */
//    @GetMapping("/{username}")
//    public List<ResumeResponse> getAllUserResumes(@PathVariable String username) {
//        return resumeService.getAllUserResume(username);
//    }
//
//    /**
//     * Получить все резюме текущего пользователя
//     * @param principal текущий пользователь
//     * @return список резюме
//     */
//    @GetMapping("/my-resume")
//    public List<ResumeResponse> getCurrentUserPortfolios(Principal principal) {
//        return resumeService.getAllUserResume(principal.getName());
//    }

    /**
     * Удалить резюме по его id
     * @param id идентификатор резюме
     * @param principal текущий пользователь
     * @return успешный ответ
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deletePortfolio(@PathVariable Long id, Principal principal) {
        resumeService.deleteResume(principal.getName(), id);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
