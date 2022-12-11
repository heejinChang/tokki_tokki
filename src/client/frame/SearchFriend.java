package client.frame;
import controller.Controller;
import server.userdb.UserDAO;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import static java.awt.Color.RED;

public class SearchFriend extends JFrame implements ActionListener {
    JButton btn1 = new JButton("검색");
    JButton btn2 = new JButton("추가");
    TextField tf = new TextField("이름을 입력하세요",20);
    //친구 list 가져올때 사용
    String[] found;

    //친구 수를 나타내기 위해 사용
    int count = 0;
    public String[] sstr;
    SearchFriend()
    {
        JPanel jp =new JPanel();
        setSize(400,500);
        setLayout(new BorderLayout());

        Label li = new Label("search :");
        btn1.addActionListener(this);
        btn2.addActionListener(this);



        //search 랑 textfiled 저장해놓음.
        jp.setLayout(new FlowLayout());
        jp.add(li);
        jp.add(tf);
        jp.add(btn1);
        jp.add(btn2);
        add(jp,BorderLayout.NORTH);

        setVisible(true);

    }

    public void friendList(int k)
    {
        if(k!= 0) {
            JList list = new JList(found);
            JPanel jp2 = new JPanel();
            JScrollPane aa = new JScrollPane(list);
            aa.setSize(300,300);
            jp2.add(aa);
            add(jp2, BorderLayout.CENTER);
        }else {
            JLabel JL = new JLabel("Can not found!");
            JPanel jp2 = new JPanel();
            jp2.add(JL);
            add(jp2, BorderLayout.CENTER);
        }
        setVisible(true);
    }


    public void find(String text)
    {
        //add(jl);
        UserDAO us = new UserDAO();
        String[] str = us.serach();
        //str은 database로 부터 받아온 나를 제외한 사람들의 list이다.

        String[] result = new String[30];
        int i =0;
        int k = 0;
        while(str[i] != null)
        {
            if(str[i].contains(text))
            {
                result[k] = str[i];
                System.out.println(result[k]);
                k++;
            }
            i++;
        }
        found = result;
        count = k;
        friendList(k);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()== btn1) {
            System.out.println("검색기능");
            String str = tf.getText();
            //System.out.println(str);
            find(str);
        }
        else if(e.getSource() == btn2)
        {
            String str = tf.getText();
            Controller ct = Controller.getInstance();
            String name = ct.username;
            UserDAO ab = new UserDAO();
            System.out.println("my Name :" + name + "friend Name : " + str);
            ab.insertFriend(name, str);
            IndexPanel indexPanel = new IndexPanel();
            MainPanel.frame.change(indexPanel);
        }

    }

}
