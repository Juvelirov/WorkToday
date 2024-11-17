package com.example.worktodayproject.utils;

import com.example.worktodayproject.database.entity.*;
import com.example.worktodayproject.dto.response.*;
import com.example.worktodayproject.security.dto.response.UserResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Преобразование из одного класса в другой
 */
public class MapperUtils {

    /**
     * Превратить IntershipInfo в IntershipInfoResponse
     * @param intershipsInfo стажировка
     * @return ответ стажировки
     */
    public IntershipInfoResponse mappingIntership(IntershipsInfo intershipsInfo) {
        List<String> tagsList = new ArrayList<>();

        UserResponse userResponse = new UserResponse(intershipsInfo.getUser().getFio(),
                intershipsInfo.getUser().getLogin(),
                intershipsInfo.getUser().getEmail());

        for (Tags tags : intershipsInfo.getTags()) {
            tagsList.add(tags.getName());
        }

        return new IntershipInfoResponse(intershipsInfo.getId(),
                intershipsInfo.getTitle(),
                intershipsInfo.getDescription(),
                intershipsInfo.getFields(),
                tagsList,
                userResponse);
    }

    /**
     * Превратить список IntershipInfo в список IntershipInfoResponse
     * @param infos список IntershipInfo
     * @return список IntershipInfoResponse
     */
    public List<IntershipInfoResponse> mappingListIntarship(List<IntershipsInfo> infos) {
        List<IntershipInfoResponse> intershipInfoResponses = new ArrayList<>();
        for (IntershipsInfo info : infos) {
            IntershipInfoResponse response = new MapperUtils().mappingIntership(info);
            intershipInfoResponses.add(response);
        }

        return intershipInfoResponses;
    }

    /**
     * Превратить UsersInfo в UsersInfoResponse
     * @param usersInfo профиль пользователя
     * @return ответ профиля
     */
    public UsersInfoResponse mappingUserInfo(UsersInfo usersInfo) {
        Optional<PortfolioResponse> portfolioResponse = Optional.ofNullable(usersInfo.getPortfolio())
                .map(this::mappingPortfolio);
        Optional<ResumeResponse> resumeResponse = Optional.ofNullable(usersInfo.getResume())
                .map(this::mappingResume);
        Optional<List<TaskResponse>> taskResponses = Optional.ofNullable(usersInfo.getTasks())
                .map(tasks -> tasks.stream()
                        .map(this::mappingTasks)
                        .collect(Collectors.toList()));
        if (usersInfo.getRecomendationFlag() == null) {
            usersInfo.setRecomendationFlag(Boolean.FALSE);
        }

        return new UsersInfoResponse(usersInfo.getId(),
                usersInfo.getUsers().getUsername(),
                usersInfo.getName(),
                usersInfo.getSurname(),
                usersInfo.getPatronymic(),
                usersInfo.getRecomendationFlag(),
                usersInfo.getPhoneNumber(),
                usersInfo.getTown(),
                portfolioResponse,
                resumeResponse,
                taskResponses);
    }

    /**
     * Превратить список UsersInfo в список UsersInfoResponse
     * @param usersInfoList список профилей пользователей
     * @return список ответов пользователей
     */
    public List<UsersInfoResponse> mappingUserInfoList(List<UsersInfo> usersInfoList) {
        List<UsersInfoResponse> usersInfoResponses = new ArrayList<>();
        for (UsersInfo info : usersInfoList) {
            usersInfoResponses.add(mappingUserInfo(info));
        }

        return usersInfoResponses;
    }

    /**
     * Превратить Portfolios в PortfolioResponse
     * @param portfolios
     * @return
     */
    public PortfolioResponse mappingPortfolio(Portfolios portfolios) {
        return new PortfolioResponse(portfolios.getId(),
                portfolios.getUserInfo().getUsers().getId(),
                portfolios.getTitle(),
                portfolios.getDescription(),
                portfolios.getFilePath(),
                portfolios.getUrl(),
                portfolios.getUploadDate());
    }

    /**
     * Превратить список Portfolios в список PortfolioResponse
     * @param portfoliosList список Portfolios
     * @return список PortfolioResponse
     */
    public List<PortfolioResponse> mappingPortfolioList(List<Portfolios> portfoliosList) {
        List<PortfolioResponse> portfolioResponses = new ArrayList<>();
        for (Portfolios portfolio : portfoliosList) {
            portfolioResponses.add(mappingPortfolio(portfolio));
        }

        return portfolioResponses;
    }

    /**
     * Превратить Resumes в ResumeResponse
     * @param resumes резюме
     * @return ответ резюме
     */
    public ResumeResponse mappingResume(Resumes resumes) {
        return new ResumeResponse(resumes.getId(),
                resumes.getUserInfo().getUsers().getId(),
                resumes.getUrl(),
                resumes.getFilePath(),
                resumes.getUploadDate());
    }

    /**
     * Превратить список resumesList в список ResumeResponse
     * @param resumesList список Resumes
     * @return список ResumeResponse
     */
    public List<ResumeResponse> mappingResumeList(List<Resumes> resumesList) {
        List<ResumeResponse> resumeResponses = new ArrayList<>();
        for (Resumes resumes : resumesList) {
            resumeResponses.add(mappingResume(resumes));
        }

        return resumeResponses;
    }

    /**
     * Превратить Tasks в TaskResponse
     * @param task задание
     * @return ответ задания
     */
    public TaskResponse mappingTasks(Tasks task) {
        return new TaskResponse(task.getId(),
                task.getUsersInfo().getId(),
                task.getIntershipsInfo().getId(),
                task.getTitle(),
                task.getInfo(),
                task.getDeadline(),
                task.getUrl(),
                task.getFilePath(),
                task.getStatus(),
                task.getGrade(),
                task.getResult());
    }

    /**
     * Превратить список Tasks в список TaskResponse
     * @param tasksList список задач
     * @return список ответа задач
     */
    public List<TaskResponse> mappingTasksList(List<Tasks> tasksList) {
        List<TaskResponse> taskResponses = new ArrayList<>();
        for (Tasks tasks : tasksList) {
            taskResponses.add(mappingTasks(tasks));
        }

        return taskResponses;
    }
}
