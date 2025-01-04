package com.example.worktodayproject.service;

import com.dropbox.core.DbxException;
import com.example.worktodayproject.database.entity.IntershipsInfo;
import com.example.worktodayproject.database.entity.Reports;
import com.example.worktodayproject.database.entity.Users;
import com.example.worktodayproject.database.entity.UsersInfo;
import com.example.worktodayproject.database.repository.IntershipInfoRepository;
import com.example.worktodayproject.database.repository.ReportsRepository;
import com.example.worktodayproject.database.repository.UsersInfoRepository;
import com.example.worktodayproject.database.repository.UsersRepository;
import com.example.worktodayproject.dto.request.ReportDto;
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
    UsersInfoService usersInfoService;
    DropBoxService dropBoxService;

    /**
     * Создать отчет по студенту от HR
     * @param username имя пользователя
     * @param internshipId id стажировки
     * @param reportDto дто отчетов
     */
    public void createReport(String username, Long internshipId, ReportDto reportDto) throws IOException, DbxException {
        Users user = usersRepository.findByLogin(username);
        UsersInfo usersInfo = usersInfoRepository.findByUsers(user);
        Optional<IntershipsInfo> intershipsInfoOptional = intershipInfoRepository.findById(internshipId);
        IntershipsInfo intershipsInfo = intershipsInfoOptional.get();
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
