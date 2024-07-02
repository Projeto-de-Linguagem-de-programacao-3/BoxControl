package main.controller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JOptionPane;

import com.mysql.cj.xdevapi.Client;

import main.model.database.VendasDatabase;
import main.model.entity.Cliente;
import main.model.entity.Produto;
import main.model.entity.Vendas;
import main.view.screens.TelasPrincipais.VendasPrincipal;

public class ButtonVendasSalvarListener implements ActionListener {
  private VendasPrincipal vendasPrincipal;

  public ButtonVendasSalvarListener(VendasPrincipal vendasPrincipal) {
    this.vendasPrincipal = vendasPrincipal;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // Obter os dados inseridos pelo usuário
    Cliente cliente = (Cliente) vendasPrincipal.getComboBoxCliente().getSelectedItem();
    Produto produto = (Produto) vendasPrincipal.getComboBoxProdutos().getSelectedItem();
    String quantidade = vendasPrincipal.getTextQuantidade().getText();
    String formaPagamento = (String) vendasPrincipal.getComboBoxFormaPagamento().getSelectedItem();
    String valorTotalTexto = vendasPrincipal.getTextValorTotal().getText();
    Double valorTotal = 0.00;
    valorTotalTexto = valorTotalTexto.replace(",", ".");
    // Converter o texto em um Double para o valor total
    try {
      valorTotal = Double.parseDouble(valorTotalTexto);
    } catch (Exception error) {
      JOptionPane.showMessageDialog(vendasPrincipal, "O cliente não pode comprar fiado!",
              "Erro de validação", JOptionPane.ERROR_MESSAGE);
      return;
    }
    int quantidadeInt = Integer.parseInt(quantidade);
    if(quantidadeInt > produto.getQuantidadeEstoque()) {
      JOptionPane.showMessageDialog(vendasPrincipal, "Estoque insuficiente! O pedido pode ter no máximo: " + produto.getQuantidadeEstoque(),
              "Erro de validação", JOptionPane.ERROR_MESSAGE);
      return;
    }

    // Criar objeto DadosVendas e definir os dados da venda
    Vendas vendas = new Vendas();
    vendas.setCliente(cliente);
    vendas.setProduto(produto);
    vendas.SetQuantidade(quantidade);
    vendas.setFormaPagamento(formaPagamento);
    vendas.setValorTotal(valorTotal);

    VendasDatabase vendasDatabase = new VendasDatabase();
    vendasDatabase.cadastrarVenda(vendas);
    System.out.println(vendas.getId());
    vendasDatabase.cadastrarItemVenda(vendas);

    // Aqui você pode adicionar lógica para salvar os dados em outro formato ou
    // local, se necessário
    // Por exemplo, salvar em um banco de dados ou enviar para um serviço web

    // Exibir mensagem de sucesso ou falha
    JOptionPane.showMessageDialog(vendasPrincipal, "Venda registrada com sucesso!", "Resultado",
        JOptionPane.INFORMATION_MESSAGE);
    vendasPrincipal.atualizarTabela(); // Atualizar tabela na interface, se necessário
  }
}
