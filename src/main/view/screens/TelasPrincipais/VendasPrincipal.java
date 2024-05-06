package main.view.screens.TelasPrincipais;

import javax.swing.*;

import main.model.entity.DadosVendas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendasPrincipal extends JPanel {
  private JTextField ProdutosQuantidade;
  private JComboBox<String> clienteComboBox;
  private JComboBox<String> formaPagamentoComboBox;
  private JTextField ValorTotal;

  public VendasPrincipal() {
    super();
    configurarComponentes();
  }

  private void configurarComponentes() {
    setLayout(null);

    JLabel lblCliente = new JLabel("Nome do Cliente:");
    lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
    lblCliente.setBounds(20, 11, 120, 20);
    add(lblCliente);

    clienteComboBox = new JComboBox<>(carregarClientes());
    clienteComboBox.setBounds(20, 42, 200, 20);
    add(clienteComboBox);

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
    formaPagamentoComboBox = new JComboBox<>(formasPagamento);
    formaPagamentoComboBox.setBounds(20, 166, 200, 20);
    add(formaPagamentoComboBox);

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
    String cliente = (String) clienteComboBox.getSelectedItem();
    String produtosQuantidadeTexto = ProdutosQuantidade.getText(); // Aqui você precisa converter o texto em um mapa de
                                                                   // produtos e quantidades
    String formaPagamento = (String) formaPagamentoComboBox.getSelectedItem();
    String valorTotalTexto = ValorTotal.getText(); // Aqui você precisa converter o texto em um Double

    // Aqui você precisa converter o texto em um mapa de produtos e quantidades
    Map<String, Integer> produtosQuantidade = converterParaMapa(produtosQuantidadeTexto);

    // Aqui você precisa converter o texto em um Double
    Double valorTotal = Double.parseDouble(valorTotalTexto);

    DadosVendas vendas = new DadosVendas();

    vendas.setCliente(cliente);
    vendas.setProdutosQuantidade(produtosQuantidade);
    vendas.setFormaPagamento(formaPagamento);
    vendas.setValorTotal(valorTotal);

    String resultado = vendas.salvarTxt();
    System.out.println(resultado);

    // Aqui você pode salvar os dados da venda
  }

  private Map<String, Integer> converterParaMapa(String texto) {
    Map<String, Integer> mapa = new HashMap<>();

    // Divida o texto em pares de chave e valor
    String[] pares = texto.split(",");

    // Para cada par de chave e valor, divida novamente para obter o nome do produto
    // e sua quantidade
    for (String par : pares) {
      String[] partes = par.trim().split(":");
      if (partes.length == 2) {
        String produto = partes[0].trim();
        int quantidade = Integer.parseInt(partes[1].trim());
        mapa.put(produto, quantidade);
      }
    }

    return mapa;
  }

  private String[] carregarClientes() {
    List<String> clientes = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader("cliente.txt"))) {
      String line;
      while ((line = br.readLine()) != null) {
        if (line.startsWith("Nome:")) {
          clientes.add(line.substring(6)); // Adiciona apenas o nome do cliente (após "Nome: ")
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return clientes.toArray(new String[0]);
  }
}
