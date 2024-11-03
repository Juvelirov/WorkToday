package com.example.worktodayproject.database.repository;

import com.example.worktodayproject.database.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Взаимодействие с таблицей Roles
 */
public interface RoleRepository extends JpaRepository<Roles, Long> {

    /**
     * Найти роль по названию
     * @param role название роли
     * @return роль
     */
    Roles findByRole(String role);
}
