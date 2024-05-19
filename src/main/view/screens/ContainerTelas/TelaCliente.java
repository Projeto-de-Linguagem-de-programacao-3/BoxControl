package main.view.screens.ContainerTelas;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import main.view.components.UpperScreen;
import main.view.screens.TelasPrincipais.ClientePrincipal;

public class TelaCliente extends JPanel {
  UpperScreen upperScreen;
  ClientePrincipal clientePrincipal;

  public TelaCliente() {
    super();
    setLayout(new BorderLayout());
    add(getUpperScreen(), BorderLayout.PAGE_START);
    add(getClientePrincipal(), BorderLayout.CENTER);
  }

  public UpperScreen getUpperScreen() {
    if(upperScreen == null) {
      upperScreen = new UpperScreen("Clientes", "src/assets/bigIcons/usuarioBigIcon.png");
    }
    return upperScreen;
  }

  public ClientePrincipal getClientePrincipal() {
    if(clientePrincipal == null) {
      clientePrincipal = new ClientePrincipal();
    }
    return clientePrincipal;
  }
}
