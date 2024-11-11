package com.example.worktodayproject.database.repository;

import com.example.worktodayproject.database.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Взаимодействие с таблицей Tags
 */
public interface TagsRepository extends JpaRepository<Tags, Long> {
    /**
     * Узнать наличие тега по имени
     * @param name имя тега
     * @return true или false
     */
    boolean existsByName(String name);

    /**
     * Найти тег по имени
     * @param name имя тега
     * @return тег
     */
    Tags findByName(String name);
}
