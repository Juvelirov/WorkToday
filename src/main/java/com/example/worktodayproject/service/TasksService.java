package com.example.worktodayproject.service;

import com.example.worktodayproject.database.entity.*;
import com.example.worktodayproject.database.enums.TaskStatus;
import com.example.worktodayproject.database.repository.*;
import com.example.worktodayproject.dto.request.TaskDto;
import com.example.worktodayproject.dto.response.PortfolioResponse;
import com.example.worktodayproject.dto.response.TaskResponse;
import com.example.worktodayproject.exception.custom.*;
import com.example.worktodayproject.utils.MapperUtils;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис заданий
 */
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class TasksService {

    MapperUtils mapperUtils = new MapperUtils();

    UsersInfoService usersInfoService;
    TasksRepository tasksRepository;
    UsersRepository usersRepository;
    IntershipInfoRepository intershipInfoRepository;
    EnrollmentRepository enrollmentRepository;
    UsersInfoRepository usersInfoRepository;

    /**
     * Назначить задачу
     */
    public void assignTask(Long internshipId, Long studentId, TaskDto taskDto, String hrUsername) {
        Users hrUser = usersRepository.findByLogin(hrUsername);
        if (hrUser == null) {
            throw new AuthorizedUserException("Только HR может назначить задачу");
        }

        Optional<IntershipsInfo> intershipsInfoOptional = intershipInfoRepository.findById(internshipId);
        if (intershipsInfoOptional.isEmpty()) {
            throw new InternshipNotFoundException(internshipId);
        }
        IntershipsInfo intershipsInfo = intershipsInfoOptional.get();

        if (!intershipsInfo.getUser().equals(hrUser)) {
            throw new AuthorizedUserException("HR не имеет доступа к данной стажировке");
        }

        Optional<Users> studentOptional = usersRepository.findById(studentId);
        if (studentOptional.isEmpty()) {
            throw new UserIdNotFoundException(studentId);
        }
        Users student = studentOptional.get();

        if (!enrollmentRepository.existsByUsersAndIntershipsInfo(student, intershipsInfo)) {
            throw new UserNotEnrolledException();
        }

        Tasks task = new Tasks();
        task.setTitle(taskDto.title());
        task.setInfo(taskDto.info());
        task.setDeadline(taskDto.deadline());
        task.setUrl(taskDto.url());
        task.setFilePath(taskDto.filePath());
        task.setStatus(TaskStatus.NOT_STARTED);
        task.setGrade(taskDto.grade());
        task.setResult(taskDto.result());
        task.setUsersInfo(usersInfoRepository.findByUsers(usersRepository.findByLogin(student.getLogin())));
        task.setIntershipsInfo(intershipsInfo);

        usersInfoService.setTaskForUserInfo(task, student.getLogin());

        tasksRepository.save(task);
    }

    /**
     * Получить задание пользователя по его id
     * @param username имя пользователя
     * @param id id
     * @return ответ задачи
     */
    public TaskResponse getUserTask(String username, Long id, String hrUsername) {
        Users user = usersRepository.findByLogin(username);
        UsersInfo usersInfo = usersInfoRepository.findByUsers(user);

        Tasks tasks = tasksRepository.findByIdAndUsersInfo(id, usersInfo);
        Users hrUser = usersRepository.findByLogin(hrUsername);
        if (!tasks.getIntershipsInfo().getUser().equals(hrUser)) {
            throw new AuthorizedUserException("Вы не привязаны к этому пользователю");
        }

        return mapperUtils.mappingTasks(tasks);
    }

    /**
     * Получить все задания пользователя
     * @param username имя пользователя
     * @return список заданий
     */
    public List<TaskResponse> getAllUserTasks(String username, String hrUsername) {
        Users user = usersRepository.findByLogin(username);
        UsersInfo usersInfo = usersInfoRepository.findByUsers(user);
        Users hrUser = usersRepository.findByLogin(hrUsername);
        List<Tasks> tasks = tasksRepository.findAllByUsersInfo(usersInfo);

        for (Tasks userTasks : tasks) {
            if (!userTasks.getIntershipsInfo().getUser().equals(hrUser)) {
                throw new AuthorizedUserException("Вы не привязаны к этому пользователю");
            }
        }

        return mapperUtils.mappingTasksList(tasks);
    }

    /**
     * Удалить задачу пользователю
     * @param id id
     * @param username имя пользователя
     */
    public void deleteTask(Long id, String username, String hrUsername) {
        Optional<Tasks> tasksOptional = tasksRepository.findById(id);
        if (tasksOptional.isEmpty()) {
            throw new RuntimeException();
        }

        Tasks tasks = tasksOptional.get();
        Users currentUser = usersRepository.findByLogin(username);
        Users hrUser = usersRepository.findByLogin(hrUsername);
        if (!tasks.getIntershipsInfo().getUser().equals(hrUser)) {
            throw new AuthorizedUserException("Вы не привязаны к этому пользователю");
        }

        if (!tasks.getUsersInfo().getUsers().equals(currentUser)) {
            throw new AuthorizedUserException("Текущий пользователь не является автором этой задачи.");
        }

        tasksRepository.delete(tasks);
    }
}
