package client.frame;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

import controller.Controller;
import server.datacommunication.Message;
import util.ColorSet;
import util.UseImageFile;
import util.UserProfileButton;
import java.awt.MenuItem;
import java.awt.PopupMenu;

@SuppressWarnings("serial")
public class FriendListPanel extends JPanel {

  private ArrayList<String> friends; // 친구들 이름 저장

  private ArrayList<ImageIcon> friendIcons = new ArrayList<ImageIcon>(); // 친구들 프로필 이미지 저장

  public static ArrayList<JButton> friendButtons = new ArrayList<JButton>(); // 친구들 정보 버튼 저장

  private JLabel jLabel2;

  String today_talk = null;

  UserProfileButton userprofileButton;

  ImageIcon imageIcon;

  private final int FRIEND_PROFILE_IMG_MAX = 8;

  private final int FRIEND_PROFILE_IMG_MIN = 1;

  Controller controller;

  public FriendListPanel() {

    setBackground(ColorSet.talkBackgroundColor);
    controller = Controller.getInstance();
    friends = controller.friendList();

    System.out.println("친구리스트 불러오기 성공");
    int friendNum = friends.size();
    System.out.println("친구수 : " + friendNum);
    setLayout(new GridLayout(friendNum,10));
    for (int index = 0; index < friendNum; index++) {
      int k = index;
      Random rand = new Random();
      int randomNum =
              rand.nextInt((FRIEND_PROFILE_IMG_MAX - FRIEND_PROFILE_IMG_MIN) + FRIEND_PROFILE_IMG_MIN)
                      + 1;

      boolean state = controller.getState(friends.get(index));


      if(state == true){
        Image img = UseImageFile.getImage("resources/on.png");
        imageIcon = new ImageIcon(img);
        userprofileButton = new UserProfileButton(imageIcon);
        userprofileButton.setText(friends.get(index)); //이름
      }
      else{
        Image img = UseImageFile.getImage("resources/off.png");
        imageIcon = new ImageIcon(img);
        userprofileButton = new UserProfileButton(imageIcon);
        userprofileButton.setText(friends.get(index)); //이름
      }

      userprofileButton.addMouseListener(new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
          Pop popMenu = new Pop(friends, k);

          popMenu.pm.show(e.getComponent(), e.getX(), e.getY());
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
      });
      add(userprofileButton);
      friendIcons.add(imageIcon);
      friendButtons.add(userprofileButton);

      today_talk = controller.today_talk(friends.get(index));
      jLabel2 = new JLabel(today_talk);
      jLabel2.setBounds(200, 115,1000, 90);
      jLabel2.setFont(new Font("맑은 고딕", Font.BOLD, 13));
      add(jLabel2);
    }
  }
}