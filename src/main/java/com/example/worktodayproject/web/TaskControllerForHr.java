package com.example.worktodayproject.web;

import com.example.worktodayproject.dto.request.CheckTaskDto;
import com.example.worktodayproject.dto.request.TaskCompleteDto;
import com.example.worktodayproject.dto.request.TaskDto;
import com.example.worktodayproject.dto.response.TaskResponse;
import com.example.worktodayproject.service.HrTasksService;
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
 * Контроллер заданий для HR
 */
//@RestController
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//@RequiredArgsConstructor
//@RequestMapping("/api/v1/private/hr/task")
//@Validated
//public class TaskControllerForHr {
//
//    HrTasksService hrTasksService;
//
//    /**
//     * Назначить задачу студенту
//     * @param internshipId id стажировки
//     * @param studentId id студента
//     * @param taskDto дто задачи
//     * @param principal текущий пользователь
//     */
//    @PostMapping("/create/{internshipId}/{studentId}")
//    public void assignTask(@PathVariable Long internshipId,
//                           @PathVariable Long studentId,
//                           @Valid @RequestBody TaskDto taskDto,
//                           Principal principal) {
//        hrTasksService.assignTask(internshipId, studentId, taskDto, principal.getName());
//    }
//
//    /**
//     * Получить задание пользователя, к которому привязан hr
//     * @param username имя пользователя
//     * @param id id задания
//     * @param principal текущий пользователь
//     * @return ответ задачи
//     */
//    @GetMapping("/{username}/{id}")
//    public TaskResponse getUserTask(@PathVariable String username,
//                                    @PathVariable Long id,
//                                    Principal principal) {
//        return hrTasksService.getHrUsersTask(username, id, principal.getName());
//    }
//
//    /**
//     * Получить все задания пользователя, которому привязан hr
//     * @param principal текущий пользователь
//     * @param username имя пользователя
//     * @return список задач
//     */
//    @GetMapping("/{username}")
//    public List<TaskResponse> getAllUsersTasks(@PathVariable String username,
//                                               Principal principal) {
//        return hrTasksService.getAllHrUsersTasks(username, principal.getName());
//    }
//
//    /**
//     * Удалить задание пользователя, к которому привязан hr
//     * @param username имя пользователя
//     * @param id id
//     * @param principal текущий пользователь
//     * @return ответ
//     */
//    @DeleteMapping("/delete/{username}/{id}")
//    public ResponseEntity<Map<String, Object>> deleteUserTask(@PathVariable String username,
//                                                 @PathVariable Long id,
//                                                 Principal principal) {
//        hrTasksService.deleteTask(id, username, principal.getName());
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("status", "success");
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    /**
//     * Проверить задание пользователя
//     * @param id id задания
//     * @param username имя пользователя
//     * @param principal имя текущего пользователя
//     * @param checkTaskDto дто проверки задания
//     * @return ответ
//     */
//    @PostMapping("/check/{username}/{id}")
//    public ResponseEntity<Map<String, Object>> checkUserTask(@PathVariable Long id,
//                                                @PathVariable String username,
//                                                Principal principal,
//                                                @Valid @RequestBody CheckTaskDto checkTaskDto) {
//        hrTasksService.checkUserTask(id, username, principal.getName(), checkTaskDto);
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("status", "success");
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//}
