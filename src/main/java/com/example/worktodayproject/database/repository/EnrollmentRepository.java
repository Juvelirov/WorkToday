package com.example.worktodayproject.database.repository;

import com.example.worktodayproject.database.entity.Enrollment;
import com.example.worktodayproject.database.entity.IntershipsInfo;
import com.example.worktodayproject.database.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Взаимодействие с таблицей enrollments
 */
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    /**
     * Проверить наличие по пользователю истажировке
     * @param currentUser текущий пользователь
     * @param intershipsInfo стажировка
     * @return
     */
    boolean existsByUsersAndIntershipsInfo(Users currentUser, IntershipsInfo intershipsInfo);
}
