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
  private JButton btn1;
  private JButton btn2;
  private JButton btn3;


  private JLabel jLabel2;

  private Image img = UseImageFile.getImage("resources/woman.png");

  public static UserProfileButton userProfileButton;

  public static ArrayList<ChatWindowPanel> chatPanelName = new ArrayList<ChatWindowPanel>();

  public static JLabel jl = new JLabel("친구 찾기");

  public static JLabel modify = new JLabel("정보 수정");
  Controller controller;

  public IndexPanel() {
    controller = Controller.getInstance();

    meanMyProfileTitle(CommonWord.MYPROFILE.getText());
    meanMyProfile();

    meanMyProfileTitle2("정보 수정");
    meanFindFriend("친구 찾기");
    meanlogout("로그아웃");

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

    ImageIcon img = new ImageIcon("resources/re4.png");
    JButton jb = new JButton(img);
    jb.setBounds(100,80,30, 30);
    jb.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        MainPanel.frame.change(new IndexPanel());
      }
    });
    add(jb);
  }

  private void meanMyProfileTitle2(String text) {
// 정보 수정
    btn1 = new JButton(text);
    btn1.setBackground(new Color(252, 255, 204));
    btn1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
    btn1.setBounds(10, 40, 122, 30);

    add(btn1);
    btn1.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("정보 수정");
        // new ModifyInfo();
        ModifyInfo modi = new ModifyInfo();
        MainPanel.frame.change(modi);
      }
    });
    // 정보 수정 버튼
  }
  private void meanFindFriend(String text){
    btn2 = new JButton(text);
    btn2.setBackground(new Color(252, 255, 204));
    btn2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
    btn2.setBounds(130, 40, 122, 30);
    btn2.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if(btn2.equals(e.getSource()))
        {
          System.out.println("친구 찾기");
          new SearchFriend();
        }
      }
    });
    // 친구 찾기 기능
    add(btn2);
  }

  private void meanlogout(String text){
    btn3 = new JButton(text);
    btn3.setBackground(new Color(252, 255, 204));
    btn3.setFont(new Font("맑은 고딕", Font.BOLD, 14));
    btn3.setBounds(250, 40, 122, 30);
    btn3.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if(btn3.equals(e.getSource()))
        {
          controller = Controller.getInstance();
          String username = controller.username;
          System.out.println("로그아웃 하려는 사용자 이름: " + username);
          controller.logout(username);

          LoginPanel goto_login = new LoginPanel();
          MainPanel.frame.change(goto_login);
        }
      }
    });
    // 친구 찾기 기능
    add(btn3);
  }

  private void meanMyProfile() {

    ImageIcon imageIcon = new ImageIcon(img);
    userProfileButton = new UserProfileButton(imageIcon);
    userProfileButton.setText(controller.username);
    System.out.println("user name = " + controller.username);
    userProfileButton.setBounds(30, 120, 180, 80);
    add(userProfileButton);

    String today_talk = controller.today_talk(controller.username);
    jLabel2 = new JLabel(today_talk);
    //System.out.println("오늘의 한마디!!!!" + today_talk);
    jLabel2.setBounds(250, 120,1000, 80);
    jLabel2.setFont(new Font("맑은 고딕", Font.BOLD, 13));
    add(jLabel2);

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

    //System.out.println("친구 리스트 패널 불러오기");
    JScrollPane scroller = new JScrollPane(jpanel);
    scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    scroller.setBounds(30, 250, 350, 300);
    add(scroller);
  }

  // 공공데이터 대기오염정보
  private void meanApiTitle(String text) {
    //System.out.println("mean Api title :"+text);
    jLabel = new JLabel(text);
    jLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
    jLabel.setBounds(30, 600, 200, 20);
    add(jLabel);
  }

  // 공공데이터
  private void meanApi() {
    String bb = client.API.xmlParsing.dustApi();
    //System.out.println("meanApi" +  bb);
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
