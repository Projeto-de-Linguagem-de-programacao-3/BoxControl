package main.view.screens.ContainerTelas;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import main.view.components.UpperScreen;
import main.view.screens.TelasPrincipais.ProdutoPrincipal;

public class TelaProduto extends JPanel {
  UpperScreen upperScreen;
  ProdutoPrincipal produtoPrincipal;

  public TelaProduto() {
    super();
    setLayout(new BorderLayout());
    add(getUpperScreen(), BorderLayout.PAGE_START);
    add(getProdutoPrincipal(), BorderLayout.CENTER);
  }

  public UpperScreen getUpperScreen() {
    if(upperScreen == null) {
      upperScreen = new UpperScreen("Produtos", "src/assets/bigIcons/produtoBigIcon.png");
    }
    return upperScreen;
  }

  public ProdutoPrincipal getProdutoPrincipal() {
    if(produtoPrincipal == null) {
      produtoPrincipal = new ProdutoPrincipal();
    }
    return produtoPrincipal;
  }
}
