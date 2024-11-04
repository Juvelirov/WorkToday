package com.example.worktodayproject.web;

import com.example.worktodayproject.security.dto.UserDto;
import com.example.worktodayproject.security.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/api/v1/public")
public class PublicController {

    UserService userServiceImpl;

    @PostMapping("/registration")
    public void signUp(@RequestBody UserDto userDto) {
        userServiceImpl.createUser(userDto);
    }
}
