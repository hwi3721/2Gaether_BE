# 투개더 📺

![투개더 리드미](https://user-images.githubusercontent.com/120078825/223928527-0912cecf-bc93-44af-b222-50c530ec0f83.png)

현재 반려동물(강아지)와 함께 살고 있는 20대~40대 남녀가 사용자의 위치 기반을 이용하여 반경 3km 내 반려인&반려동물과 함께 산책을 할 수 있다.

<br>

## 1. 제작 기간 & 팀원 소개


- 2023년 2월 3일 ~ 2023년 3월 8일
    [BE]
  - 고현우 : cicd 연결, nginx 등 서버 설정 및 연결, 소셜로그인, 좋아요와 매칭 기능, 이미지 구현
  - 이상휘 : 이메일 인증, 유저 정보
  - 이성진 : 채팅 기능, 강아지 정보

<br>

## 2. 기술 스택
<img src="https://img.shields.io/badge/JAVA-007396?style=for-the-badge&logo=java&logoColor=white"><img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white"><img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"><img src="https://img.shields.io/badge/LINUX-005496?style=for-the-badge&logo=linux&logoColor=white"><img src="https://img.shields.io/badge/NGINX-3D1332?style=for-the-badge&logo=nginx&logoColor=white"><img src="https://img.shields.io/badge/STOMPJS-lightgrey?style=for-the-badge&logo=stompjs&logoColor=white">

<br>

## 3. 실행화면

![스크린샷(101)](https://user-images.githubusercontent.com/120078825/223934839-5cf9a2a6-ce26-4a2a-aabc-eac4021885ef.png)

![스크린샷(102)](https://user-images.githubusercontent.com/120078825/223934776-e4993c3f-4511-4237-bad9-689385fd1ba0.png)



<br>

## 4. 핵심기능

1. 로그인 기능 구현 ( 기본 회원가입 + 카카오톡 DB 회원가입 )
2. 강아지 추가하기 ( Form Data 방식으로 데이터 값을 백엔드와 교류 + GPS 데이터를 로컬기능을 통해서 위도경도값을 DB로 보내는 방식)
3. 데이터 값을 기반으로 유저 매칭 (유저간의 거리 비교 및 데이터를 가져와서 유저간의 매칭을 가능하게 해주는 기능)
4. 좋아요 기능 구현(받고 보낸 좋아요 데이터를 따로 모으며 프로필 확인과 채팅을 위해 매칭되는 기능 구현)
5. Stompjs를 통해 채팅 기능 구현 ( 채팅기능을 구현하기위해 필요하다 생각한 개념입니다. )

<br>

## 5. 개인 회고

고현우 : 
이상휘 : 
이성진 : 
