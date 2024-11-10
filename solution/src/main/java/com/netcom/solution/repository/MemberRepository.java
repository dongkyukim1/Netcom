package com.netcom.solution.repository;

import com.netcom.solution.domain.Member;
import com.netcom.solution.domain.MemberStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email);

    Optional<Member> findByEmail(String email);

    // 승인 대기 중인 회원 목록 조회
    List<Member> findByStatus(MemberStatus status);

    // 부서별 회원 조회
    List<Member> findByDepartment(String department);
}