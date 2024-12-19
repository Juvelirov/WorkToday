package com.example.worktodayproject.web;

import com.example.worktodayproject.dto.request.TaskCompleteDto;
import com.example.worktodayproject.dto.response.TaskResponse;
import com.example.worktodayproject.service.StudentTasksService;
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
import java.util.List;
import java.util.Map;

/**
 * Контроллер заданий для всех
 */
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/api/v1/private/student/task")
@Validated
public class TaskControllerForAll {

    StudentTasksService studentTasksService;

    /**
     * Получить все задания текущего пользователя
     * @param principal текущий пользователь
     * @return список заданий пользователя
     */
    @GetMapping("/my-tasks")
    public List<TaskResponse> getMyTasks(Principal principal) {
        return studentTasksService.getAllUsersTasks(principal.getName());
    }

    @GetMapping("/my-tasks/{id}")
    public TaskResponse getMyTask(@PathVariable Long id,
                                  Principal principal) {
        return studentTasksService.getUsersTask(principal.getName(), id);
    }

    /**
     * Начать задание
     * @param id id задания
     * @param principal текущий пользователь
     * @return ответ
     */
    @PostMapping("/start-task/{id}")
    public ResponseEntity<Map<String, Object>> startTask(@PathVariable Long id,
                                            Principal principal) {
        studentTasksService.startTask(principal.getName(), id);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Завершить задание
     * @param id id задания
     * @param principal текущий пользователь
     * @param taskCompleteDto дто завершенной задачи
     * @return ответ
     */
    @PostMapping("/complete-task/{id}")
    public ResponseEntity<Map<String, Object>> completeTask(@PathVariable Long id,
                                               Principal principal,
                                               @Valid @RequestBody TaskCompleteDto taskCompleteDto) {
        studentTasksService.completeTask(principal.getName(), id, taskCompleteDto);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
