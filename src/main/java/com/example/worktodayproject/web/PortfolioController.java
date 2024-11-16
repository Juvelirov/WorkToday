package com.example.worktodayproject.web;

import com.example.worktodayproject.dto.request.PortfolioDto;
import com.example.worktodayproject.dto.response.PortfolioResponse;
import com.example.worktodayproject.service.PortfolioService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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
    public ResponseEntity<String> createPortfolio(@RequestBody PortfolioDto portfolioDto, Principal principal) {
        portfolioService.createPortfolio(principal.getName(), portfolioDto);
        return ResponseEntity.ok("success");
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
    public ResponseEntity<String> deletePortfolio(@PathVariable Long id, Principal principal) {
        portfolioService.deletePortfolio(id, principal.getName());
        return ResponseEntity.ok("success");
    }
}
