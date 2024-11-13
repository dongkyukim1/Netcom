package com.netcom.solution.controller;

import com.netcom.solution.domain.Member;
import com.netcom.solution.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/main")
    public String mainPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Member member = memberService.getMemberByEmail(userDetails.getUsername());
        model.addAttribute("member", member);
        return "main";
    }
}