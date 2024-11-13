package com.netcom.solution.controller;

import com.netcom.solution.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@Tag(name = "Admin API", description = "관리자 기능 API - 회원 승인 및 승인 대기 목록 조회")
public class AdminRestController {

    @Autowired
    private MemberService memberService;

    /**
     * 회원 승인 API
     * 지정된 회원 ID의 회원을 승인합니다.
     *
     * @param memberId 승인할 회원의 ID
     * @return 승인 성공 메시지 또는 오류 메시지
     */
    @Operation(summary = "회원 승인", description = "지정된 회원 ID의 회원을 승인합니다.")
    @PostMapping("/approve/{memberId}")
    public ResponseEntity<String> approveMember(@PathVariable Long memberId) {
        try {
            memberService.approveMember(memberId);
            return ResponseEntity.ok("회원이 승인되었습니다!");
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("회원 승인 중 오류가 발생했습니다. 오류: " + e.getMessage());
        }
    }

    /**
     * 승인 대기 회원 목록 조회 API
     * 승인 대기 상태의 모든 회원 목록을 반환합니다.
     *
     * @return 승인 대기 회원 목록 또는 오류 메시지
     */
    @Operation(summary = "승인 대기 회원 목록 조회", description = "승인 대기 상태의 모든 회원 목록을 조회합니다.")
    @GetMapping("/pending-members")
    public ResponseEntity<?> getPendingMembers() {
        try {
            return ResponseEntity.ok(memberService.getPendingMembers());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("승인 대기 회원 조회 중 오류가 발생했습니다.");
        }
    }
}
