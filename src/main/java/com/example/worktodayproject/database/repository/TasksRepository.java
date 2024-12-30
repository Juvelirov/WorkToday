package com.example.worktodayproject.database.repository;

import com.example.worktodayproject.database.entity.Tasks;
import com.example.worktodayproject.database.entity.UsersInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Взаимодействие с таблицей Tasks
 */
public interface TasksRepository extends JpaRepository<Tasks, Long> {

}
