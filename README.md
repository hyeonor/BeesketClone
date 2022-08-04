# 🥨BeesketStudioClone

<h3>직접 촬영한 사진을 바탕으로 작업하는 디자인 스튜디오 비스켓 스튜디오 클론 코딩입니다.😊</h3>

![image](https://user-images.githubusercontent.com/107676736/182812014-27d51d4c-f8d8-4e59-94f8-7ad04cc182f6.png)
</br></br></br>


# 📆제작 기간 및 팀원 소개👨‍💻👩‍💻
<h3>2022-07-29 ~ 2022-08-04</h3></br>

![image](https://user-images.githubusercontent.com/107676736/182812447-c80ef6a3-a3a4-42ba-b6cf-b5ce9300811e.png)

김하늘 : 장바구니 관련 기능 담당<br>
배소정 : 로그인/회원가입 관련 기능 담당<br>
정현아 : 게시글 관련 기능 담당<br>
</br></br></br>

# 🌎Website
[BeesketStudioClone 사이트](http://clonecoding-beesketstudio.s3-website.ap-northeast-2.amazonaws.com/)
</br></br></br>

# 🎬데모 영상 링크
[데모영상 바로가기](https://www.youtube.com/watch?v=m1xxRSVzEpw)
</br></br></br>

# 📝노션 설계 페이지
[노션 바로가기](https://www.notion.so/SA-1-271ebc4df43e4d01908ca319d0c85adf)
</br></br></br>

# 📋와이어 프레임
![image](https://user-images.githubusercontent.com/107676736/182814728-f59f7131-778d-43b1-8b61-7e0e227d671e.png)
</br></br></br>

# 🛠Back-end 기술 스택 및 개발 환경 🔨
<h3>JAVA<br>
Spring<br>
JWT<br>
MySQL<br>
CORS<br>
AWS EC2 (Ubuntu 18.04LTS)</h3>
</br></br>

# 🔗API 상세

# 💬 Back-end
이번 프로젝트는 주특기 주차를 마치고 처음으로 프론트엔드와 백엔드로 나눠서 진행한 프로젝트 입니다.
우선적으로 주특기 주차에 배운 기본적인 CRUD기능의 구현에 집중하였고, 세부적으로 내실을 다지는데 집중하였습니다.
**❓ Why? JWT + Bearer 인증 유형 **

발급한 JWT 토큰은 OAuth 2.0 인증으로 발급한 액세스 토큰이 아니기 때문에 공식적으로 이 방법은 "비표준" 방식으로 볼 수 있습니다. 그렇지만 토큰을 헤더로 교환 할 목적으로 사용할 인증 유형이 Bearer가 제일 적절하다 생각하여 HTTP 인증 유형중, Bearer 타입을 사용하여 토큰을 전달 하는 방법을 사용하였습니다.

❓ Why? mongoDB

1차 목표를 기본 CRUD 기능 구현과 프론트엔드와 백엔드의 아름다운 협업으로 하였고 추후에 기능을 추가할 계획을 세웠던 저희 프로젝트에서 RDBMS와 다르게 자유로이 데이터 구조를 잡을 수 있다는 특징이 있는 Document 기반 데이터베이스인 mongoDB가 가장 적합하다 판단하였습니다.

❓ Why? dotenv

노출되면 보안에 위협적인 환경변수들을 한곳에 모아 관리했습니다.
