package com.netcom.solution.controller;

import com.netcom.solution.domain.Member;
import com.netcom.solution.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class ApprovalRestController {

    @Autowired
    private MemberService memberService;

    @PutMapping("/approve/{memberId}")
    public ResponseEntity<String> approveMember(@PathVariable Long memberId) {
        try {
            memberService.approveMember(memberId);
            return new ResponseEntity<>("회원이 승인되었습니다!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("회원 승인 중 오류가 발생했습니다. 오류: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}