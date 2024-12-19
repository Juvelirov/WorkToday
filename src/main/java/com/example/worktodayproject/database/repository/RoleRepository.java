package com.example.worktodayproject.database.repository;

import com.example.worktodayproject.database.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Взаимодействие с таблицей Roles
 */
@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {

    /**
     * Найти роль по названию
     * @param role название роли
     * @return роль
     */
    Roles findByRole(String role);

    /**
     * Проверяет наличие роли
     * @param role роль
     * @return результат проверки
     */
    boolean existsByRole(String role);
}
