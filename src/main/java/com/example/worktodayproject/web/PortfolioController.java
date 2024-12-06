package com.example.worktodayproject.web;

import com.example.worktodayproject.dto.request.PortfolioDto;
import com.example.worktodayproject.dto.response.PortfolioResponse;
import com.example.worktodayproject.service.PortfolioService;
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

/**
 * Контроллер Portfolio
 */
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/api/v1/private/student/portfolio")
public class PortfolioController {

    PortfolioService portfolioService;

    /**
     * Создать портфолио
     * @param portfolioDto дто портфолио
     * @param principal текущий пользователь
     * @return успешный ответ
     */
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createPortfolio(@RequestBody PortfolioDto portfolioDto, Principal principal) {
        portfolioService.createPortfolio(principal.getName(), portfolioDto);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Получить портфолио пользователя по его id
     * @param username имя пользователя
     * @param id идентификатор профиля
     * @return ответ портфолио
     */
    @GetMapping("/{username}/{id}")
    public PortfolioResponse getUserPortfolio(@PathVariable String username, @PathVariable Long id) {
        return portfolioService.getUserPortfolio(username, id);
    }

    /**
     * Получить все портфолио пользователя
     * @param username имя пользователя
     * @return список портфолио
     */
    @GetMapping("/{username}")
    public List<PortfolioResponse> getAllUserPortfolios(@PathVariable String username) {
        return portfolioService.getAllUserPortfolios(username);
    }

    /**
     * Получить все портфолио текущего пользователя
     * @param principal текущий пользователь
     * @return список портфолио
     */
    @GetMapping("/my-portfolio")
    public List<PortfolioResponse> getCurrentUserPortfolios(Principal principal) {
        return portfolioService.getAllUserPortfolios(principal.getName());
    }

    /**
     * Удалить портфолио по его id
     * @param id идентификатор портфолио
     * @param principal текущий пользователь
     * @return успешный ответ
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deletePortfolio(@PathVariable Long id, Principal principal) {
        portfolioService.deletePortfolio(id, principal.getName());

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
