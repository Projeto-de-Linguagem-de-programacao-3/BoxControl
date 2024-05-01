package main.view.screens.TelasPrincipais;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.model.entity.DadosVendas;
import main.view.components.StyleGuide;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VendasPrincipal extends JPanel {
  private DadosVendas dadosVendas;

  private JTextField Cliente;
  private JTextField ProdutosQuantidade;
  private JTextField FormaPagamento;
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
    lblCliente.setBounds(20, 20, 120, 20);
    add(lblCliente);

    Cliente = new JTextField();
    Cliente.setBounds(150, 20, 200, 20);
    add(Cliente);

    JLabel lblProdutosQuantidade = new JLabel("Produtos & Quantidade:");
    lblProdutosQuantidade.setBounds(20, 50, 150, 20);
    add(lblProdutosQuantidade);

    ProdutosQuantidade = new JTextField();
    ProdutosQuantidade.setBounds(150, 50, 200, 20);
    add(ProdutosQuantidade);

    JLabel lblFormaPagamento = new JLabel("Forma de Pagamento:");
    lblFormaPagamento.setBounds(20, 80, 150, 20);
    add(lblFormaPagamento);

    FormaPagamento = new JTextField();
    FormaPagamento.setBounds(150, 80, 200, 20);
    add(FormaPagamento);

    JLabel lblValorTotal = new JLabel("Valor Total:");
    lblValorTotal.setBounds(20, 110, 150, 20);
    add(lblValorTotal);

    ValorTotal = new JTextField();
    ValorTotal.setBounds(150, 110, 200, 20);
    add(ValorTotal);

    JButton btnSalvar = new JButton("Salvar em Texto");
    btnSalvar.setBounds(150, 140, 150, 30);
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
    String formaPagamento = FormaPagamento.getText();
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
