package com.example.worktodayproject.service;

import com.example.worktodayproject.database.entity.*;
import com.example.worktodayproject.database.repository.*;
import com.example.worktodayproject.dto.request.ResultDto;
import com.example.worktodayproject.exception.custom.AuthorizedUserException;
import com.example.worktodayproject.exception.custom.UserNotEnrolledException;
import com.example.worktodayproject.utils.MapperUtils;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Сервис для результатов стажировки
 */
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class InternshipResultService {

    MapperUtils mapperUtils = new MapperUtils();

    InternshipResultRepository internshipResultRepository;
    UsersRepository usersRepository;
    IntershipInfoRepository intershipInfoRepository;
    UsersInfoRepository usersInfoRepository;
    EnrollmentRepository enrollmentRepository;
    ReportsRepository reportsRepository;

    /**
     * Создать результат стажировки
     * @param username имя пользователя
     * @param hrUsername имя пользователя HR
     * @param internshipId id стажировки
     * @param resultDto дто результатов
     */
    public void createInternshipResult(String username,
                                       String hrUsername,
                                       Long internshipId,
                                       ResultDto resultDto) {
        Users user = usersRepository.findByLogin(username);
        Users hrUser = usersRepository.findByLogin(hrUsername);
        UsersInfo usersInfo = usersInfoRepository.findByUsers(user);
        Optional<IntershipsInfo> intershipsInfoOptional = Optional.of(intershipInfoRepository
                .findById(internshipId)
                .orElseThrow());
        IntershipsInfo intershipsInfo = intershipsInfoOptional.get();

        if (!intershipsInfo.getUser().equals(hrUser)) {
            throw new AuthorizedUserException("HR не имеет доступа к данной стажировке");
        }
        if (!enrollmentRepository.existsByUsersAndIntershipsInfo(user, intershipsInfo)) {
            throw new UserNotEnrolledException();
        }

        Reports reports = new Reports();
        reports.setTitle(resultDto.report().title());
        reports.setDescription(resultDto.report().description());
        reports.setUserInfo(usersInfo);
        reports.setIntershipsInfo(intershipsInfo);

        InternshipsResult internshipsResult = new InternshipsResult();
        internshipsResult.setMark(resultDto.mark());
        internshipsResult.setRecomendation(resultDto.recommendation());
        internshipsResult.setFinalDate(LocalDateTime.now());
        internshipsResult.setReport(reports);

        usersInfo.setRecomendationFlag(resultDto.recommendation());

        reports.setResult(internshipsResult);
        usersInfo.getReports().add(reports);

        internshipResultRepository.save(internshipsResult);
        reportsRepository.save(reports);
        usersInfoRepository.save(usersInfo);
    }
}
