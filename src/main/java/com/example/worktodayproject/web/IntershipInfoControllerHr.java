package com.example.worktodayproject.web;

import com.example.worktodayproject.dto.request.IntershipInfoDto;
import com.example.worktodayproject.dto.response.IntershipInfoResponse;
import com.example.worktodayproject.service.IntershipInfoService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * Контроллер стажировок для HR
 */
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/api/v1/private/hr/interships")
@Validated
public class IntershipInfoControllerHr {

    IntershipInfoService intershipInfoService;

    /**
     * Создание стажировки
     * @param intershipInfoDto дто
     * @param principal текущий пользователь
     */
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createIntership(@Valid @RequestBody IntershipInfoDto intershipInfoDto,
                                                               Principal principal) {
        Long id = intershipInfoService.createIntership(intershipInfoDto, principal.getName());

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("ID", id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Обновить данные стажировки
     * @param id идентификатор стажировки
     * @param intershipInfoDto дто стажировки
     * @param principal текущий пользователь
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateIntership(@PathVariable Long id,
                                @Valid @RequestBody IntershipInfoDto intershipInfoDto,
                                Principal principal) {
        intershipInfoService.updateIntership(id, intershipInfoDto, principal.getName());

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Удалить стажировку
     * @param id идентификатор стажировки
     * @param principal текущий пользователь
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteIntership(@PathVariable Long id,
                                Principal principal) {
        intershipInfoService.deleteIntership(id, principal.getName());

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
