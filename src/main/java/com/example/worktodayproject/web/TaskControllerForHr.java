package com.example.worktodayproject.web;

import com.example.worktodayproject.dto.request.TaskDto;
import com.example.worktodayproject.dto.response.TaskResponse;
import com.example.worktodayproject.service.TasksService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Контроллер заданий
 */
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/api/v1/private/hr/task")
public class TaskControllerForHr {

    TasksService tasksService;

    /**
     * Назначить задачу студенту
     * @param internshipId id стажировки
     * @param studentId id студента
     * @param taskDto дто задачи
     * @param principal текущий пользователь
     */
    @PostMapping("/create/{internshipId}/{studentId}")
    public void assignTask(@PathVariable Long internshipId,
                           @PathVariable Long studentId,
                           @RequestBody TaskDto taskDto,
                           Principal principal) {
        tasksService.assignTask(internshipId, studentId, taskDto, principal.getName());
    }

    /**
     * Получить задание пользователя, к которому привязан hr
     * @param username имя пользователя
     * @param id id задания
     * @param principal текущий пользователь
     * @return ответ задачи
     */
    @GetMapping("/{username}/{id}")
    public TaskResponse getUserTask(@PathVariable String username,
                                    @PathVariable Long id,
                                    Principal principal) {
        return tasksService.getUserTask(username, id, principal.getName());
    }

    /**
     * Получить все задания пользователя, которому привязан hr
     * @param principal текущий пользователь
     * @param username имя пользователя
     * @return список задач
     */
    @GetMapping("/{username}")
    public List<TaskResponse> getAllUsersTasks(@PathVariable String username,
                                               Principal principal) {
        return tasksService.getAllUserTasks(username, principal.getName());
    }

    /**
     * Удалить задание пользователя, к которому привязан hr
     * @param username имя пользователя
     * @param id id
     * @param principal текущий пользователь
     * @return ответ
     */
    @DeleteMapping("/delete/{username}/{id}")
    public ResponseEntity<String> deleteUserTask(@PathVariable String username,
                                                 @PathVariable Long id,
                                                 Principal principal) {
        tasksService.deleteTask(id, username, principal.getName());
        return ResponseEntity.ok("success");
    }
}
