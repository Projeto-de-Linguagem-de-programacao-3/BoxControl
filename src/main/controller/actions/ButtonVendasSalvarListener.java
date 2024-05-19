package main.controller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JOptionPane;

import main.model.entity.DadosVendas;
import main.view.screens.TelasPrincipais.VendasPrincipal;

public class ButtonVendasSalvarListener implements ActionListener {
  VendasPrincipal vendasPrincipal;
  
  public ButtonVendasSalvarListener(VendasPrincipal vendasPrincipal) {
    this.vendasPrincipal = vendasPrincipal;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // Obter os dados inseridos pelo usuário
    String cliente = (String) vendasPrincipal.getComboBoxCliente().getSelectedItem();
    String produtosQuantidadeTexto = vendasPrincipal.getTextProdutosQuantidade().getText(); // Aqui você precisa converter o texto em um mapa de
                                                                   // produtos e quantidades
    String formaPagamento = (String) vendasPrincipal.getComboBoxFormaPagamento().getSelectedItem();
    String valorTotalTexto = vendasPrincipal.getTextValorTotal().getText(); // Aqui você precisa converter o texto em um Double

    // Aqui você precisa converter o texto em um mapa de produtos e quantidades
    Map<String, Integer> produtosQuantidade = vendasPrincipal.converterParaMapa(produtosQuantidadeTexto);

    // Aqui você precisa converter o texto em um Double
    Double valorTotal = Double.parseDouble(valorTotalTexto);

    DadosVendas vendas = new DadosVendas();

    vendas.setCliente(cliente);
    vendas.setProdutosQuantidade(produtosQuantidade);
    vendas.setFormaPagamento(formaPagamento);
    vendas.setValorTotal(valorTotal);

    String resultado = vendas.salvarTxt();
    JOptionPane.showMessageDialog(vendasPrincipal, resultado, "Resultado:", JOptionPane.INFORMATION_MESSAGE);
  }
}
