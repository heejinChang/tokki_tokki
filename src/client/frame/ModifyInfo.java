package client.frame;
import controller.Controller;
import enums.CommonWord;
import server.userdb.User;
import server.userdb.UserDAO;
import util.UserInfoPanel;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import static java.awt.Color.RED;


public class ModifyInfo extends UserInfoPanel {
    private final String MODIFY = "정보 수정";

    private ArrayList<TextField> userInfos = new ArrayList<TextField>();

    private User user;

    public ModifyInfo() {
        showFormTitle("정보 수정");
        writeUserInfo();
        showSignUpButton();
    }

    // 정보 수정 폼 내용
    public void writeUserInfo() {
        int y_value = 180;
        int a = 0;

        for (CommonWord commonWord : CommonWord.values()) {

            if (commonWord.getNum() > CommonWord.EMAIL.getNum()
                    && commonWord.getNum() <= CommonWord.SITE_ADDRESS.getNum()) {
                System.out.println(commonWord.getText());

                //System.out.println(commonWord.getText());
                if(commonWord.getText().equals("집주소")){
                    formTitleLabel = new JLabel(commonWord.getText() + "(선택)");
                }
                else if(commonWord.getText().equals("사이트 주소")){
                    formTitleLabel = new JLabel(commonWord.getText() + "(선택)");
                }
                else{
                    formTitleLabel = new JLabel(commonWord.getText());
                }
                formTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
                formTitleLabel.setBounds(30, y_value - 2, 200, 30);
                add(formTitleLabel);

                if (a == 1) {
                    userInfoTextField.setEchoChar('*');
                }

                userInfoTextField = new TextField(10);
                userInfoTextField.setBounds(30, y_value + 30, 325, 30);
                add(userInfoTextField);
                y_value += 65;
                a++;

                /* 비밀번호 입력 후 바로 enter 누르면 화면 넘어가도록. */
                if (commonWord.getNum() == CommonWord.PASSWORD.getNum()) {
                    userInfoTextField.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            modifyinfo();
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

        JButton signupButton = showFormButton(MODIFY);
        signupButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                modifyinfo();
            }
        });
    }

    private void modifyinfo() {

        /*System.out.println("----------------------------------------");
        for(int i = 0; i < userInfos.size(); i++){
          System.out.println(userInfos.get(i).getText());
        }*/

        user = new User(
                userInfos.get(0).getText(), userInfos.get(1).getText(),
                userInfos.get(2).getText(), userInfos.get(3).getText(),
                userInfos.get(4).getText(), userInfos.get(5).getText(), userInfos.get(6).getText());

        Controller controller = Controller.getInstance();

        controller.modifyDB(user);
    }
}