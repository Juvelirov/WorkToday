package com.example.worktodayproject.database.repository;

import com.example.worktodayproject.database.entity.IntershipsInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Взаимодействие с таблицей IntershipInfo
 */
public interface IntershipInfoRepository extends JpaRepository<IntershipsInfo, Long> {
    /**
     * Получить стажировку по его id
     * @param id идентификатор
     * @return стажировка
     */
    Optional<IntershipsInfo> findById(Long id);

    /**
     * Проверить наличие стажировки по id
     * @param id идентификатор
     * @return true или false
     */
    boolean existsById(Long id);

    /**
     * Найти стажировку по названию или по тегу
     * @param title название
     * @return список стажировок по запросу
     */
    // TODO ПОИСК ПО ТЕГАМ
    List<IntershipsInfo> findByTitleContaining(String title);
}
