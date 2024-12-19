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

    ReportsRepository reportsRepository;
    UsersInfoRepository usersInfoRepository;
    UsersRepository usersRepository;
    IntershipInfoRepository intershipInfoRepository;

    /**
     * Создать отчет по студенту от HR
     * @param username имя пользователя
     * @param hrUsername имя пользователя HR
     * @param internshipId id стажировки
     * @param reportDto дто отчетов
     */
    public void createReport(String username, String hrUsername, Long internshipId, ReportDto reportDto) {
        Users user = usersRepository.findByLogin(username);
        UsersInfo usersInfo = usersInfoRepository.findByUsers(user);
        Optional<IntershipsInfo> intershipsInfoOptional = intershipInfoRepository.findById(internshipId);
        IntershipsInfo intershipsInfo = intershipsInfoOptional.get();

        Reports reports = new Reports();

        reports.setTitle(reportDto.title());
        reports.setDescription(reportDto.description());
        reports.setUserInfo(usersInfo);
        reports.setIntershipsInfo(intershipsInfo);

        reportsRepository.save(reports);
    }
}
