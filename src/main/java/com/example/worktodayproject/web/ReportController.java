package com.example.worktodayproject.web;

import com.example.worktodayproject.dto.request.ReportDto;
import com.example.worktodayproject.service.ReportsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/private/student/report")
public class ReportController {

    ReportsService reportsService;

    @PostMapping(value = "/create/{internshipId}", consumes = { "multipart/form-data" })
    public ResponseEntity<?> createReport(@RequestParam(value = "filePath", required = false) MultipartFile filePath,
                                          @PathVariable Long internshipId,
                                          Principal principal) {
        ReportDto reportDto = new ReportDto(filePath);
        reportsService.createReport(principal.getName(), internshipId, reportDto);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete-report/{internshipId}")
    public ResponseEntity<?> deleteReport(Principal principal,
                                          @PathVariable Long internshipId) {
        reportsService.deleteReport(principal.getName(), internshipId);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
