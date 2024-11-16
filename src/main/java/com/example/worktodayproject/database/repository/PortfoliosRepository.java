package com.example.worktodayproject.database.repository;

import com.example.worktodayproject.database.entity.Portfolios;
import com.example.worktodayproject.database.entity.UsersInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Взаимодействие с таблицей Portfolios
 */
public interface PortfoliosRepository extends JpaRepository<Portfolios, Long> {

    /**
     * Получить портфолио по id
     * @param id идентификатор портфолио
     * @return портфолио
     */
    Optional<Portfolios> findById(Long id);

    /**
     * Получить все портфолио пользователя
     * @param usersInfo профиль пользователя
     * @return список портфолио пользователя
     */
    List<Portfolios> findAllByUserInfo(UsersInfo usersInfo);

    /**
     * Получить портфолио пользователя по id
     * @param id идентификатор портфолио
     * @param usersInfo профиль пользователя
     * @return портфолио пользователя по id
     */
    Portfolios findByIdAndUserInfo(Long id, UsersInfo usersInfo);
}
