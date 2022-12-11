package util;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
public class CommonPanel extends JPanel {

  protected JLabel logoLabel;

  protected Image logoImg = UseImageFile.getImage("resources/img_1.png");

  protected CommonPanel() {

    setBackground(ColorSet.talkBackgroundColor);
    setLayout(null);

    logoLabel = new JLabel(new ImageIcon(logoImg));
    logoLabel.setBounds(50, 500, 200, 50);
    //add(logoLabel);
  }

}
