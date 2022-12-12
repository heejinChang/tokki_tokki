package client.frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import controller.Controller;
import enums.CommonWord;
import server.userdb.User;
import util.UserInfoPanel;

@SuppressWarnings("serial")
public class JoinMembershipPanel extends UserInfoPanel {

  private final String SIGN_UP = "가입하기";

  private ArrayList<TextField> userInfos = new ArrayList<TextField>();

  private User user;

  public JoinMembershipPanel() {

    showFormTitle(CommonWord.SIGN_UP_MEMBERSHIP.getText());
    writeUserInfo();
    showSignUpButton();
  }

  /* 회원가입 폼 내용 */
  public void writeUserInfo() {
    int y_value = 180;
    int a = 0;

    for (CommonWord commonWord : CommonWord.values()) {
      if (commonWord.getNum() >= CommonWord.NAME.getNum()
          && commonWord.getNum() <= CommonWord.SITE_ADDRESS.getNum()) {
        System.out.println(commonWord.getText());
        formTitleLabel = new JLabel(commonWord.getText());
        formTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        formTitleLabel.setBounds(30, y_value - 8, 200, 50);
        add(formTitleLabel);

        if(a == 3)
        {
          userInfoTextField.setEchoChar('*');
        }

        userInfoTextField = new TextField(10);
        userInfoTextField.setBounds(30, y_value + 35, 325, 30);
        add(userInfoTextField);
        y_value += 60;
        a++;

        /* 비밀번호 입력 후 바로 enter 누르면 화면 넘어가도록. */
        if (commonWord.getNum() == CommonWord.PASSWORD.getNum()) {
          userInfoTextField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

              createUser();

            }
          });
        }
        userInfos.add(userInfoTextField);
      } else {
        continue;
      }
    }
  }

  private void showSignUpButton() {

    JButton signupButton = showFormButton(SIGN_UP);
    signupButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {

        createUser();

      }
    });
  }

  private void createUser() {

    /*for(int i = 0; i < userInfos.size(); i++){
      System.out.println(i);
    }*/

    user = new User(userInfos.get(0).getText(), userInfos.get(1).getText(),
        userInfos.get(2).getText(),userInfos.get(3).getText(),
            userInfos.get(4).getText(),userInfos.get(5).getText(),
            userInfos.get(6).getText(),userInfos.get(7).getText(),userInfos.get(8).getText());
    
    Controller controller = Controller.getInstance();
    controller.insertDB(user);
  }
}
