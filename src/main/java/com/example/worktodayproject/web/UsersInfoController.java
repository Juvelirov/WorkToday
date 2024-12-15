package com.example.worktodayproject.web;

import com.example.worktodayproject.dto.request.UsersInfoDto;
import com.example.worktodayproject.dto.response.UsersInfoResponse;
import com.example.worktodayproject.service.UsersInfoService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Контроллер профиля
 */
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/api/v1/private/student/profiles")
@Validated
public class UsersInfoController {

    UsersInfoService usersInfoService;

    /**
     * Установить данные в профиль
     * @param usersInfoDto
     * @param principal
     */
    @PostMapping("/my-profile/set-data")
    public ResponseEntity<Map<String, Object>> setProfile(@Valid @RequestBody UsersInfoDto usersInfoDto,
                                                          Principal principal) {
        usersInfoService.updateUsersInfo(usersInfoDto, principal.getName());

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Получить данные профиля пользователя
     * @param principal текущий пользователь
     * @return ответ профиля пользователя
     */
    @GetMapping("/my-profile")
    public UsersInfoResponse getUserInfo(Principal principal) {
        return usersInfoService.getUserInfo(principal.getName());
    }

    /**
     * Получить данные другого пользователя
     * @param username имя пользователя
     * @return ответ профиля пользователя
     */
    @GetMapping("/{username}")
    public UsersInfoResponse getOtherUserInfo(@PathVariable String username) {
        return usersInfoService.getOtherUserInfo(username);
    }

    /**
     * Получить все профили пользователей
     * @return список профилей пользователей
     */
    @GetMapping()
    public List<UsersInfoResponse> getAllUserInfo() {
        return usersInfoService.getAllUsersInfo();
    }
}