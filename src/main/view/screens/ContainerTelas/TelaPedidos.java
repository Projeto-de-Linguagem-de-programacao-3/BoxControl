package main.view.screens.ContainerTelas;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import main.view.components.UpperScreen;
import main.view.screens.TelasPrincipais.PedidosPrincipal;

public class TelaPedidos extends JPanel {
  UpperScreen upperScreen;
  PedidosPrincipal pedidosPrincipal;

  public TelaPedidos() {
    super();
    setLayout(new BorderLayout());
    add(getUpperScreen(), BorderLayout.PAGE_START);
    add(getPedidosPrincipal(), BorderLayout.CENTER);
  }

  public UpperScreen getUpperScreen() {
    if(upperScreen == null) {
      upperScreen = new UpperScreen("Pedidos", "src/resources/bigIcons/pedidoBigIcon.png");
    }
    return upperScreen;
  }

  public PedidosPrincipal getPedidosPrincipal() {
    if(pedidosPrincipal == null) {
      pedidosPrincipal = new PedidosPrincipal();
    }
    return pedidosPrincipal;
  }
}
