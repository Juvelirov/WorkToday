package com.example.worktodayproject.service;

import com.example.worktodayproject.database.entity.Users;
import com.example.worktodayproject.database.entity.UsersInfo;
import com.example.worktodayproject.database.repository.*;
import com.example.worktodayproject.dto.UsersInfoDto;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

/**
 * Сервис страницы пользователя
 */
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class UsersInfoService {

    UsersInfoRepository usersInfoRepository;
    UsersRepository usersRepository;
    ResumesRepository resumesRepository;
    PortfoliosRepository portfoliosRepository;
    ReportsRepository reportsRepository;
    TasksRepository tasksRepository;

    /**
     * Обновить информацию о пользователе
     * @param usersInfoDto дто
     * @param username логин
     */
    public void updateUsersInfo(UsersInfoDto usersInfoDto, String username) {
        Users user = usersRepository.findByLogin(username);
        UsersInfo usersInfo = usersInfoRepository.findByUsers(user);

        if (usersInfoDto.name() != null) {
            usersInfo.setName(usersInfoDto.name());
        }
        if (usersInfoDto.surname() != null) {
            usersInfo.setSurname(usersInfoDto.surname());
        }
        if (usersInfoDto.patronymic() != null) {
            usersInfo.setPatronymic(usersInfoDto.patronymic());
        }
        if (usersInfoDto.phoneNumber() != null) {
            usersInfo.setPhoneNumber(usersInfoDto.phoneNumber());
        }
        if (usersInfoDto.town() != null) {
            usersInfo.setTown(usersInfoDto.town());
        }

        usersInfoRepository.save(usersInfo);
    }
}
