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

   @Column(nullable = false, length = 20)  // 이름 20자 제한
   private String name;

   @Column(nullable = false, length = 13)  // 전화번호 13자 (010-1234-5678)
   private String phoneNumber;

   @Column(nullable = false, length = 30)  // 부서명 30자 제한
   private String department;

   @Column(nullable = false, length = 10)  // 직책 10자 제한
   private String position;

   @Column(nullable = false, unique = true, length = 50)  // 이메일 50자 제한
   private String email;

   @Column(nullable = false, length = 100)  // 암호화된 비밀번호 고려 100자
   private String password;

   @Enumerated(EnumType.STRING)
   @Column(length = 10)  // PENDING, APPROVED 등
   private MemberStatus status = MemberStatus.PENDING;

   @Column(nullable = false, length = 20)  // ROLE_USER, ROLE_ADMIN 등
   private String role = "ROLE_USER";

   @Column(updatable = false)
   private LocalDateTime createdAt;
   
   private LocalDateTime approvedAt;
   
   private Long approvedBy;  // 승인자 ID

   @PrePersist
   protected void onCreate() {
       createdAt = LocalDateTime.now();
   }
}