package com.netcom.solution.service;

import com.netcom.solution.domain.Member;
import com.netcom.solution.domain.MemberStatus;
import com.netcom.solution.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class MemberService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 회원가입 메서드
    public Member register(Member member) {
        if (isEmailExist(member.getEmail())) {
            throw new RuntimeException("이미 사용중인 이메일입니다.");
        }
        member.setPassword(passwordEncoder.encode(member.getPassword())); // 비밀번호 암호화
        return memberRepository.save(member);
    }

    // 스프링 시큐리티 UserDetailsService 구현 (로그인 처리)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + email));

        // 승인된 회원만 접근 허용
        if (!MemberStatus.APPROVED.equals(member.getStatus())) {
            throw new UsernameNotFoundException("승인되지 않은 회원입니다.");
        }

        return new org.springframework.security.core.userdetails.User(
                member.getEmail(),
                member.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }

    // 이메일 중복 확인
    public boolean isEmailExist(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }

    // 승인 대기 상태의 회원 목록을 조회
    @Transactional(readOnly = true)
    public List<Member> getPendingMembers() {
        return memberRepository.findByStatus(MemberStatus.PENDING);
    }

    // 회원 승인 처리
    @Transactional
    public void approveMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));

        if (MemberStatus.PENDING.equals(member.getStatus())) {
            member.setStatus(MemberStatus.APPROVED);
            member.setApprovedAt(LocalDateTime.now());
            memberRepository.save(member);
        } else {
            throw new RuntimeException("승인 대기 상태의 회원만 승인할 수 있습니다.");
        }
    }

    // 특정 이메일로 회원 조회
    @Transactional(readOnly = true)
    public Member getMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));
    }

    // 회원 상태를 확인
    @Transactional(readOnly = true)
    public MemberStatus getMemberStatus(String email) {
        return memberRepository.findByEmail(email)
                .map(Member::getStatus)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));
    }

    // 관리자 권한 확인
    @Transactional(readOnly = true)
    public boolean isManager(Member member) {
        return "차장".equals(member.getPosition()) || "부장".equals(member.getPosition());
    }

    // 승인 처리 (승인자 정보 포함)
    @Transactional
    public void approveMember(Long memberId, Long approverId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));

        if (MemberStatus.PENDING.equals(member.getStatus())) {
            member.setStatus(MemberStatus.APPROVED);
            member.setApprovedAt(LocalDateTime.now());
            member.setApprovedBy(approverId);
            memberRepository.save(member);
        } else {
            throw new RuntimeException("승인 대기 상태의 회원만 승인할 수 있습니다.");
        }
    }
}
