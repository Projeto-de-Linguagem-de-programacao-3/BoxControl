package main.view.screens.TelasPrincipais;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.model.entity.DadosVendas;
import main.view.components.StyleGuide;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class VendasPrincipal extends JPanel {
  private DadosVendas dadosVendas;

  private JTextField Cliente;
  private JTextField ProdutosQuantidade;
  private JComboBox<String> formaPagamentoComboBox; // Alteração aqui
  private JTextField ValorTotal;

  public VendasPrincipal() {
    super();
    setBackground(StyleGuide.bgScreen);
    dadosVendas = new DadosVendas();
    configurarComponentes();
  }

  private void configurarComponentes() {
    setLayout(null);

    JLabel lblCliente = new JLabel("Nome do Cliente:");
    lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
    lblCliente.setBounds(20, 11, 120, 20);
    add(lblCliente);

    Cliente = new JTextField();
    Cliente.setBounds(20, 42, 200, 20);
    add(Cliente);

    JLabel lblProdutosQuantidade = new JLabel("Produtos & Quantidade:");
    lblProdutosQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
    lblProdutosQuantidade.setBounds(20, 73, 150, 20);
    add(lblProdutosQuantidade);

    ProdutosQuantidade = new JTextField();
    ProdutosQuantidade.setBounds(20, 104, 200, 20);
    add(ProdutosQuantidade);

    JLabel lblFormaPagamento = new JLabel("Forma de Pagamento:");
    lblFormaPagamento.setFont(new Font("Tahoma", Font.PLAIN, 14));
    lblFormaPagamento.setBounds(20, 135, 150, 20);
    add(lblFormaPagamento);

    String[] formasPagamento = { "À vista: 22% de desconto", "Cartão", "Fiado" };
    formaPagamentoComboBox = new JComboBox<>(formasPagamento); // Alteração aqui
    formaPagamentoComboBox.setBounds(20, 166, 200, 20); // Alteração aqui
    add(formaPagamentoComboBox); // Alteração aqui

    JLabel lblValorTotal = new JLabel("Valor Total:");
    lblValorTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
    lblValorTotal.setBounds(20, 197, 150, 20);
    add(lblValorTotal);

    ValorTotal = new JTextField();
    ValorTotal.setBounds(20, 228, 200, 20);
    add(ValorTotal);

    JButton btnSalvar = new JButton("Salvar em Texto");
    btnSalvar.setBounds(20, 259, 150, 20);
    btnSalvar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        salvarVenda();
      }
    });
    add(btnSalvar);
  }

  private void salvarVenda() {
    // Obter os dados inseridos pelo usuário
    String cliente = Cliente.getText();
    String produtosQuantidade = ProdutosQuantidade.getText();
    String formaPagamento = (String) formaPagamentoComboBox.getSelectedItem(); // Alteração aqui
    String valorTotal = ValorTotal.getText();

    // Definir os dados da venda na classe DadosVendas
    dadosVendas.setCliente(cliente);
    // Definir a lógica para converter a String de produtos & quantidade em um Map
    // dadosVendas.setProdutosQuantidade(produtosQuantidadeMap);
    dadosVendas.setFormaPagamento(formaPagamento);
    dadosVendas.setValorTotal(Double.parseDouble(valorTotal));

    // Chamar o método salvarTxt() para salvar os dados da venda
    String resultado = dadosVendas.salvarTxt();
    System.out.println(resultado);
  }
}