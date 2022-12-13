package server.userdb;


public class User {

  private String uname;

  private String uemail;

  private String password;

  private String nickname;
  private String birth;
  private String today_talk;
  private String phone_num;
  private String address;
  private String site_address;


  public User(String uname, String uemail, String password, String nickname,
              String birth, String today_talk, String phone_num, String address, String site_address) {

    this.uname = uname;
    this.uemail = uemail;
    this.password = password;
    this.nickname = nickname;
    this.birth = birth;
    this.today_talk = today_talk;
    this.phone_num = phone_num;
    this.address = address;
    this.site_address = site_address;
  }
  public User(String password, String nickname,
              String birth, String today_talk, String phone_num, String address, String site_address) {
    this.password = password;
    this.nickname = nickname;
    this.birth = birth;
    this.today_talk = today_talk;
    this.phone_num = phone_num;
    this.address = address;
    this.site_address = site_address;
  }

  public String getUname() {

    return uname;
  }

  public void setUname(String uname) {

    this.uname = uname;
  }

  public String getUemail() {

    return uemail;
  }

  public void setUemail(String uemail) {

    this.uemail = uemail;
  }

  public String getPassword() {

    return password;
  }

  public void setPassword(String password) {

    this.password = password;
  }

  public String getNickname() {

    return nickname;
  }

  public void setNickname(String nickname) {

    this.nickname = nickname;
  }

  public String getBirth() {

    return birth;
  }

  public void setBirth(String birth) {

    this.birth = birth;
  }
  public String getToday_talk() {

    return today_talk;
  }

  public void setToday_talk(String today_talk) {

    this.today_talk = today_talk;
  }

  public String getPhone_num() {

    return phone_num;
  }

  public void setPhone_num(String phone_num) {

    this.phone_num = phone_num;
  }

  public String getAddress() {

    return address;
  }

  public void setAddress(String address) {

    this.address = address;
  }
  public String getSite_address() {

    return site_address;
  }

  public void setSite_address(String site_address) {

    this.site_address = site_address;
  }


}