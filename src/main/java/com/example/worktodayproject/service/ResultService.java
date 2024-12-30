package com.example.worktodayproject.service;

import com.example.worktodayproject.database.entity.*;
import com.example.worktodayproject.database.enums.EnrollStatus;
import com.example.worktodayproject.database.enums.ResultStatus;
import com.example.worktodayproject.database.repository.*;
import com.example.worktodayproject.dto.request.ResultDto;
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
import java.util.ArrayList;
import java.util.List;
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

        List<Enrollment> allEnrollments = hrCreatedInterships.stream()
                .flatMap(intershipsInfo -> intershipsInfo.getEnrollments().stream())
                .filter(enrollment -> enrollment.getStatus().equals(EnrollStatus.ACCEPTED))
                .collect(Collectors.toList());

        List<InternshipsResult> internshipsResults = new ArrayList<>();
        List<Enrollment> enrollments = new ArrayList<>();

        for(Enrollment enrollment : allEnrollments) {
            if (!enrollment.getStatus().equals(EnrollStatus.PENDING)
                    && !enrollment.getStatus().equals(EnrollStatus.REJECTED)) {
                enrollments.add(enrollment);
            }

            for (Enrollment enrollment1 : enrollments) {
                UsersInfo usersInfo = usersInfoRepository.findByUsers(enrollment1.getUsers());
                InternshipsResult existingResult = internshipResultRepository.findByUserInfo(usersInfo);
                if(existingResult == null) {
                    InternshipsResult internshipsResult = new InternshipsResult();
                    internshipsResult.setFio(enrollment1.getUsers().getFio());
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
                    internshipsResults.add(internshipsResult);
                }  else {
                    if(existingResult.getStatus().equals(ResultStatus.IN_PROGRESS)) {
                        if(usersInfo != null && usersInfo.getReports() != null && !usersInfo.getReports().isEmpty()) {
                            existingResult.setStatus(ResultStatus.REVIEW);
                            internshipResultRepository.save(existingResult);
                        }
                    }
                    internshipsResults.add(existingResult);
                }
            }
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

    private boolean isHrCreatorOfInternshipResult(Users hrUser, InternshipsResult internshipsResult) {
        Enrollment enrollment = enrollmentRepository.findAll().stream()
                .filter(x -> x.getUsers().equals(internshipsResult.getUserInfo().getUsers()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Не найдена запись для данного пользователя"));
        return  enrollment.getIntershipsInfo().getUser().equals(hrUser);
    }
}
