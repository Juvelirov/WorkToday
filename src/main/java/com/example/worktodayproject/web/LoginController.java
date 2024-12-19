package com.example.worktodayproject.web;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    /*
     Если успешно авторизовался, получаем данные и возвращаем homepageRealization
     После успешной аутентификации через ВКонтакте пользователь будет перенаправлен на /login/oauth2/success,
     где будет обработан его вход и он сможет увидеть домашнюю страницу.
     Или здесь нужно возвращать redirectPage (автоматом перенаправляет туда после успешной авторизации),
     посмотри в конфигурации, я так и не понял.
     */
    @GetMapping("/login/oauth2/success")
    public String loginSuccess(Model model, OAuth2AuthenticationToken authentication) {
        model.addAttribute("user", authentication.getPrincipal().getAttributes());
        return "homepageRealization";
    }
}
