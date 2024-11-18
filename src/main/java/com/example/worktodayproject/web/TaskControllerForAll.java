package com.example.worktodayproject.web;

import com.example.worktodayproject.dto.request.TaskCompleteDto;
import com.example.worktodayproject.dto.response.TaskResponse;
import com.example.worktodayproject.service.StudentTasksService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Контроллер заданий для всех
 */
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/api/v1/private/student/task")
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
    public ResponseEntity<String> startTask(@PathVariable Long id,
                                            Principal principal) {
        studentTasksService.startTask(principal.getName(), id);
        return ResponseEntity.ok("success");
    }

    /**
     * Завершить задание
     * @param id id задания
     * @param principal текущий пользователь
     * @param taskCompleteDto дто завершенной задачи
     * @return ответ
     */
    @PostMapping("/complete-task/{id}")
    public ResponseEntity<String> completeTask(@PathVariable Long id,
                                               Principal principal,
                                               @RequestBody TaskCompleteDto taskCompleteDto) {
        studentTasksService.completeTask(principal.getName(), id, taskCompleteDto);
        return ResponseEntity.ok("success");
    }
}
