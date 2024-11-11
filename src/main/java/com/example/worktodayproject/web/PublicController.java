package com.example.worktodayproject.web;

import com.example.worktodayproject.security.dto.request.UserDto;
import com.example.worktodayproject.security.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/api/v1/public")
public class PublicController {

    UserService userServiceImpl;

    @PostMapping("/registration")
    public ResponseEntity<String> signUp(@RequestBody UserDto userDto) {
        userServiceImpl.createUser(userDto);

        return ResponseEntity.ok("succes");
    }
}
