package com.example.worktodayproject.database.repository;


import com.example.worktodayproject.database.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Взаимодейтсвие с таблицей Users
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    /**
     * Найти пользователя по имени
     * @param login имя пользователя
     * @return сведения о пользователе
     */
    Users findByLogin(String login);

    /**
     * Удалить пользователя по имени
     * @param login имя пользователя
     */
    void deleteByLogin(String login);

    /**
     * Найти всех пользователей
     * @return список пользователей
     */
    List<Users> findAll();
}
