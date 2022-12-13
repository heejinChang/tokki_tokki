package client.frame;

import controller.Controller;
import server.datacommunication.Message;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;

public class Pop extends JPopupMenu {

    JPopupMenu pm = new JPopupMenu();
    private ArrayList<String> friends; // 친구들 이름 저장

    JButton request = new JButton("대화 신청");
    JButton todayTalk;

    boolean state_B = false;

    JButton state;

    private int idx = 0;

    //private String todayTalk = null;

    Controller controller;

    public Pop(ArrayList<String> f, int k){
        controller = Controller.getInstance();

        friends = f;
        todayTalk = new JButton(controller.today_talk(friends.get(k)));
        state_B = controller.getState(friends.get(k));

        if(state_B == true){
            state = new JButton("활동중입니다");
        }
        else{
            state = new JButton("활동 x");
        }

        request.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println(friends.get(idx));

                if(state_B == false){
                    JOptionPane.showMessageDialog(null, friends.get(k) + "님은 접속 상태가 아닙니다.", "대화 신청", JOptionPane.WARNING_MESSAGE);
                }
                else{
                    // '요청' 메세지를 보낸다
                    System.out.println("대화를 신청하는 사람 : " + controller.username);
                    Message message = new Message(controller.username, "request", LocalTime.now(), "request",
                            friends.get(k));

                    controller.clientSocket.send(message);
                }
            }
        });

        pm.add(request);
        pm.add(todayTalk);
        pm.add(state);
    }


}
