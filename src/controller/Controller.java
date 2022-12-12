package controller;

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
    }

  }

  public void findUser(ArrayList<JTextField> userInfos) {

   username = userDAO.findUser(userInfos);
   System.out.println(username);

    if (username != null) {
      System.out.println("회원 정보 존재");
      IndexPanel indexPanel = new IndexPanel();
      System.out.println("인덱스 패널로 이동");
      MainPanel.frame.change(indexPanel);
      clientSocket.startClient();
      JOptionPane.showMessageDialog(indexPanel, "로그인 성공!!!", "로그인", JOptionPane.WARNING_MESSAGE);
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
      JOptionPane.showMessageDialog(indexPanel, "친구검색 실패!!!", "친구추가", JOptionPane.WARNING_MESSAGE);
    }
    return str;
  }

  public void insertFriend(String me, String friend){
    boolean isInsert = userDAO.insertFriend(me, friend);
    IndexPanel indexPanel = new IndexPanel();

    if (isInsert) {
      MainPanel.frame.change(indexPanel);
      System.out.println("친구 추가 성공");
      JOptionPane.showMessageDialog(indexPanel, "친구추가 성공!!!", "친구추가", JOptionPane.WARNING_MESSAGE);
    } else {
      System.out.println("친구 추가 실패");
      JOptionPane.showMessageDialog(indexPanel, "친구 추가 실패!!!", "친구추가", JOptionPane.WARNING_MESSAGE);
    }
  }

  public String today_talk(String user_name){
    String today_talk = userDAO.getTodayTalk(user_name);
    return today_talk;
  }

}
