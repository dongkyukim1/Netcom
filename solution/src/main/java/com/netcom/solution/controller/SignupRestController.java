package com.netcom.solution.controller;

import com.netcom.solution.domain.Member;
import com.netcom.solution.domain.MemberStatus;
import com.netcom.solution.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SignupRestController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody Member member) {
        try {
            // 직책에 따라 승인 상태 설정
            if (isManagerPosition(member.getPosition())) {
                member.setStatus(MemberStatus.APPROVED); // ACTIVE를 APPROVED로 수정
                memberService.register(member);
                return new ResponseEntity<>("회원가입이 완료되었습니다.", HttpStatus.OK);
            } else {
                member.setStatus(MemberStatus.PENDING);
                memberService.register(member);
                return new ResponseEntity<>("회원가입이 신청되었습니다. 관리자의 승인이 필요합니다.", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("회원가입 중 오류가 발생했습니다. 오류: " + e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    private boolean isManagerPosition(String position) {
        return "차장".equals(position) || "부장".equals(position);
    }

    @PutMapping("/approve/{memberId}")
    public ResponseEntity<String> approveMember(@PathVariable Long memberId) {
        try {
            memberService.approveMember(memberId);
            return new ResponseEntity<>("회원이 승인되었습니다!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("회원 승인 중 오류가 발생했습니다. 오류: " + e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }
}