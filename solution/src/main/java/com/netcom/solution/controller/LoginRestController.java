package com.netcom.solution.controller;

import com.netcom.solution.domain.Member;
import com.netcom.solution.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class LoginRestController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Member loginRequest) {
        Optional<Member> memberOpt = memberRepository.findByEmail(loginRequest.getEmail());
        if (memberOpt.isPresent()) {
            Member member = memberOpt.get();
            if (passwordEncoder.matches(loginRequest.getPassword(), member.getPassword())) {
                return new ResponseEntity<>("로그인이 성공적으로 완료되었습니다!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("비밀번호가 올바르지 않습니다.", HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>("해당 이메일로 등록된 사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
        }
    }
}