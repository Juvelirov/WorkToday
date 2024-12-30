package com.example.worktodayproject.database.repository;

import com.example.worktodayproject.database.entity.Reports;
import com.example.worktodayproject.database.entity.Users;
import com.example.worktodayproject.database.entity.UsersInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Взаимодействие с таблицей Reports
 */
public interface ReportsRepository extends JpaRepository<Reports, Long> {

    void deleteByUserInfoAndIntershipsInfoId(UsersInfo usersInfo, Long internshipId);
}
