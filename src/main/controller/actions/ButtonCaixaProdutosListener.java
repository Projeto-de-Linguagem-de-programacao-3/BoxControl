package main.controller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import main.view.screens.TelasPrincipais.VendasPrincipal;

public class ButtonCaixaProdutosListener implements ActionListener {
  VendasPrincipal vendasPrincipal;

  public ButtonCaixaProdutosListener(VendasPrincipal vendasPrincipal) {

  }

  @SuppressWarnings("unchecked")
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == vendasPrincipal.getCaixaProdutos().btnAdicionar) {
      List<String> selecionadas = vendasPrincipal.getCaixaProdutos().listDisponiveis.getSelectedValuesList();
      for (int i = 0; i < selecionadas.size(); i++) {
        String disciplina = selecionadas.get(i);
        vendasPrincipal.getCaixaProdutos().modelSelecionadas.addElement(disciplina);
      }
    } else {
      List<String> selecionadas = vendasPrincipal.getCaixaProdutos().listSelecionadas.getSelectedValuesList();
      for (int i = 0; i < selecionadas.size(); i++) {
        String disciplina = selecionadas.get(i);
        vendasPrincipal.getCaixaProdutos().modelSelecionadas.removeElement(disciplina);
      }
    }
  }

}
