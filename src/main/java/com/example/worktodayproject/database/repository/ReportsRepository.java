package com.example.worktodayproject.database.repository;

import com.example.worktodayproject.database.entity.Reports;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Взаимодействие с таблицей Reports
 */
public interface ReportsRepository extends JpaRepository<Reports, Long> {
}
