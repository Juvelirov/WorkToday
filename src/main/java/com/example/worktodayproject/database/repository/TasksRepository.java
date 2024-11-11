package com.example.worktodayproject.database.repository;

import com.example.worktodayproject.database.entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Взаимодействие с таблицей Tasks
 */
public interface TasksRepository extends JpaRepository<Tasks, Long> {
}
