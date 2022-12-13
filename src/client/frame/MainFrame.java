package client.frame;

import controller.Controller;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements WindowListener {

  private MainPanel mainPanel;
  Controller controller;

  public MainFrame() {

    setTitle("KakaoTalk");
    setBounds(800, 20, 400, 800);
    mainPanel = new MainPanel(this);
    getContentPane().add(mainPanel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setVisible(true);
    this.addWindowListener(this);
  }

  /*panel 바꾸기*/
  public void change(JPanel panelName) {
    getContentPane().removeAll();
    getContentPane().add(panelName);
    revalidate();
    repaint();
  }

  @Override
  public void windowOpened(WindowEvent e) {

  }

  @Override
  public void windowClosing(WindowEvent e) {
    controller = Controller.getInstance();
    String name = controller.username;
    System.out.println("프레임 : " + name);
    controller.logout(name);
  }

  @Override
  public void windowClosed(WindowEvent e) {
    controller = Controller.getInstance();
    String name = controller.username;
    System.out.println("프레임 : " + name);
    controller.logout(name);
  }

  @Override
  public void windowIconified(WindowEvent e) {

  }

  @Override
  public void windowDeiconified(WindowEvent e) {

  }

  @Override
  public void windowActivated(WindowEvent e) {

  }

  @Override
  public void windowDeactivated(WindowEvent e) {

  }
}
