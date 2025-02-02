# Netcom
# ERP 인수인계 게시판 시스템

## 🌟 프로젝트 소개
실시간 채팅이 가능한 ERP 인수인계 게시판 시스템입니다. Spring Boot 기반의 백엔드와 웹소켓을 활용한 실시간 통신을 제공합니다.

## 🛠 기술 스택

## 예상 기간
12월 2일 ~ 1월 첫째주

## 과정 
승인 -> 멤버 역할 분배 -> 설계 -> 개발 -> 배포 

### Backend(Java)
- **Framework**: Spring Boot
- **ORM**: JPA
- **Build Tool**: Gradle
- **WebSocket**: 실시간 채팅 구현
- **Restapi : swagger 로 문서화

### Frontend
- HTML5
- JavaScript
- CSS3

### Database
- MySQL
-> member 테이블 
-> board 테이블
-> chat 테이블


### Infrastructure
- **Cloud**: AWS (EC2, RDS)
- **Container**: Docker

### Version Control
- Git
- Git Flow 브랜치 전략

## 🏗 시스템 아키텍처
```
Client Side <--> AWS EC2 (Docker Container)
                    ├── Spring Boot API Server
                    ├── WebSocket Server
                    └── JPA Layer
                           └── AWS RDS (MySQL)
```

## 🌲 Git 브랜치 전략
- `main`: 프로덕션 환경 배포용 브랜치
- `develop`: 개발 환경 브랜치
- `feature/*`: 새로운 기능 개발 브랜치
- `hotfix/*`: 긴급 버그 수정 브랜치

## 📋 주요 기능
1. **게시판 기능**
    -게시판 부서별로 생성 (DX사업부(영업/컨설팅, GC / KT ))(ICT 사업본부)(서버개발부)
   - 인수인계 게시글 작성/수정/삭제
   * 게시글에 사진 동영상등 업로드 후 바로 볼 수 있게.
   - 첨부파일 업로드/다운로드
   - 게시글 검색 및 필터링

2. **실시간 채팅**
   - WebSocket 기반 실시간 채팅
   - 현재 접속중인 인원 파악 가능하게
   - 1:1 채팅 및 그룹 채팅
   - 메세지 알림 도착기능
   - 채팅 내역 저장 및 조회

3. **사용자 관리**
   - 사용자 인증 및 권한 관리
   - 부서별/팀별 사용자 그룹 관리
   - 차장 이상이 해당 사업부 사용자 승인 후 회원가입 승인/삭제
   - 모든 권한 있는 ADMIN 계정 생성 

## 🚀 시작하기

### 필수 요구사항
- JDK 17 이상
- MySQL 8.0 이상
- Docker

### 로컬 개발 환경 설정
```bash
# 프로젝트 클론
git clone [www.github/dongkyukim1/netcome]
cd [project-directory]

# Gradle 빌드
./gradlew clean build

# Docker 이미지 빌드
docker build -t erp-system .

# Docker 컨테이너 실행
docker run -p 8080:8080 erp-system
```
#AWS계정 세팅
-> 차후 NETLIFY인지 AWS 로 할지 결정 후 담당자 배정

### 환경 변수 설정
# application.yml
Teams로 공유 
## 📝 API 문서
- Swagger UI: `http://localhost:8080/swagger-ui.html`

## 🔨 테스트
```bash
# 전체 테스트 실행
./gradlew test

# 단위 테스트만 실행
./gradlew test --tests "*.unit.*"
```

## 🌐 배포
1. `develop` 브랜치에서 개발 완료
2. `main` 브랜치로 PR 생성 및 리뷰
3. CI/CD 파이프라인을 통한 자동 배포
4. AWS EC2 인스턴스에 Docker 컨테이너로 실행

## 📜 라이선스
This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

