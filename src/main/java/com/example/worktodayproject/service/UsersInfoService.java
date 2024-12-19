package com.example.worktodayproject.service;

import com.example.worktodayproject.database.entity.*;
import com.example.worktodayproject.database.repository.*;
import com.example.worktodayproject.dto.request.UsersInfoDto;
import com.example.worktodayproject.dto.response.UsersInfoResponse;
import com.example.worktodayproject.exception.custom.UserInfoNotFoundException;
import com.example.worktodayproject.utils.MapperUtils;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис страницы пользователя
 */
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class UsersInfoService {

    MapperUtils mapperUtils = new MapperUtils();

    UsersInfoRepository usersInfoRepository;
    UsersRepository usersRepository;

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

    /**
     * Получить информацию по текущему пользователю
     * @param username имя текущего пользователя
     */
    public UsersInfoResponse getUserInfo(String username) {
        Users currentUser = usersRepository.findByLogin(username);
        UsersInfo usersInfo = usersInfoRepository.findByUsers(currentUser);
        if (usersInfo == null) {
            throw new UserInfoNotFoundException(currentUser.getUsername());
        }

        if (usersInfo.getRecomendationFlag() == null) {
            usersInfo.setRecomendationFlag(Boolean.FALSE);
        }
        if (usersInfo.getPortfolio() == null) {
            usersInfo.setPortfolio(null);
        }
        return mapperUtils.mappingUserInfo(usersInfo);
    }

    /**
     * Получить данные профиля другого пользователя
     * @return ответ профиля пользователя
     * @param username имя пользователя
     */
    public UsersInfoResponse getOtherUserInfo(String username) {
        Users currentUser = usersRepository.findByLogin(username);
        UsersInfo usersInfo = usersInfoRepository.findByUsers(currentUser);
        if (usersInfo == null) {
            throw new UserInfoNotFoundException(currentUser.getUsername());
        }

        return mapperUtils.mappingUserInfo(usersInfo);
    }

    /**
     * Получить все профили пользователей
     * @return список ответов профилей пользователей
     */
    public List<UsersInfoResponse> getAllUsersInfo() {
        return mapperUtils.mappingUserInfoList(usersInfoRepository.findAll());
    }

    /**
     * Установить портфолио за пользователем
     * @param portfolios портфолио
     * @param username имя пользователя, которому надо добавить портфолио
     */
    public void setPortfolioForUserInfo(Portfolios portfolios, String username) {
        Users currentUser = usersRepository.findByLogin(username);
        UsersInfo usersInfo = usersInfoRepository.findByUsers(currentUser);

        usersInfo.setPortfolio(portfolios);
        usersInfoRepository.save(usersInfo);
    }

    /**
     * Установить резюме за пользователем
     * @param resumes резюме
     * @param username имя пользователя, которому надо добавить резюме
     */
    public void setResumeForUserInfo(Resumes resumes, String username) {
        Users currentUser = usersRepository.findByLogin(username);
        UsersInfo usersInfo = usersInfoRepository.findByUsers(currentUser);

        usersInfo.setResume(resumes);
        usersInfoRepository.save(usersInfo);
    }

    /**
     * Добавить задачу пользователю
     * @param task задача
     * @param username пользователь
     */
    public void setTaskForUserInfo(Tasks task, String username) {
        Users currentUser = usersRepository.findByLogin(username);
        UsersInfo usersInfo = usersInfoRepository.findByUsers(currentUser);

        usersInfo.getTasks().add(task);
        usersInfoRepository.save(usersInfo);
    }
}
