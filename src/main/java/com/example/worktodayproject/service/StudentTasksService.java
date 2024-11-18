package com.example.worktodayproject.service;

import com.example.worktodayproject.database.entity.Tasks;
import com.example.worktodayproject.database.entity.Users;
import com.example.worktodayproject.database.entity.UsersInfo;
import com.example.worktodayproject.database.enums.TaskGrade;
import com.example.worktodayproject.database.enums.TaskStatus;
import com.example.worktodayproject.database.repository.*;
import com.example.worktodayproject.dto.request.TaskCompleteDto;
import com.example.worktodayproject.dto.response.TaskResponse;
import com.example.worktodayproject.exception.custom.AuthorizedUserException;
import com.example.worktodayproject.utils.MapperUtils;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис заданий для студентов
 */
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class StudentTasksService {

    MapperUtils mapperUtils = new MapperUtils();

    UsersInfoService usersInfoService;
    TasksRepository tasksRepository;
    UsersRepository usersRepository;
    IntershipInfoRepository intershipInfoRepository;
    EnrollmentRepository enrollmentRepository;
    UsersInfoRepository usersInfoRepository;

    /**
     * Получить задание пользователя по его id
     * @param username имя пользователя
     * @param id id
     * @return ответ задачи
     */
    public TaskResponse getUsersTask(String username, Long id) {
        Users user = usersRepository.findByLogin(username);
        UsersInfo usersInfo = usersInfoRepository.findByUsers(user);
        Tasks tasks = tasksRepository.findByIdAndUsersInfo(id, usersInfo);

        return mapperUtils.mappingTasks(tasks);
    }

    /**
     * Получить все задания пользователя
     * @param username имя пользователя
     * @return список заданий
     */
    public List<TaskResponse> getAllUsersTasks(String username) {
        Users user = usersRepository.findByLogin(username);
        UsersInfo usersInfo = usersInfoRepository.findByUsers(user);
        List<Tasks> tasks = tasksRepository.findAllByUsersInfo(usersInfo);

        return mapperUtils.mappingTasksList(tasks);
    }

    /**
     * Начать выполнять задание
     * @param username имя текущего пользователя
     * @param taskId id задачи
     */
    public void startTask(String username, Long taskId) {
        Users user = usersRepository.findByLogin(username);
        UsersInfo usersInfo = usersInfoRepository.findByUsers(user);
        Tasks tasks = tasksRepository.findByIdAndUsersInfo(taskId, usersInfo);

        tasks.setStatus(TaskStatus.IN_PROGRESS);
        tasksRepository.save(tasks);
    }

    /**
     * Завершить выполнение задачи
     * @param username имя текущего пользователя
     * @param taskId id задания
     * @param taskCompleteDto дто завершенного задания
     */
    public void completeTask(String username, Long taskId, TaskCompleteDto taskCompleteDto) {
        Users user = usersRepository.findByLogin(username);
        UsersInfo usersInfo = usersInfoRepository.findByUsers(user);
        Tasks tasks = tasksRepository.findByIdAndUsersInfo(taskId, usersInfo);

        tasks.setStatus(TaskStatus.COMPLETE);
        tasks.setGrade(TaskGrade.NOT_VERIFIED);
        tasks.setResult(taskCompleteDto.result());
        tasksRepository.save(tasks);
    }
}
