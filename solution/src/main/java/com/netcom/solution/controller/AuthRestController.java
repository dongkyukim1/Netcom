package com.netcom.solution.controller;

import com.netcom.solution.domain.Member;
import com.netcom.solution.domain.MemberStatus;
import com.netcom.solution.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    @Autowired
    private MemberService memberService;

    // 회원가입 API
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody Member member) {
        try {
            if (isManagerPosition(member.getPosition())) {
                member.setStatus(MemberStatus.APPROVED);
                memberService.register(member);
                return ResponseEntity.ok("회원가입이 완료되었습니다.");
            } else {
                member.setStatus(MemberStatus.PENDING);
                memberService.register(member);
                return ResponseEntity.ok("회원가입이 신청되었습니다. 관리자의 승인이 필요합니다.");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("회원가입 중 오류가 발생했습니다. 오류: " + e.getMessage());
        }
    }

    private boolean isManagerPosition(String position) {
        return "차장".equals(position) || "부장".equals(position);
    }
}
