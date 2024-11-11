package com.example.worktodayproject.database.repository;

import com.example.worktodayproject.database.entity.Portfolios;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Взаимодействие с таблицей Portfolios
 */
public interface PortfoliosRepository extends JpaRepository<Portfolios, Long> {
}
