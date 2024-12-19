package com.example.worktodayproject.web;

import com.example.worktodayproject.dto.response.IntershipInfoResponse;
import com.example.worktodayproject.service.IntershipInfoService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер стажировок для HR и STUDENT
 */
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/api/v1/private/student/interships")
public class IntershipInfoControllerForAll {

    IntershipInfoService intershipInfoService;

    /**
     * Получить стажировку по ее id
     * @param id идентификатор
     * @return ответ стажировки
     */
    @GetMapping("/{id}")
    public IntershipInfoResponse getIntership(@PathVariable Long id) {
        return intershipInfoService.getIntership(id);
    }

    /**
     * Получить все стажировки
     * @return список стажировок
     */
    @GetMapping()
    public List<IntershipInfoResponse> getAllInterships() {
        return intershipInfoService.getAllInterships();
    }

    /**
     * Найти стажировки по запросу
     * @param query
     * @return список стажировок по запросу
     */
    @GetMapping("/search")
    public List<IntershipInfoResponse> getIntershipByQuery(@RequestParam String query) {
        return intershipInfoService.searchIntership(query);
    }
}
