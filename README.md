## 중거 거래 게시판

### ➡️ 개요
    1. 일전 작성했던 secondhand의 DB 관리를 JDBC -> JPA로 교체
    2. 로그인 방법에 카카오 REST API 추가

### ➡️ 기능 설명
    1. 카카오 REST API를 활용한 연동 로그인 기능 구현
        - social token을 DB 내 회원 정보와 대조하여 가입한 적이 없는 유저인 경우 join 페이지로 이동
        - 가입 내역이 있어 socail token이 확인된 경우 로그인 처리
    2. DB 관리를 모두 JPA 교체
        - 주된 교체 내역 : VO(value object), repository, DB 접근 기능이 필요한 service
