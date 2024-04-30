package main.controller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.view.components.MenuLateral;
import main.view.components.TelaSwitch;

public class ButtonMenuListener implements ActionListener {
  // Adiciona os JPanels do menu lateral e da tela switch
  private MenuLateral menuLateral;
  private TelaSwitch telaSwitch;

  // Inicializa os JPanels por parâmetro, assim é possível alterar eles em execução
  public ButtonMenuListener(MenuLateral menuLateral,TelaSwitch telaSwitch) {
    this.menuLateral = menuLateral;
    this.telaSwitch = telaSwitch;
  }

  // Método de implementação simples, se for o botão de cada tela, 
  // chama o telaSwitch com o método de show("NomeDaTela")
  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getSource() == menuLateral.getBotaoClientes()) {
      telaSwitch.telaClientes();
    } else if(e.getSource() == menuLateral.getBotaoProdutos()) {
      telaSwitch.telaProdutos();
    } else if(e.getSource() == menuLateral.getBotaoPedidos()) {
      telaSwitch.telaPedidos();
    } else if(e.getSource() == menuLateral.getBotaoVendas()) {
      telaSwitch.telaVendas();
    } else {
      telaSwitch.telaHome();
    }
  }
  
}
