package com.example.worktodayproject.service;

import com.example.worktodayproject.database.entity.Enrollment;
import com.example.worktodayproject.database.entity.IntershipsInfo;
import com.example.worktodayproject.database.entity.Users;
import com.example.worktodayproject.database.entity.UsersInfo;
import com.example.worktodayproject.database.enums.EnrollStatus;
import com.example.worktodayproject.database.repository.EnrollmentRepository;
import com.example.worktodayproject.database.repository.IntershipInfoRepository;
import com.example.worktodayproject.database.repository.UsersInfoRepository;
import com.example.worktodayproject.database.repository.UsersRepository;
import com.example.worktodayproject.dto.response.EnrollResponse;
import com.example.worktodayproject.exception.custom.AuthorizedUserException;
import com.example.worktodayproject.exception.custom.IntershipTitleNotFoundException;
import com.example.worktodayproject.exception.custom.UserAlreadyEnrolledException;
import com.example.worktodayproject.utils.MapperUtils;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Сервис для записи на стажировку
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class EnrollmentService {

    MapperUtils mapperUtils = new MapperUtils();

    EnrollmentRepository enrollmentRepository;
    UsersRepository usersRepository;
    IntershipInfoRepository intershipInfoRepository;
    UsersInfoRepository usersInfoRepository;

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
        UsersInfo usersInfo = usersInfoRepository.findByUsers(currentUser);
        if (enrollmentRepository.existsByUsersAndIntershipsInfo(currentUser, intershipsInfo)) {
            throw new UserAlreadyEnrolledException(currentUser.getUsername());
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setUsers(currentUser);
        enrollment.setIntershipsInfo(intershipsInfo);
        enrollment.setUserInfo(usersInfo);
        enrollment.setStatus(EnrollStatus.PENDING);
        enrollmentRepository.save(enrollment);
    }

    public void cancelEnroll(String login, Long id) {
        Users currentUser = usersRepository.findByLogin(login);
        if (currentUser == null) {
            throw new AuthorizedUserException("Только авторизованные пользователи могут отменить стажировку");
        }

        Optional<IntershipsInfo> intershipsInfoOptional = intershipInfoRepository.findById(id);
        if (intershipsInfoOptional.isEmpty()) {
            throw new IntershipTitleNotFoundException(id);
        }

        IntershipsInfo intershipsInfo = intershipsInfoOptional.get();

        UsersInfo userInfo = usersInfoRepository.findByUsers(currentUser);
        userInfo.getIntershipsInfo().add(intershipsInfo);
        usersInfoRepository.save(userInfo);

        intershipsInfo.setUserInfo(userInfo);
        intershipInfoRepository.save(intershipsInfo);

        enrollmentRepository.deleteByUsersAndIntershipsInfoId(currentUser, id);
    }

    public List<EnrollResponse> getUserEnrolls(String hrUsername) {
        Users hrUser = usersRepository.findByLogin(hrUsername);
        if (hrUser == null) {
            throw new AuthorizedUserException("HR пользователь не найден");
        }

        List<IntershipsInfo> hrCreatedInterships = intershipInfoRepository.findByUser(hrUser);

        List<Enrollment> allEnrollments = hrCreatedInterships.stream()
                .flatMap(intershipsInfo -> intershipsInfo.getEnrollments().stream())
                .collect(Collectors.toList());

        List<Enrollment> enrollments = new ArrayList<>();

        for (Enrollment enrollment : allEnrollments) {
            if (!enrollment.getStatus().equals(EnrollStatus.ACCEPTED)
                    && !enrollment.getStatus().equals(EnrollStatus.REJECTED)) {
                enrollments.add(enrollment);
            }
        }

        return mapperUtils.mappingEnrollList(enrollments);
    }

    public List<EnrollResponse> getPersonalEnrolls(String username) {
        Users user = usersRepository.findByLogin(username);
        List<Enrollment> enrollments = enrollmentRepository.findByUsers(user);

        return mapperUtils.mappingEnrollList(enrollments);
    }

    public void setEnrollAcceptStatus(String username, String hrUsername, Long enrollId) {
        Users hrUser = usersRepository.findByLogin(hrUsername);
        if (hrUser == null) {
            throw new AuthorizedUserException("HR пользователь не найден");
        }

        Users user = usersRepository.findByLogin(username);
        Enrollment enrollment = enrollmentRepository.findByUsersAndId(user, enrollId);

        if(!enrollment.getIntershipsInfo().getUser().equals(hrUser)) {
            throw new IllegalArgumentException("HR пользователь не является владельцем стажировки");
        }

        if (enrollment.getStatus().equals(EnrollStatus.PENDING)) {
            enrollment.setStatus(EnrollStatus.ACCEPTED);
            enrollmentRepository.save(enrollment);
        }
    }

    public void setEnrollRejectStatus(String username, String hrUsername, Long enrollId) {
        Users hrUser = usersRepository.findByLogin(hrUsername);
        if (hrUser == null) {
            throw new AuthorizedUserException("HR пользователь не найден");
        }

        Users user = usersRepository.findByLogin(username);
        Enrollment enrollment = enrollmentRepository.findByUsersAndId(user, enrollId);

        if(!enrollment.getIntershipsInfo().getUser().equals(hrUser)) {
            throw new IllegalArgumentException("HR пользователь не является владельцем стажировки");
        }

        if (enrollment.getStatus().equals(EnrollStatus.PENDING)) {
            enrollment.setStatus(EnrollStatus.REJECTED);
            enrollmentRepository.save(enrollment);
        }
    }
}
