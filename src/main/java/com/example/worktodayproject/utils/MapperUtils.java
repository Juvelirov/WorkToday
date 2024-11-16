package com.example.worktodayproject.utils;

import com.example.worktodayproject.database.entity.IntershipsInfo;
import com.example.worktodayproject.database.entity.Tags;
import com.example.worktodayproject.database.entity.UsersInfo;
import com.example.worktodayproject.dto.response.IntershipInfoResponse;
import com.example.worktodayproject.dto.response.UsersInfoResponse;
import com.example.worktodayproject.security.dto.response.UserResponse;

import java.util.ArrayList;
import java.util.List;

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
        return new UsersInfoResponse(usersInfo.getId(),
                usersInfo.getName(),
                usersInfo.getSurname(),
                usersInfo.getPatronymic(),
                usersInfo.getRecomendationFlag(),
                usersInfo.getPhoneNumber(),
                usersInfo.getTown());
    }
}
