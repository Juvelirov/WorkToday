package com.example.worktodayproject.web;

import com.example.worktodayproject.dto.response.EnrollResponse;
import com.example.worktodayproject.service.EnrollmentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/private/hr")
public class EnrollControllerForHr {

    EnrollmentService enrollmentService;

    @GetMapping("/enrolls")
    public List<EnrollResponse> getUserEnroll(Principal principal) {
        return enrollmentService.getUserEnrolls(principal.getName());
    }
}
