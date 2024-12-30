package com.example.worktodayproject.service;

import com.example.worktodayproject.database.entity.IntershipsInfo;
import com.example.worktodayproject.database.entity.Reports;
import com.example.worktodayproject.database.entity.Users;
import com.example.worktodayproject.database.entity.UsersInfo;
import com.example.worktodayproject.database.repository.IntershipInfoRepository;
import com.example.worktodayproject.database.repository.ReportsRepository;
import com.example.worktodayproject.database.repository.UsersInfoRepository;
import com.example.worktodayproject.database.repository.UsersRepository;
import com.example.worktodayproject.dto.request.ReportDto;
import com.example.worktodayproject.utils.FileProcessingUtils;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Сервис отчетов
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class ReportsService {

    static String UPLOAD_PATH = "src/main/resources/static/report";
    FileProcessingUtils fileProcessingUtils = new FileProcessingUtils();

    ReportsRepository reportsRepository;
    UsersInfoRepository usersInfoRepository;
    UsersRepository usersRepository;
    IntershipInfoRepository intershipInfoRepository;
    UsersInfoService usersInfoService;

    /**
     * Создать отчет по студенту от HR
     * @param username имя пользователя
     * @param internshipId id стажировки
     * @param reportDto дто отчетов
     */
    public void createReport(String username, Long internshipId, ReportDto reportDto) {
        Users user = usersRepository.findByLogin(username);
        UsersInfo usersInfo = usersInfoRepository.findByUsers(user);
        Optional<IntershipsInfo> intershipsInfoOptional = intershipInfoRepository.findById(internshipId);
        IntershipsInfo intershipsInfo = intershipsInfoOptional.get();
        String fileUrl = fileProcessingUtils.uploadFile(reportDto.filePath(), UPLOAD_PATH);

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
