package com.example.worktodayproject.service;

import com.example.worktodayproject.database.entity.Tasks;
import com.example.worktodayproject.database.repository.TasksRepository;
import com.example.worktodayproject.dto.request.TaskDto;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

/**
 * Сервис заданий
 */
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class TasksService {

    TasksRepository tasksRepository;

    /**
     * Создать задание
     */
    public void createTask(TaskDto taskDto) {
        Tasks task = new Tasks();

        task.setTitle(taskDto.title());
        task.setInfo(taskDto.info());
        task.setDeadline(taskDto.deadline());

        tasksRepository.save(task);
    }
}
