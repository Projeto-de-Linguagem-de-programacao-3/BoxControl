package main.view.screens;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Frame extends JFrame {
  private static final long serialVersionUID = 1L;

  public Frame() {
    super("Sistema Mercado");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(1200, 700);
    ImageIcon icon = new ImageIcon("src/assets/programIcon.png");
    setIconImage(icon.getImage());
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setLocationRelativeTo(null);
    setVisible(true);
  }
}
