package com.example.worktodayproject.service;

import com.example.worktodayproject.database.entity.*;
import com.example.worktodayproject.database.enums.EnrollStatus;
import com.example.worktodayproject.database.enums.ResultStatus;
import com.example.worktodayproject.database.repository.*;
import com.example.worktodayproject.dto.request.ResultDto;
import com.example.worktodayproject.dto.request.ResultForAllDto;
import com.example.worktodayproject.dto.response.AnalyticResponse;
import com.example.worktodayproject.exception.custom.AuthorizedUserException;
import com.example.worktodayproject.utils.MapperUtils;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@Slf4j
public class ResultService {

    MapperUtils mapperUtils = new MapperUtils();

    InternshipResultRepository internshipResultRepository;
    UsersRepository usersRepository;
    UsersInfoRepository usersInfoRepository;
    IntershipInfoRepository intershipInfoRepository;
    EnrollmentRepository enrollmentRepository;

    public List<AnalyticResponse> generateResult(String hrUsername) {
        Users hrUser = usersRepository.findByLogin(hrUsername);
        if (hrUser == null) {
            throw new AuthorizedUserException("HR пользователь не найден");
        }

        List<IntershipsInfo> hrCreatedInterships = intershipInfoRepository.findByUser(hrUser);

        List<Enrollment> acceptedEnrollments = hrCreatedInterships.stream()
                .flatMap(intershipsInfo -> intershipsInfo.getEnrollments().stream())
                .filter(enrollment -> enrollment.getStatus().equals(EnrollStatus.ACCEPTED))
                .toList();

        Set<UsersInfo> uniqueUsersInfos = acceptedEnrollments.stream()
                .map(enrollment -> usersInfoRepository.findByUsers(enrollment.getUsers()))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        List<InternshipsResult> internshipsResults = new ArrayList<>();

        for(UsersInfo usersInfo : uniqueUsersInfos) {
            Optional<InternshipsResult> existingResultOptional = internshipResultRepository.findFirstByUserInfo(usersInfo);
            InternshipsResult internshipsResult;

            if(existingResultOptional.isPresent()) {
                internshipsResult = existingResultOptional.get();
                if(internshipsResult.getStatus().equals(ResultStatus.IN_PROGRESS)) {
                    if (usersInfo != null && usersInfo.getReports() != null && !usersInfo.getReports().isEmpty()) {
                        internshipsResult.setStatus(ResultStatus.REVIEW);
                        internshipResultRepository.save(internshipsResult);
                    }
                }
            } else {
                internshipsResult = new InternshipsResult();
                Users users = usersInfo.getUsers();
                if(users != null) {
                    internshipsResult.setFio(users.getFio());
                }
                if (usersInfo != null && usersInfo.getReports() != null && !usersInfo.getReports().isEmpty()) {
                    internshipsResult.setStatus(ResultStatus.REVIEW);
                } else {
                    internshipsResult.setStatus(ResultStatus.IN_PROGRESS);
                }
                internshipsResult.setMark(0);
                internshipsResult.setRecomendation(Boolean.FALSE);
                internshipsResult.setUserInfo(usersInfo);
                internshipsResult.setFinalDate(LocalDateTime.now());
                internshipResultRepository.save(internshipsResult);
            }
            internshipsResults.add(internshipsResult);
        }
        return mapperUtils.mappingIternshipResultList(internshipsResults);
    }

    public void setMarkAndRecommendation(String hrUsername, Long internshipResultId, ResultDto resultDto) {
        Users hrUser = usersRepository.findByLogin(hrUsername);
        if (hrUser == null) {
            throw new AuthorizedUserException("HR пользователь не найден");
        }
        InternshipsResult internshipsResult = internshipResultRepository.findById(internshipResultId)
                .orElseThrow(() -> new IllegalArgumentException("Запись с id: " + internshipResultId + " не найдена"));

        if (!isHrCreatorOfInternshipResult(hrUser, internshipsResult)) {
            throw new IllegalArgumentException("HR пользователь не является владельцем стажировки");
        }
        if (internshipsResult.getStatus().equals(ResultStatus.IN_PROGRESS)
                || internshipsResult.getStatus().equals(ResultStatus.REVIEW)
                || internshipsResult.getStatus().equals(ResultStatus.REWORK)) {
            internshipsResult.setMark(resultDto.mark());
            internshipsResult.setRecomendation(resultDto.recommendation());
            internshipsResult.setStatus(resultDto.status());
            internshipResultRepository.save(internshipsResult);
        }
    }

    public void setMarkAndRecommendationAll(String hrUsername, List<ResultForAllDto> resultDto) {
        Users hrUser = usersRepository.findByLogin(hrUsername);
        if (hrUser == null) {
            throw new AuthorizedUserException("HR пользователь не найден");
        }
        for (ResultForAllDto resultDto1 : resultDto) {
            InternshipsResult internshipsResult = internshipResultRepository.findById(resultDto1.internshipResultId())
                    .orElseThrow(() -> new IllegalArgumentException("Запись с id: " + resultDto1.internshipResultId() + " не найдена"));

            if (!isHrCreatorOfInternshipResult(hrUser, internshipsResult)) {
                throw new IllegalArgumentException("HR пользователь не является владельцем стажировки");
            }

            if (internshipsResult.getStatus().equals(ResultStatus.IN_PROGRESS)
                    || internshipsResult.getStatus().equals(ResultStatus.REVIEW)
                    || internshipsResult.getStatus().equals(ResultStatus.REWORK)) {
                internshipsResult.setMark(resultDto1.mark());
                internshipsResult.setRecomendation(resultDto1.recommendation());
                internshipsResult.setStatus(resultDto1.status());
                internshipResultRepository.save(internshipsResult);
            }
        }
    }

    private boolean isHrCreatorOfInternshipResult(Users hrUser, InternshipsResult internshipsResult) {
        Enrollment enrollment = enrollmentRepository.findAll().stream()
                .filter(x -> x.getUsers().equals(internshipsResult.getUserInfo().getUsers()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Не найдена запись для данного пользователя"));
        return  enrollment.getIntershipsInfo().getUser().equals(hrUser);
    }
}
