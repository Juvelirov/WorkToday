package com.example.worktodayproject.database.repository;

import com.example.worktodayproject.database.entity.Enrollment;
import com.example.worktodayproject.database.entity.IntershipsInfo;
import com.example.worktodayproject.database.entity.Users;
import com.example.worktodayproject.database.entity.UsersInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

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

    void deleteByUsersAndIntershipsInfoId(Users users, Long internshipInfoId);

    List<Enrollment> findByUsers(Users users);

    Enrollment findByUsersAndId(Users users, Long enrollId);

    Enrollment findByUsersAndIntershipsInfoId(Users users, Long intershipInfoId);
}
