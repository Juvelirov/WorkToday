package com.example.worktodayproject.database.repository;

import com.example.worktodayproject.database.entity.InternshipsResult;
import com.example.worktodayproject.database.entity.UsersInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Взаимодействие с таблицей internship_result
 */
public interface InternshipResultRepository extends JpaRepository<InternshipsResult, Long> {

    InternshipsResult findByUserInfo(UsersInfo usersInfo);
}
