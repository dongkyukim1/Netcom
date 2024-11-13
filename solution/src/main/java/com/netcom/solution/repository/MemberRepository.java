package com.netcom.solution.repository;

import com.netcom.solution.domain.Member;
import com.netcom.solution.domain.MemberStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // 이메일로 회원 찾기 (로그인, 중복 체크에 사용)
    Optional<Member> findByEmail(String email);
    
    // 이메일 존재 여부 확인 (회원가입 시 중복 체크)
    boolean existsByEmail(String email);
    
    // 상태별 회원 목록 조회 (승인 대기 회원 목록 등)
    List<Member> findByStatus(MemberStatus status);
    
    // 부서 및 상태로 회원 조회 (특정 부서의 승인된 회원 등)
    List<Member> findByDepartmentAndStatus(String department, MemberStatus status);
    
    // 직급으로 회원 조회 (관리자 권한 확인 등)
    List<Member> findByPosition(String position);
}