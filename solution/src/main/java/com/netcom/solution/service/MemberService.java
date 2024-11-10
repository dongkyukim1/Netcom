package com.netcom.solution.service;

import com.netcom.solution.domain.Member;
import com.netcom.solution.domain.MemberStatus;
import com.netcom.solution.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Member register(Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        member.setStatus(MemberStatus.PENDING);
        return memberRepository.save(member);
    }

    @Transactional
    public void approveMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));

        // 회원 상태를 APPROVED로 변경합니다.
        member.setStatus(MemberStatus.APPROVED);
        memberRepository.save(member);
    }
}
