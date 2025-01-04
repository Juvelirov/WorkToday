package com.example.worktodayproject.web;

import com.dropbox.core.DbxException;
import com.example.worktodayproject.dto.request.UsersInfoDto;
import com.example.worktodayproject.dto.response.UsersInfoResponse;
import com.example.worktodayproject.security.service.UserService;
import com.example.worktodayproject.service.UsersInfoService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    UserService userService;

    /**
     * Установить данные в профиль
     * @param principal
     */
    @PostMapping(value = "/my-profile/set-data", consumes = { "multipart/form-data" })
    public ResponseEntity<?> setProfile(@RequestParam(value = "name", required = false) String name,
                                        @RequestParam(value = "surname", required = false) String surname,
                                        @RequestParam(value = "patronymic", required = false) String patronymic,
                                        @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                                        @RequestParam(value = "town", required = false) String town,
                                        @RequestParam(value = "avatar", required = false) MultipartFile avatar,
                                                          Principal principal) throws IOException, DbxException {
        UsersInfoDto usersInfoDto = new UsersInfoDto(name, surname, patronymic, phoneNumber, town, avatar);

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

    @DeleteMapping("/delete-account")
    public ResponseEntity<?> deleteAccount(Principal principal) {
        userService.deleteUser(principal.getName());

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        return new ResponseEntity<>(response, HttpStatus.OK);
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
