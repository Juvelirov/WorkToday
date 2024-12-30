package com.example.worktodayproject.utils;

import com.example.worktodayproject.database.entity.*;
import com.example.worktodayproject.dto.request.ReportDto;
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

        UserResponse userResponse = new UserResponse(intershipsInfo.getUser().getId(),
                intershipsInfo.getUser().getFio(),
                intershipsInfo.getUser().getEmail(),
                intershipsInfo.getUser().getUserInfo().getUserPhoto());

        for (Tags tags : intershipsInfo.getTags()) {
            tagsList.add(tags.getName());
        }

        return new IntershipInfoResponse(intershipsInfo.getId(),
                intershipsInfo.getTitle(),
                intershipsInfo.getCompany(),
                intershipsInfo.getDuties(),
                intershipsInfo.getRequirements(),
                intershipsInfo.getTask(),
                intershipsInfo.getTown(),
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
        Optional<List<IntershipInfoResponse>> intershipsInfosResponse = Optional.ofNullable(usersInfo.getEnrollments())
                .map(enrollments -> enrollments.stream()
                        .map(this::mappingEnrollToInternship)
                        .collect(Collectors.toList()));
        Optional<List<ReportResponse>> reportResponses = Optional.ofNullable(usersInfo.getReports())
                .map(reports -> reports.stream().map(this::mappingReport).collect(Collectors.toList()));
        if (usersInfo.getRecomendationFlag() == null) {
            usersInfo.setRecomendationFlag(Boolean.FALSE);
        }

        String fio = usersInfo.getName() + " " + usersInfo.getSurname() + " " + usersInfo.getPatronymic();

        return new UsersInfoResponse(usersInfo.getId(),
                usersInfo.getUsers().getUsername(),
                fio,
                usersInfo.getRecomendationFlag(),
                usersInfo.getPhoneNumber(),
                usersInfo.getTown(),
                usersInfo.getUserPhoto(),
                portfolioResponse,
                resumeResponse,
                intershipsInfosResponse,
                reportResponses);
    }

    private IntershipInfoResponse mappingEnrollToInternship(Enrollment enrollment) {
        if (enrollment == null || enrollment.getIntershipsInfo() == null) {
            return null;
        }
        IntershipsInfo intershipsInfo = enrollment.getIntershipsInfo();
        return mappingIntership(intershipsInfo);
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

    /**
     * Превратить Reports в ReportResponse
     * @param reports отчет
     * @return ответ отчета
     */
    public ReportResponse mappingReport(Reports reports) {
        return new ReportResponse(reports.getId(),
                reports.getUserInfo().getId(),
                reports.getIntershipsInfo().getId(),
                reports.getFilePath());
    }

    /**
     * Превратить InternshipsResult в ResultResponse
     * @param internshipsResult результат стажировки
     * @return ответ результата
     */
    public AnalyticResponse mappingInternshipResult(InternshipsResult internshipsResult) {
        UsersInfoResponse usersInfoResponse = mappingUserInfo(internshipsResult.getUserInfo());

        return new AnalyticResponse(internshipsResult.getId(),
                internshipsResult.getFio(),
                internshipsResult.getStatus(),
                internshipsResult.getMark(),
                internshipsResult.getRecomendation(),
                usersInfoResponse);
    }

    public List<AnalyticResponse> mappingIternshipResultList(List<InternshipsResult> internshipsResults) {
        List<AnalyticResponse> analyticResponses = new ArrayList<>();
        for (InternshipsResult internshipsResult : internshipsResults) {
            analyticResponses.add(mappingInternshipResult(internshipsResult));
        }
        return analyticResponses;
    }

    public List<UserResponse> mappingUsersList(List<Users> users) {
        List<UserResponse> userResponses = new ArrayList<>();
        for (Users user : users) {
            userResponses.add(mappingUsers(user));
        }
        return userResponses;
    }

    public UserResponse mappingUsers(Users users) {
        return new UserResponse(users.getId(),
                users.getFio(),
                users.getEmail(),
                users.getUserInfo().getUserPhoto());
    }

    public EnrollResponse mappingEnroll(Enrollment enrollment) {
        UsersInfoResponse usersInfoResponse = mappingUserInfo(enrollment.getUsers().getUserInfo());

        return new EnrollResponse(enrollment.getId(),
                usersInfoResponse,
                enrollment.getStatus());
    }

    public List<EnrollResponse> mappingEnrollList(List<Enrollment> enrollments) {
        List<EnrollResponse> enrollResponses = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            enrollResponses.add(mappingEnroll(enrollment));
        }

        return enrollResponses;
    }
}
