package com.example.worktodayproject.web;

import com.example.worktodayproject.dto.request.JwtRequest;
import com.example.worktodayproject.dto.response.JwtResponse;
import com.example.worktodayproject.dto.response.UsersInfoResponse;
import com.example.worktodayproject.security.dto.request.UserDto;
import com.example.worktodayproject.security.service.UserDetailServiceImpl;
import com.example.worktodayproject.security.service.UserService;
import com.example.worktodayproject.service.UsersInfoService;
import com.example.worktodayproject.utils.JwtTokensUtils;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/api/v1/public")
@Validated
public class PublicController {

    UserService userService;
    UserDetailServiceImpl userDetailService;
    UsersInfoService usersInfoService;
    JwtTokensUtils jwtTokensUtils;
    AuthenticationManager authenticationManager;

    @GetMapping()
    public List<UsersInfoResponse> getAllUserInfo() {
        return usersInfoService.getAllUsersInfo();
    }

    @PostMapping("/registration")
    public ResponseEntity<?> signUp(@Valid @RequestBody UserDto userDto) {
        userService.createUser(userDto);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthToken(@Valid @RequestBody JwtRequest jwtRequest) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Incorrect login or password");

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.email(),
                    jwtRequest.password()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = userDetailService.loadUserByUsername(jwtRequest.email());
        String token = jwtTokensUtils.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }
}
