package main.controller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JOptionPane;

import main.model.entity.DadosVendas;
import main.view.screens.TelasPrincipais.VendasPrincipal;

public class ButtonVendasSalvarListener implements ActionListener {
  private VendasPrincipal vendasPrincipal;

  public ButtonVendasSalvarListener(VendasPrincipal vendasPrincipal) {
    this.vendasPrincipal = vendasPrincipal;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // Obter os dados inseridos pelo usuário
    String cliente = (String) vendasPrincipal.getComboBoxCliente().getSelectedItem();
    String produtosQuantidadeTexto = vendasPrincipal.getTextProdutosQuantidade().getText();
    String formaPagamento = (String) vendasPrincipal.getComboBoxFormaPagamento().getSelectedItem();
    String valorTotalTexto = vendasPrincipal.getTextValorTotal().getText();

    // Converter o texto em um mapa de produtos e quantidades
    Map<String, Integer> produtosQuantidade = vendasPrincipal.converterParaMapa(produtosQuantidadeTexto);

    // Converter o texto em um Double para o valor total
    Double valorTotal = Double.parseDouble(valorTotalTexto);

    // Criar objeto DadosVendas e definir os dados da venda
    DadosVendas vendas = new DadosVendas();
    vendas.setCliente(cliente);
    vendas.setProdutosQuantidade(produtosQuantidade);
    vendas.setFormaPagamento(formaPagamento);
    vendas.setValorTotal(valorTotal);

    // Aqui você pode adicionar lógica para salvar os dados em outro formato ou
    // local, se necessário
    // Por exemplo, salvar em um banco de dados ou enviar para um serviço web

    // Exibir mensagem de sucesso ou falha
    JOptionPane.showMessageDialog(vendasPrincipal, "Venda registrada com sucesso!", "Resultado",
        JOptionPane.INFORMATION_MESSAGE);
    vendasPrincipal.atualizarTabela(); // Atualizar tabela na interface, se necessário
  }
}
