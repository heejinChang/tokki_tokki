package enums;


public enum CommonWord {

  SIGN_UP_MEMBERSHIP("회원가입", 0),
  LOGIN("로그인", 1),
  NAME("이름", 2),
  EMAIL("이메일", 3),
  PASSWORD("비밀번호", 4),
  NICKNAME("닉네임", 5),
  BIRTH("생년월일", 6),
  TODAY_TALK("오늘의 한마디",7),
  PHONE_NUM("휴대전화 번호", 8),
  ADDRESS("집주소",9),
  SITE_ADDRESS("사이트 주소",10),
  MYPROFILE("내 프로필", 11),
  FRIEND("친구", 12),
  API("대기오염 정보",13),
  GOBACK("뒤로가기",14);

  private final String text;

  private final int num;


  CommonWord(String text, int num) {
    this.text = text;
    this.num = num;
  }

  public String getText() {

    return text;
  }

  public int getNum() {

    return num;
  }


}
