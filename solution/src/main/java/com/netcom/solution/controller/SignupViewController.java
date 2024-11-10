package com.netcom.solution.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignupViewController {

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @GetMapping("/api/login") // '/login'에서 '/api/login'으로 변경
    public String loginPage() {
        return "login";
    }

    @GetMapping("/api/pending-approval") // '/pending-approval'에서 '/api/pending-approval'으로 변경
    public String pendingApprovalPage() {
        return "pending-approval";
    }
}