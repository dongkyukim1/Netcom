package com.netcom.solution.config;

import com.netcom.solution.domain.Member;
import com.netcom.solution.domain.MemberStatus;
import com.netcom.solution.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

public class DataInitializer implements CommandLineRunner {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // 마스터 계정이 없을 경우에만 생성
        if (!memberRepository.existsByEmail("netcom@netcom.com")) {
            Member master = new Member();
            master.setName("관리자");
            master.setEmail("netcom@netcom.com");
            master.setPassword(passwordEncoder.encode("netcom!"));
            master.setPhoneNumber("010-0000-0000");
            master.setDepartment("관리부");
            master.setPosition("관리자");
            master.setRole("ROLE_ADMIN");
            master.setStatus(MemberStatus.APPROVED);
            memberRepository.save(master);
        }
    }
}