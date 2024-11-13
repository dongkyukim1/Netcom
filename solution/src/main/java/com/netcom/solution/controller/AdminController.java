package com.netcom.solution.controller;

import com.netcom.solution.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private MemberService memberService;

    // 회원가입 승인 페이지
    @GetMapping("/pending-members")
    public String pendingMembersPage(Model model) {
        model.addAttribute("pendingMembers", memberService.getPendingMembers());
        return "admin/pending-members";
    }
}
