# tokki_tokki
# tokki_tokki
2022-2 네트워크 텀프로젝트 
유투브 영상 : 네트워크 Team D - YouTube
유투브 Demo video : https://www.youtube.com/watch?v=jN5mXV1q7sc&feature=youtu.be


큰 기능 
1. 회원가입
- 회원 가입에 들어가는 정보들: 이름, 이메일 , 비번, 닉네임, 생년월일, 오늘의 한마디, 휴대전화 번호, 집주소(선택), 사이트 주소(선택)

- 프로토콜: userDAO -> controller

return 값

1   가입 정보 부족 에러->에러메시지

2   이메일 중복 에러->에러메시지

3   문제 없음->성공 메시지->메인 패널로 이동

2. 로그인
- 프로토콜 : userDAO -> controller

return

1   로그인 정보 부족 에러->에러메시지

2   비밀번호 불일치->에러메시지

3   정보가 존재하지 않음->에러메시지

4   문제 없음->성공메시지->인덱스 패널로 이동

3. 인덱스 패널


①   정보 수정

- 정보 수정에 들어가는 정보들: 비번, 닉네임, 생년월일, 오늘의 한마디, 휴대전화 번호, 집주소(선택), 사이트 주소(선택)

- 프로토콜: userDAO -> controller

0   수정 정보 부족 에러->에러메시지

1   문제 없음->성공메시지->인덱스 패널로 이동

   - 회원 탈퇴 -> 탈퇴 후 로그인 패널로 이동

②   친구 찾기 -> 친구 추가

- 친구 검색

검색 성공 :  검색한 글자를 포함하는 유저 리스트 출력 - > 추가하고 싶은 유저 클릭 -> 추가 하시겠습니까? 메시지 -> 추가 버튼 누르면 프렌드 테이블에 추가됨 -> 프랜드 리스트에 변화된 정보 바로 없데이트 됨 

검색 실패 :  can not found! 메시지가 클라이언트 GUI에 출력


③   로그아웃 -> 바로 로그아웃 -> userDAO에 접근해서 상태를 false로 바꿈

④   새로 고침 -> 친구 상태(접속, 비접속, 한줄 메시지), 공공데이터 정보 업데이트 됨

⑤   친구리스트 -> 친구 상태(접속, 비접속, 한줄 메시지)알려줌, 
             상태 버튼 클릭 시 팝업 메뉴 나옴

- 팝업 메뉴

대화 신청

오늘의 한마디

접속 상태
   
   - 대화 신청
   
접속중 :  해당 유저에게 대화 신청 메시지 -> 수락시 바로 대화 창이 생김

비접속 상태 :  접속중이 아니라는 에러 메시지를 띄움


   - 채팅창 기능
   
출력 :  시간, 보내는 사람, 받는 사람, 보내는 메시지 출력

파일 전송 :  파일 이모티콘을 누름 -> 파일 선택 창 나옴 -> 파일 선택 하여 전송 -> 상대방에게 파일을 수락하겠냔 메시지 뜸 -> 수락 시 상대방이 파일 수신 

나가기  : +버튼 누름 -> 채팅을 그만 두겠냐는 창 나옴 -> ok를 누르면 채팅 종료

   
⑥   공공데이터
https://www.data.go.kr/   
한국환경공단_에어코리아_대기오염정보의 시도별 실시간 측정정보 조회 데이터 사용
https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15073861
