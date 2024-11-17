package com.example.worktodayproject.database.repository;

import com.example.worktodayproject.database.entity.Tasks;
import com.example.worktodayproject.database.entity.UsersInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Взаимодействие с таблицей Tasks
 */
public interface TasksRepository extends JpaRepository<Tasks, Long> {

    /**
     * Получить задачу пользователя по его id
     * @param id id
     * @param usersInfo имя пользователя
     * @return задание
     */
    Tasks findByIdAndUsersInfo(Long id, UsersInfo usersInfo);

    /**
     * Получить все задание по профилю
     * @param usersInfo профиль
     * @return все задания
     */
    List<Tasks> findAllByUsersInfo(UsersInfo usersInfo);
}
