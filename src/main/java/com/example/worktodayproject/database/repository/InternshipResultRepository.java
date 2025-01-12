package com.example.worktodayproject.database.repository;

import com.example.worktodayproject.database.entity.InternshipsResult;
import com.example.worktodayproject.database.entity.UsersInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Взаимодействие с таблицей internship_result
 */
public interface InternshipResultRepository extends JpaRepository<InternshipsResult, Long> {

    InternshipsResult findByUserInfo(UsersInfo usersInfo);
    Optional<InternshipsResult> findFirstByUserInfo(UsersInfo usersInfo);
}
