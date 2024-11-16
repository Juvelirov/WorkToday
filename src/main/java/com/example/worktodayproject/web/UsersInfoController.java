package com.example.worktodayproject.web;

import com.example.worktodayproject.dto.request.UsersInfoDto;
import com.example.worktodayproject.dto.response.UsersInfoResponse;
import com.example.worktodayproject.service.UsersInfoService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Контроллер профиля
 */
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/api/v1/private/student/profiles")
public class UsersInfoController {

    UsersInfoService usersInfoService;

    /**
     * Установить данные в профиль
     * @param usersInfoDto
     * @param principal
     */
    @PostMapping("/my-profile/set-data")
    public ResponseEntity<String> setProfile(@RequestBody UsersInfoDto usersInfoDto, Principal principal) {
        usersInfoService.updateUsersInfo(usersInfoDto, principal.getName());

        return ResponseEntity.ok("succes");
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
