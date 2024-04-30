package main.view.screens.ContainerTelas;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import main.view.components.UpperScreen;
import main.view.screens.TelasPrincipais.HomePrincipal;

// Tela Com o Balanço

public class TelaHome extends JPanel {
  UpperScreen upperScreen;
  HomePrincipal homePrincipal;

  public TelaHome() {
    super();
    setLayout(new BorderLayout());
    add(getUpperScreen(), BorderLayout.PAGE_START);
    add(getHomePrincipal(), BorderLayout.CENTER);
  }

  public UpperScreen getUpperScreen() {
    if(upperScreen == null) {
      upperScreen = new UpperScreen("Home", "src/resources/bigIcons/homeBigIcon.png");
    }
    return upperScreen;
  }

  public HomePrincipal getHomePrincipal() {
    if(homePrincipal == null) {
      homePrincipal = new HomePrincipal();
    }
    return homePrincipal;
  }
}
