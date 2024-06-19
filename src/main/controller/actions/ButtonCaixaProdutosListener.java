package main.controller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import main.view.screens.TelasPrincipais.VendasPrincipal;
import main.view.components.CaixaProdutos; // Ensure you import the correct package

public class ButtonCaixaProdutosListener implements ActionListener {
  private VendasPrincipal vendasPrincipal;

  public ButtonCaixaProdutosListener(VendasPrincipal vendasPrincipal) {
    this.vendasPrincipal = vendasPrincipal;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void actionPerformed(ActionEvent e) {
    CaixaProdutos caixaProdutos = vendasPrincipal.getCaixaProdutos();
    if (caixaProdutos != null) {
      if (e.getSource() == caixaProdutos.btnAdicionar) {
        List<String> selecionadas = caixaProdutos.listDisponiveis.getSelectedValuesList();
        for (int i = 0; i < selecionadas.size(); i++) {
          String disciplina = selecionadas.get(i);
          caixaProdutos.modelSelecionadas.addElement(disciplina);
        }
      } else {
        List<String> selecionadas = caixaProdutos.listSelecionadas.getSelectedValuesList();
        for (int i = 0; i < selecionadas.size(); i++) {
          String disciplina = selecionadas.get(i);
          caixaProdutos.modelSelecionadas.removeElement(disciplina);
        }
      }
    }
  }
}
