package com.example.worktodayproject.service;

import com.dropbox.core.DbxException;
import com.example.worktodayproject.database.entity.*;
import com.example.worktodayproject.database.enums.EnrollStatus;
import com.example.worktodayproject.database.repository.*;
import com.example.worktodayproject.dto.request.ReportDto;
import com.example.worktodayproject.exception.custom.AuthorizedUserException;
import com.example.worktodayproject.exception.custom.InternshipNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

/**
 * Сервис отчетов
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class ReportsService {

    ReportsRepository reportsRepository;
    UsersInfoRepository usersInfoRepository;
    UsersRepository usersRepository;
    IntershipInfoRepository intershipInfoRepository;
    EnrollmentRepository enrollmentRepository;
    UsersInfoService usersInfoService;
    DropBoxService dropBoxService;

    /**
     * Создать отчет по студенту от HR
     * @param username имя пользователя
     * @param reportDto дто отчетов
     */
    public void createReport(String username, ReportDto reportDto) throws IOException, DbxException {
        Users user = usersRepository.findByLogin(username);
        UsersInfo usersInfo = usersInfoRepository.findByUsers(user);

        Long internshipId = usersInfo.getEnrollments().getFirst().getIntershipsInfo().getId();

        Optional<IntershipsInfo> intershipsInfoOptional = intershipInfoRepository.findById(internshipId);
        IntershipsInfo intershipsInfo = intershipsInfoOptional.get();

        Enrollment userEnrollment = enrollmentRepository.findByUsersAndIntershipsInfoId(user, internshipId);
        boolean isStudentEnrolled = enrollmentRepository.existsByUsersAndIntershipsInfo(user, intershipsInfo);
        if (!isStudentEnrolled) {
            throw new AuthorizedUserException("Student " + username + " is not enrolled in" +
                    " internship with id " + internshipId);
        }
        if (userEnrollment.getStatus().equals(EnrollStatus.PENDING)) {
            throw new AuthorizedUserException("Internship in waiting");
        }

        String fileName = dropBoxService.generateFileName(reportDto.filePath().getOriginalFilename());
        String fileUrl = dropBoxService.uploadFile(reportDto.filePath(), fileName);

        Reports reports = new Reports();
        reports.setFilePath(fileUrl);
        reports.setUserInfo(usersInfo);
        reports.setIntershipsInfo(intershipsInfo);

        usersInfoService.setReportForUserInfo(reports, username);
        reportsRepository.save(reports);
    }

    public void deleteReport(String username, Long internshipId) {
        Users user = usersRepository.findByLogin(username);
        UsersInfo usersInfo = usersInfoRepository.findByUsers(user);
        reportsRepository.deleteByUserInfoAndIntershipsInfoId(usersInfo, internshipId);
    }
}
