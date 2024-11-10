package com.netcom.solution.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private String position; // 직책

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private MemberStatus status = MemberStatus.PENDING; // 가입 승인 상태

    @Column(nullable = false)
    private String role = "ROLE_USER";

    private LocalDateTime createdAt;
    private LocalDateTime approvedAt;
    private Long approvedBy; // 승인자 ID

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}