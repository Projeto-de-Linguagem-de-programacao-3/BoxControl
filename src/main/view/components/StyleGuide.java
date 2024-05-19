package main.view.components;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class StyleGuide {
  public static Color bgMenu = new Color(47,72,88);
  public static Color bgButton = new Color(26,34,51);
  public static Color bgUpperPanel = new Color(47,72,88);
  public static Color bgScreen = new Color(51,101,138);

  public static Color white = Color.WHITE;
  public static Color titleButton = Color.WHITE;
  public static Color titleScreen = Color.WHITE;

  public static void formataComponente(JTextField elemento) {
    elemento.setFont(new Font("Tahoma", Font.PLAIN, 14));
  }

  public static void formataComponente(JLabel elemento) {
    elemento.setFont(new Font("Tahoma", Font.BOLD, 16));
    elemento.setForeground(StyleGuide.bgButton);
  }

  public static void formataComponente(JFormattedTextField elemento) {
    elemento.setFont(new Font("Tahoma", Font.PLAIN, 14));
  }

  public static void formataComponente(JComboBox<String> elemento) {
    elemento.setFont(new Font("Tahoma", Font.PLAIN, 14));
  }

  public static void formataComponente(JButton elemento) {
    elemento.setFont(new Font("Tahoma", Font.BOLD, 16));
    elemento.setForeground(StyleGuide.white);
    elemento.setBackground(StyleGuide.bgButton);
    elemento.setCursor(new Cursor(Cursor.HAND_CURSOR));
    elemento.setFocusPainted(false);
    elemento.setBorder(new EmptyBorder(10,10,10,10));
  }
}
