package com.example.worktodayproject.web;

import com.example.worktodayproject.dto.UsersInfoDto;
import com.example.worktodayproject.service.UsersInfoService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Контроллер профиля
 */
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/api/v1/private/my-profile")
public class UsersInfoController {

    UsersInfoService usersInfoService;

    /**
     * Установить данные в профиль
     * @param usersInfoDto
     * @param principal
     */
    @PostMapping("/set-data")
    public void setProfile(@RequestBody UsersInfoDto usersInfoDto, Principal principal) {
        String username = principal.getName();
        usersInfoService.updateUsersInfo(usersInfoDto, username);
    }

}
