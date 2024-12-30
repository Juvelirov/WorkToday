package com.example.worktodayproject.web;

import com.example.worktodayproject.dto.request.ResultDto;
import com.example.worktodayproject.dto.response.AnalyticResponse;
import com.example.worktodayproject.service.ResultService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/private/hr/analytics")
@Validated
public class AnalyticsController {

    ResultService resultService;

    @GetMapping
    public List<AnalyticResponse> getAnalytics(Principal principal) {
        return resultService.generateResult(principal.getName());
    }

    @PostMapping("/set-result/{internshipResultId}")
    public ResponseEntity<?> setResult(Principal principal,
                                       @PathVariable Long internshipResultId,
                                       @Valid @RequestBody ResultDto resultDto) {
        resultService.setMarkAndRecommendation(principal.getName(), internshipResultId, resultDto);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
