package com.example.worktodayproject.web;

import com.example.worktodayproject.dto.request.UsersInfoDto;
import com.example.worktodayproject.service.UsersInfoService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Контроллер профиля
 */
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/api/v1/private/student/my-profile")
public class UsersInfoController {

    UsersInfoService usersInfoService;

    /**
     * Установить данные в профиль
     * @param usersInfoDto
     * @param principal
     */
    @PostMapping("/set-data")
    public ResponseEntity<String> setProfile(@RequestBody UsersInfoDto usersInfoDto, Principal principal) {
        usersInfoService.updateUsersInfo(usersInfoDto, principal.getName());

        return ResponseEntity.ok("succes");
    }
}
