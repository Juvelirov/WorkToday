package com.example.worktodayproject.database.repository;

import com.example.worktodayproject.database.entity.Resumes;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Взаимодействие с таблицей Resumes
 */
public interface ResumesRepository extends JpaRepository<Resumes, Long> {
}
