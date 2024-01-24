package com.janikcrew.szkola.controller;

import com.janikcrew.szkola.entity.Wydarzenie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    // netstat -ano
    // taskkill /PID .... /F
    @GetMapping("/loginPage")
    public String showLoginPage() {
        return "login_page";
    }

    @GetMapping("/landingPage")
    public String showLandingPage() {
        return "landing_page";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }

}
