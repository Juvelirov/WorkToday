package com.example.worktodayproject.database.repository;

import com.example.worktodayproject.database.entity.Resumes;
import com.example.worktodayproject.database.entity.UsersInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Взаимодействие с таблицей Resumes
 */
public interface ResumesRepository extends JpaRepository<Resumes, Long> {

    /**
     * Найти резьюме по его id и профилю пользователя
     * @param id идентификатор
     * @param usersInfo профиль
     * @return резюме
     */
    Resumes findByIdAndUserInfo(Long id, UsersInfo usersInfo);

    /**
     * Получить все резюме
     * @param usersInfo профиль
     * @return все резюме
     */
    List<Resumes> findAllByUserInfo(UsersInfo usersInfo);
}
