package controller;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import client.datacommunication.ClientSocket;
import client.frame.ErrorMessagePanel;
import client.frame.IndexPanel;
import client.frame.MainPanel;
import server.userdb.User;
import server.userdb.UserDAO;

public class Controller {

  private static Controller singleton = new Controller();

  public String username = null;

  public ClientSocket clientSocket;

  UserDAO userDAO;


  private Controller() {

    clientSocket = new ClientSocket();

    userDAO = new UserDAO();

  }

  public static Controller getInstance() {

    return singleton;
  }

  public void insertDB(User user) {

    boolean isInsert = userDAO.insertDB(user);

    if (isInsert) {
      MainPanel mainPanel = new MainPanel(MainPanel.frame);
      MainPanel.frame.change(mainPanel);
      JOptionPane.showMessageDialog(mainPanel, "회원가입 성공!!!", "회원가입", JOptionPane.WARNING_MESSAGE);
    } else {
      ErrorMessagePanel errorPanel = new ErrorMessagePanel("회원가입");
      MainPanel.frame.change(errorPanel);
      JOptionPane.showMessageDialog(null, "회원 가입 정보를 모두 입력해주세요", "회원가입", JOptionPane.WARNING_MESSAGE);
    }

  }

  public void findUser(ArrayList<TextField> userInfos) {

    username = userDAO.findUser(userInfos);
    System.out.println(username);

    if (username != null) {
      System.out.println("회원 정보 존재");
      IndexPanel indexPanel = new IndexPanel();
      System.out.println("인덱스 패널로 이동");
      MainPanel.frame.change(indexPanel);
      clientSocket.startClient();
      JOptionPane.showMessageDialog(indexPanel, "로그인 성공!!!", "로그인", JOptionPane.WARNING_MESSAGE);
      userDAO.toOn(username); //state를 1로 바꿈

    } else if (username == null) {
      ErrorMessagePanel err = new ErrorMessagePanel("로그인");
      MainPanel.frame.change(err);
    }
  }

  public ArrayList<String> friendList() {

    ArrayList<String> friends = new ArrayList<String>();
    friends = userDAO.friendList();

    return friends;
  }

  public String[] searchUser(){
    String str[] = userDAO.serach();
    //System.out.println(str);
    IndexPanel indexPanel = new IndexPanel();

    if(str.length != 0){
      return str;
    }
    else{
      System.out.println("검색 실패");
      /*IndexPanel indexPanel = new IndexPanel();
      MainPanel.frame.change(indexPanel);*/
      //JOptionPane.showMessageDialog(indexPanel, "친구검색 실패!!!", "친구추가", JOptionPane.WARNING_MESSAGE);
    }
    return str;
  }

  public void insertFriend(String me, String friend_email){
    boolean isInsert = true;
    isInsert = userDAO.insertFriend(me, friend_email);
    IndexPanel indexPanel = new IndexPanel();
    System.out.println("컨트롤로의 인서트 프랜드= " + isInsert);

    if (isInsert == true) {
      MainPanel.frame.change(indexPanel);
      System.out.println("친구 추가 성공");
      JOptionPane.showMessageDialog(indexPanel, "친구추가 성공!!!", "친구추가", JOptionPane.INFORMATION_MESSAGE);
    } else if(isInsert == false){
      System.out.println("친구 추가 실패");
      JOptionPane.showMessageDialog(indexPanel, "친구 추가 실패!!!", "친구추가", JOptionPane.WARNING_MESSAGE);
    }
  }

  public String today_talk(String user_name){
    String today_talk = userDAO.getTodayTalk(user_name);
    return today_talk;
  }

  public boolean getState(String username){
    boolean user_state = userDAO.getState(username);
    return user_state;
  }

  public void modifyDB(User user) {

    boolean isModify = userDAO.modifyDB(user);

    if (isModify) {
      MainPanel mainPanel = new MainPanel(MainPanel.frame);
      MainPanel.frame.change(mainPanel);
      JOptionPane.showMessageDialog(mainPanel, "정보 수정 성공", "정보 수정", JOptionPane.WARNING_MESSAGE);
    } else {
      ErrorMessagePanel errorPanel = new ErrorMessagePanel("정보 수정");
      MainPanel.frame.change(errorPanel);
      JOptionPane.showMessageDialog(null, "변경 정보를 모두 입력해주세요", "정보 수정", JOptionPane.WARNING_MESSAGE);
    }

  }

  public void logout(String username){
    //System.out.println(username);
    boolean state = userDAO.logout(username);
    System.out.println("로그아웃이 되었는가? " + state);
  }

}
