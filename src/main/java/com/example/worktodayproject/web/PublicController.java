package com.example.worktodayproject.web;

import com.example.worktodayproject.dto.response.UsersInfoResponse;
import com.example.worktodayproject.security.dto.request.UserDto;
import com.example.worktodayproject.security.service.UserService;
import com.example.worktodayproject.service.UsersInfoService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/api/v1/public")
public class PublicController {

    UserService userServiceImpl;
    UsersInfoService usersInfoService;

    @GetMapping()
    public List<UsersInfoResponse> getAllUserInfo() {
        return usersInfoService.getAllUsersInfo();
    }

    @PostMapping("/registration")
    public ResponseEntity<Map<String, Object>> signUp(@RequestBody UserDto userDto) {
        userServiceImpl.createUser(userDto);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
