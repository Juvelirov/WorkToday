package com.example.worktodayproject.service;

import com.example.worktodayproject.database.entity.Enrollment;
import com.example.worktodayproject.database.entity.IntershipsInfo;
import com.example.worktodayproject.database.entity.Users;
import com.example.worktodayproject.database.repository.EnrollmentRepository;
import com.example.worktodayproject.database.repository.IntershipInfoRepository;
import com.example.worktodayproject.database.repository.UsersRepository;
import com.example.worktodayproject.exception.custom.AuthorizedUserException;
import com.example.worktodayproject.exception.custom.IntershipTitleNotFoundException;
import com.example.worktodayproject.exception.custom.UserAlreadyEnrolledException;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Сервис для записи на стажировку
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class EnrollmentService {

    EnrollmentRepository enrollmentRepository;
    UsersRepository usersRepository;
    IntershipInfoRepository intershipInfoRepository;

    /**
     * Записать пользователя на стажировку
     * @param login имя пользователя
     * @param id идентификатор стажировки
     */
    public void enrollUser(String login, Long id) {
        Users currentUser = usersRepository.findByLogin(login);
        if (currentUser == null) {
            throw new AuthorizedUserException("Только авторизованные пользователи могут записаться на стажировку");
        }

        Optional<IntershipsInfo> intershipsInfoOptional = intershipInfoRepository.findById(id);
        if (intershipsInfoOptional.isEmpty()) {
            throw new IntershipTitleNotFoundException(id);
        }

        IntershipsInfo intershipsInfo = intershipsInfoOptional.get();
        if (enrollmentRepository.existsByUsersAndIntershipsInfo(currentUser, intershipsInfo)) {
            throw new UserAlreadyEnrolledException(currentUser.getUsername());
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setUsers(currentUser);
        enrollment.setIntershipsInfo(intershipsInfo);
        enrollmentRepository.save(enrollment);
    }
}
