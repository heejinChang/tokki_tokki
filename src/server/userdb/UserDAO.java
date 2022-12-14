package server.userdb;

import controller.Controller;

import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JTextField;

public class UserDAO {

  private Connection conn;

  private PreparedStatement pstmt;

  public String username = null;
  String my_email;
  String friend_email;

  String url = "jdbc:mysql://127.0.0.1/sbn?serverTimezone=UTC&&useSSL=false&user=root&password= 1234";

  public void connect() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection(this.url);
      //this.pstmt = this.conn.createStatement();
      System.out.println("[Server] MySQL 서버 연동 성공");


    } catch (SQLException e) {
      System.out.println("[Server] 1MySQL 서버 연동 실패> ");
    } catch (ClassNotFoundException e) {
      System.out.println("[Server] 2MySQL 서버 연동 실패> ");
    }
  }


  public void disconnect() {

    try {
      pstmt.close();
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public int insertDB(User user) {

    String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?,?,?)";

    boolean isInsert = false;

    if (user.getUemail().length() == 0 || user.getPassword().length() == 0 || user.getPhone_num().length() == 0
            || user.getBirth().length() == 0 || user.getNickname().length() == 0 || user.getToday_talk().length() == 0) {

      System.out.println("회원 가입 정보 부족");

      //disconnect();

      return 1;
    }
    else{
      if(this.duplicate(user.getUemail()) == false){
        System.out.println("이메일 중복");

        //disconnect();

        return 2;
      }
      else {
        try {
          connect();

          SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          Date now = new Date();
          String nowTime1 = sdf1.format(now);

          pstmt = conn.prepareStatement(sql);
          pstmt.setString(1, user.getUemail()); //userid
          pstmt.setString(2, user.getUname());
          pstmt.setString(3, user.getUemail());
          pstmt.setString(4, user.getPassword());
          pstmt.setString(5, nowTime1.toString());
          pstmt.setString(6, user.getNickname());
          pstmt.setString(7, user.getBirth());
          pstmt.setString(8, user.getToday_talk());
          pstmt.setString(9, user.getPhone_num());
          pstmt.setInt(10,0);
          pstmt.setString(11, user.getAddress());
          pstmt.setString(12, user.getSite_address());
          pstmt.executeUpdate();

          System.out.println("회원가입 완료됨");

          return 3;

        } catch (SQLException e) {
          isInsert = false;
        }
      }

      disconnect();

      return 0;

    }
  }

  public String findUser(ArrayList<TextField> userInfos) {

    connect();
    String sql = "select user_name from user where user_email=? and user_password=?";
    String uemail = userInfos.get(0).getText();
    String password = userInfos.get(1).getText();
    System.out.println("로그인 하려는 이메일 = " + uemail + " 비밀번호 = " + password);

    String uname = null;
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, uemail);
      pstmt.setString(2, password);
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        uname = rs.getString("user_name");
      }

      username = uname;

    } catch (SQLException e) {
      e.printStackTrace();
    }

    disconnect();

    return username;
  }

  public ArrayList<String> friendList() {

    String uemail = findUserEmail();
    connect();
    ArrayList<String> friends = new ArrayList<String>();
    String sql =
            "select u.user_name from user u, friend f where u.user_email = f.friend_friendEmail and f.friend_myEmail = ?";
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, uemail);
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        friends.add(rs.getString("user_name"));
      }
    } catch (SQLException e) {
    }
    disconnect();
    System.out.println("size: " + friends.size());
    return friends;
  }



  private String findUserEmail() {

    connect();
    String sql = "select user_email from user where user_name=?";
    String uemail = null;
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, username);
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        uemail = rs.getString("user_email");
      }
    } catch (SQLException e) {
    }
    disconnect();
    return uemail;
  }

  public String[] serach() {
    //uemail은 나의 email이다.
    connect();
    String[]str = new String[1000];
    String sql = "select user_email from user";
    int i =0;
    try {
      pstmt = conn.prepareStatement(sql);
      //pstmt.setString(1,"user_name");
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        str[i] = (rs.getString("user_email"));
        // System.out.println("str[i] is = " + str[i]);
        i++;
      }
    } catch (SQLException e) {
    }
    disconnect();
    return str;
  }


  public boolean insertFriend(String me, String friend_email) {
    connect();

    String sql = "select user_email from user where user_name = " + "'" + me + "'";
    try {
      pstmt = conn.prepareStatement(sql);
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        my_email = (rs.getString("user_email"));
        //System.out.println(my_email);
      }
    } catch (SQLException e) {
    }

    sql = "insert into friend values (" + "'" + my_email +"'" + "," +"'" + friend_email + "'" + ")";

    boolean isInsert = false;

    try {

      if(!my_email.equals(friend_email)){
        pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();
        isInsert = true;
      }

    } catch (SQLException e) {
      //isInsert = false;
      //System.out.println(e);
    }

    disconnect();

    return isInsert;
  }

  public String getTodayTalk(String username){
    //System.out.println("오늘의 한마디 : " + username);
    //uemail은 나의 email이다.
    connect();
    String str = null;
    String sql = "select user_todaytalk from user where user_name = ?";
    String uname = null;
    int i =0;
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1,username);
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        str = (rs.getString("user_todaytalk"));
        //System.out.println("todaytalk is = " + str);
      }
    } catch (SQLException e) {
    }
    disconnect();

    return str;
  }

  public boolean getState(String username){
    //uemail은 나의 email이다.
    connect();
    boolean state = false;
    String sql = "select user_state from user where user_name = ?";
    String uname = null;
    int i =0;
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1,username);
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        state = (rs.getBoolean("user_state"));
      }
    } catch (SQLException e) {
    }
    disconnect();

    return state;
  }

  public boolean getState_for_email(String email){
    //uemail은 나의 email이다.
    connect();
    boolean state = false;
    String sql = "select user_state from user where user_email = ?";
    String uname = null;
    int i =0;
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1,email);
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        state = (rs.getBoolean("user_state"));
      }
    } catch (SQLException e) {
    }
    disconnect();

    return state;
  }

  public boolean modifyDB(User user) {

    Controller a = Controller.getInstance();
    String name = a.username;
    //System.out.println(name);

    if (user.getPassword().length() == 0 || user.getPhone_num().length() == 0 || user.getBirth().length() == 0 || user.getNickname().length() == 0 || user.getToday_talk().length() == 0) {
      System.out.println("입력 정보 부족");

      return false;

    } else
    {
      connect();

      String sql = "UPDATE user SET "
              + "user_password = '" + user.getPassword() + "', "
              + "user_createdAt = SYSDATE(),  "
              + "user_nickname = '" + user.getNickname() + "', "
              + "user_birthday = '" + user.getBirth() + "', "
              + "user_todaytalk = '" + user.getToday_talk() + "', "
              + "user_phonenumber =  '" + user.getPhone_num() + "',"
              + "user_state = 1, "
              + "user_address = '" + user.getAddress() + "' ,"
              + "user_siteaddress = '" + user.getSite_address() + "' "
              + "where user_name = '" + name + "' ";
      try {

        pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();

        disconnect();

        return true;

      } catch (SQLException e) {
        System.out.println(e);
      }

      return false;
    }
  }


  public void toOn(String username){
    connect();
    boolean state = false;
    String sql = "update user set user_state = true where user_name = ?";
    String uname = null;
    int i =0;
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1,username);
      pstmt.executeUpdate();
      state = true;
    } catch (SQLException e) {
    }
    disconnect();
  }

  public boolean logout(String username){
    System.out.println("로그아웃 실행 시작: " + username);
    connect();
    boolean state = true;

    String sql = "update user set user_state = false where user_name = '" + username +"'";
    // String uname = null;
    int i =0;
    try {
      pstmt = conn.prepareStatement(sql);
      // pstmt.setString(1,username);
      pstmt.executeUpdate();

      state = false;

    } catch (SQLException e) {
      System.out.println(e);
    }
    disconnect();

    return state;
  }

  public void Exit()
  {
    connect();
    String sql = "Delete from user where user_email = '" + my_email +"'";
    // String uname = null;
    int i =0;
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.executeUpdate();

    } catch (SQLException e) {
      System.out.println(e);
    }
    disconnect();


    connect();
    sql = "Delete from friend where friend_myEmail = '" + my_email +"'";
    // String uname = null;
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.executeUpdate();

    } catch (SQLException e) {
      System.out.println(e);
    }
    disconnect();

  }

  public String getUsername(String email) {
    //uemail은 나의 email이다.
    connect();

    String sql = "select user_name from user where user_email = ?";
    String uname = null;
    int i =0;
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1,email);
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        uname = (rs.getString("user_name"));
      }
    } catch (SQLException e) {
    }
    disconnect();

    return uname;
  }

  public boolean duplicate (String email){
    //uemail은 나의 email이다.
    connect();

    String sql = "select user_name from user where user_email = ?";
    String uname = null;
    int i =0;
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1,email);
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        uname = (rs.getString("user_name"));

        disconnect();

        return false;
      }
    } catch (SQLException e) {
      disconnect();

      return true;
    }

    return true;
  }

  public String findemail(ArrayList<TextField> userInfos) {

    connect();
    String sql = "select * from user where user_email=?";
    String uemail = userInfos.get(0).getText();

    String uname = null;
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, uemail);
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        uname = rs.getString("user_email");
      }
      username = uname;

    } catch (SQLException e) {
      e.printStackTrace();
    }

    disconnect();

    return username;
  }


}