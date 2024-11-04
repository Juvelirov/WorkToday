package com.example.worktodayproject.web;

import com.example.worktodayproject.database.entity.Users;
import com.example.worktodayproject.security.dto.UserDto;
import com.example.worktodayproject.security.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/api/v1/private/admin")
public class AdminController {

    UserService userService;

    /**
     * DELETE запрос на удаление пользователя по его имени
     * @param login имя пользователя
     * @return статус
     */
    @DeleteMapping("/delete-user")
    public String deleteUser(@RequestParam String login) {
        userService.deleteUser(login);
        return "OK";
    }

    /**
     * GET запрос на просмотр всех пользователей
     * @return список пользователей
     */
    @GetMapping("/all-users")
    public List<Users> getUsers() {
        return userService.getAllUsers();
    }

    /**
     * GET запрос на получение пользователя по его имени
     * @param login имя пользователя
     * @return пользователь
     */
    @GetMapping("/user/{login}")
    public Users getUser(@PathVariable String login) {
        return userService.getUser(login);
    }

    /**
     * PUT запрос на обновление данных пользователя
     * @param userDto дто пользователя
     * @return статус
     */
    @PutMapping("/update-user")
    public String updateUser(UserDto userDto) {
        userService.updateUser(userDto);
        return "OK";
    }
}
