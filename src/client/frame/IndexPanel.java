package client.frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.*;

import controller.Controller;
import enums.CommonWord;
import server.datacommunication.Message;
import util.CommonPanel;
import util.UseImageFile;
import util.UserProfileButton;

@SuppressWarnings("serial")
public class IndexPanel extends CommonPanel {

  private JLabel jLabel;
  private JButton btn;

  private Image img = UseImageFile.getImage("C:\\Users\\user\\IdeaProjects\\SimpleTalk\\resources\\cchunsik.png");

  public static UserProfileButton userProfileButton;

  public static ArrayList<ChatWindowPanel> chatPanelName = new ArrayList<ChatWindowPanel>();

  public static JLabel jl = new JLabel("친구찾기");
  Controller controller;

  public IndexPanel() {
    controller = Controller.getInstance();

    meanMyProfileTitle(CommonWord.MYPROFILE.getText());
    meanMyProfile();

    meanMyProfileTitle2("친구찾기 기능");


    //"친구"라고 label보여주기
    meanFriendProfileTitle(CommonWord.FRIEND.getText());
    //친구 목록 보여주기
    showFriendList();

    meanApiTitle(CommonWord.API.getText());
    meanApi(); // 공공데이터 api 실행하기

  }

  private void meanMyProfileTitle(String text) {

    jLabel = new JLabel(text);
    jLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
    jLabel.setBounds(30, 80, 200, 30);
    //jLable = 내 프로필
    add(jLabel);
  }

  private void meanMyProfileTitle2(String text) {

    btn = new JButton(text);
    btn.setBackground(new Color(252, 255, 204));
    btn.setFont(new Font("맑은 고딕", Font.BOLD, 14));
    btn.setBounds(30, 50, 200, 30);
    btn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if(btn.equals(e.getSource()))
        {
          System.out.println("검색시작");
          new SearchFriend();
        }
      }
    });
    //btn = 친구찾기기능.

    add(btn);
  }


  private void meanMyProfile() {

    ImageIcon imageIcon = new ImageIcon(img);
    userProfileButton = new UserProfileButton(imageIcon);
    userProfileButton.setText(controller.username);
    System.out.println("user name = " + controller.username);
    userProfileButton.setBounds(30, 120, 1000, 80);
    add(userProfileButton);
    userProfileButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        if (userProfileButton.getText().contains("대화 중..")) {
          // 작동x
        } else {
          userProfileButton.setText(userProfileButton.getText() + "       대화 중..");
          String messageType = "text";
          Message message = new Message(controller.username, controller.username + "님이 입장하였습니다.",
                  LocalTime.now(), messageType, controller.username);
          ChatWindowPanel c = new ChatWindowPanel(imageIcon, controller.username);
          new ChatWindowFrame(c, controller.username);
          chatPanelName.add(c);

          Controller controller = Controller.getInstance();
          controller.clientSocket.send(message);
        }
      }
    });
  }

  private void meanFriendProfileTitle(String text) {

    jLabel = new JLabel(text);
    jLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
    jLabel.setBounds(30, 220, 200, 30);
    //label = 친구
    add(jLabel);


  }

  private void showFriendList() {

    FriendListPanel jpanel = new FriendListPanel();
    System.out.println("친구 리스트 패널 불러오기");
    JScrollPane scroller = new JScrollPane(jpanel);
    scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    scroller.setBounds(30, 250, 350, 300);
    add(scroller);
  }

  // 공공데이터 대기오염정보
  private void meanApiTitle(String text) {
    System.out.println("mean Api title :"+text);
    jLabel = new JLabel(text);
    jLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
    jLabel.setBounds(30, 600, 200, 20);
    add(jLabel);
  }

  // 공공데이터
  private void meanApi() {
    String bb = client.API.xmlParsing.dustApi();
    System.out.println("meanApi" +  bb);
    jLabel = new JLabel("<html><body><center>" + bb + "<br> <br></center></body></html>");
    jLabel.setBounds(30, 650, 600, 70);

    add(jLabel);
    // 문자열 추가 하기
  }

  public void paint(Graphics g) {

    super.paint(g);
    Graphics2D g2 = (Graphics2D) g;
    Line2D lin = new Line2D.Float(30, 210, 350, 210);
    g2.draw(lin);
  }
}
