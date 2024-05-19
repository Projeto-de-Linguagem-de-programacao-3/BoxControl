package main.view.screens.ContainerTelas;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import main.view.components.UpperScreen;
import main.view.screens.TelasPrincipais.VendasPrincipal;

public class TelaVenda extends JPanel {
  UpperScreen upperScreen;
  VendasPrincipal vendasPrincipal;

  public TelaVenda() {
    super();
    setLayout(new BorderLayout());
    add(getUpperScreen(), BorderLayout.PAGE_START);
    add(getVendasPrincipal(), BorderLayout.CENTER);
  }

  public UpperScreen getUpperScreen() {
    if(upperScreen == null) {
      upperScreen = new UpperScreen("Vendas", "src/assets/bigIcons/vendasBigIcon.png");
    }
    return upperScreen;
  }

  public VendasPrincipal getVendasPrincipal() {
    if(vendasPrincipal == null) {
      vendasPrincipal = new VendasPrincipal();
    }
    return vendasPrincipal;
  }
}
