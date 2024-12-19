package com.example.worktodayproject.database.repository;

import com.example.worktodayproject.database.entity.Users;
import com.example.worktodayproject.database.entity.UsersInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Взаимодействие с таблицей UsersInfo
 */
public interface UsersInfoRepository extends JpaRepository<UsersInfo, Long> {
    UsersInfo findByUsers(Users user);
}
