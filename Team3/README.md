학원관리 시스템
===
Spring Team Project 

🖥️프로젝트 소개
===
- 직관적인 UI 디자인으로 조작이 간단하고 행정·원생 관리를 통합한 프로젝트임
- 운영관리, 학습관리, 회원관리 및 기타기능으로 분류하여 통합된 시스템으로 학원을 관리하고자 함

⏲️개발 기간
===
2024-02-23 ~ 2024-04-30

🧰개발 환경
===
<div align=center>
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=OpenJDK&logoColor=white">
  <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white">
  <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
  <img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=Spring Security&logoColor=white">
  <img src="https://img.shields.io/badge/mariaDB-003545?style=for-the-badge&logo=mariadb&logoColor=white">
  <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=GitHub&logoColor=white">
  <img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=HTML5&logoColor=white">
  <img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=CSS3&logoColor=white">
  <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=white">
  <img src="https://img.shields.io/badge/Chart.js-FF6384?style=for-the-badge&logo=chartdotjs&logoColor=white">
  <img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=Thymeleaf&logoColor=white">
  <img src="https://img.shields.io/badge/tippy.js-FF6666?style=for-the-badge&logo=''&logoColor=white">
</div>

👨‍👩‍👧‍👧팀 구성
===
|서지우|이송민|👑정민재|홍은지|
|:---:|:---:|:---:|:---:|
|<a href="https://github.com/JENGMINJAE"><img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=GitHub&logoColor=white"></a>|<a href="https://github.com/JENGMINJAE"><img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=GitHub&logoColor=white"></a>|<a href="https://github.com/JENGMINJAE"><img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=GitHub&logoColor=white"></a>|<a href="https://github.com/JENGMINJAE"><img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=GitHub&logoColor=white"></a>|


🧮 구현 기능
===
+ 학습관리
  1. 성적 관리
    - 시험 등록 및 시험 개시일 안내
    - 성적 (단수/복수) 등록 및 성적 조회
    - 성적 이의 신청
  
  2. 학생 관리
    - 출결 관리 및 출결 현황 안내
    - 상담일정 등록 및 안내(Full Callender)
    - 상담 내용 등록
  
  3. 학급 관리
    - 학급 생성 및 수정
    - 과제 등록 및 과제 기간 안내
    - 학급별 강의 진행률 (데이터 시각화)
    - 강사/학생 별 수강(강의) 과목 조회 (관리자 모드)

+ 회원 관리
  - 시큐리티 활용 회원가입 (ID, PW, 생일, 전화번호 등 유효성 검사)
  - 로그인
  - 마이페이지 조회 (학생 학습 정보 조회)
  - 내 정보 조회 (회원 기본 정보 조회)
  - ID/PW 찾기 (시큐리티를 활용한 임시 비밀번호 e-mail 발송 서비스)
  - 인적사항 조회(관리자 모드)
  - 회원 권한 변경 (학생/강사/원장 중 택1)

+ 운영 관리
  - 매출 관리 (매출 데이터 시각화)
  - 직원 관리 (근태 상태 조회 및 변경)
  - 강의별 결제 (카카오페이)

+ 기타 기능
  1. 게시판 관리
    - 게시판 관리
    - 게시판 글 등록, 수정, 삭제
    - 첨부파일 등록, 수정, 삭제
    - 댓글 등록, 수정, 삭제
    - 게시글 이전글, 다음글 조회
