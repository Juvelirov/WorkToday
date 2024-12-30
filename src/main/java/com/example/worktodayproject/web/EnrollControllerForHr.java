package com.example.worktodayproject.web;

import com.example.worktodayproject.dto.response.EnrollResponse;
import com.example.worktodayproject.service.EnrollmentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/private/hr/enrolls")
public class EnrollControllerForHr {

    EnrollmentService enrollmentService;

    @GetMapping()
    public List<EnrollResponse> getUserEnroll(Principal principal) {
        return enrollmentService.getUserEnrolls(principal.getName());
    }

    @PostMapping("/accept/{username}/{enrollId}")
    public ResponseEntity<?> acceptUserEnroll(Principal principal,
                                              @PathVariable String username,
                                              @PathVariable Long enrollId) {
        enrollmentService.setEnrollAcceptStatus(username, principal.getName(), enrollId);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/reject/{username}/{enrollId}")
    public ResponseEntity<?> rejectUserEnroll(Principal principal,
                                              @PathVariable String username,
                                              @PathVariable Long enrollId) {
        enrollmentService.setEnrollRejectStatus(username, principal.getName(), enrollId);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
