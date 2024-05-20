package main.view.screens.TelasPrincipais;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import main.controller.actions.ButtonVendasSalvarListener;
import main.view.components.StyleGuide;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendasPrincipal extends JPanel {
  private JTextField textProdutosQuantidade;
  private JComboBox<String> comboBoxCliente;
  private JComboBox<String> comboBoxFormaPagamento;
  private JTextField textValorTotal;

  private JLabel labelProdutosQuantidade;
  private JLabel labelCliente;
  private JLabel labelFormaPagamento;
  private JLabel labelValorTotal;

  JButton btnSalvar;

  private JTable tabelaCliente;

  public VendasPrincipal() {
    super();
    setBackground(StyleGuide.bgScreen);
    setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.weightx = 1;
    constraints.weighty = 1;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(10, 10, 10, 10);

    constraints.gridx = 0;
    constraints.gridy = 0;
    add(getLabelCliente(), constraints);
    constraints.gridx = 0;
    constraints.gridy = 1;
    add(getComboBoxCliente(), constraints);

    constraints.gridx = 0;
    constraints.gridy = 2;
    add(getLabelProdutosQuantidade(), constraints);
    constraints.gridx = 0;
    constraints.gridy = 3;
    add(getTextProdutosQuantidade(), constraints);

    constraints.gridx = 0;
    constraints.gridy = 4;
    add(getLabelFormaPagamento(), constraints);
    constraints.gridx = 0;
    constraints.gridy = 5;
    add(getComboBoxFormaPagamento(), constraints);

    constraints.gridx = 0;
    constraints.gridy = 6;
    add(getLabelValorTotal(), constraints);
    constraints.gridx = 0;
    constraints.gridy = 7;
    add(getTextValorTotal(), constraints);

    constraints.gridx = 0;
    constraints.gridy = 8;
    constraints.gridwidth = 2; // ocupa 2 colunas
    add(getBtnSalvar(), constraints);
    ButtonVendasSalvarListener buttonVendasSalvarListener = new ButtonVendasSalvarListener(this);
    btnSalvar.addActionListener(buttonVendasSalvarListener);

    constraints.gridx = 2;
    constraints.gridy = 0;
    constraints.gridwidth = 2;
    constraints.gridheight = 5;
    JScrollPane scrollPane = new JScrollPane(getTabelaCliente());
    add(scrollPane, constraints);
  }

  public JTextField getTextProdutosQuantidade() {
    if (textProdutosQuantidade == null) {
      textProdutosQuantidade = new JTextField();
      StyleGuide.formataComponente(textProdutosQuantidade);
    }
    return textProdutosQuantidade;
  }

  public JComboBox<String> getComboBoxCliente() {
    if (comboBoxCliente == null) {
      comboBoxCliente = new JComboBox<>(carregarClientes());
      StyleGuide.formataComponente(comboBoxCliente);
    }
    return comboBoxCliente;
  }

  public JComboBox<String> getComboBoxFormaPagamento() {
    if (comboBoxFormaPagamento == null) {
      String[] formasPagamento = { "À vista: 22% de desconto", "Cartão", "Fiado" };
      comboBoxFormaPagamento = new JComboBox<>(formasPagamento);
      StyleGuide.formataComponente(comboBoxFormaPagamento);
    }
    return comboBoxFormaPagamento;
  }

  public JTextField getTextValorTotal() {
    if (textValorTotal == null) {
      textValorTotal = new JTextField();
      StyleGuide.formataComponente(textValorTotal);
    }
    return textValorTotal;
  }

  public JLabel getLabelProdutosQuantidade() {
    if (labelProdutosQuantidade == null) {
      labelProdutosQuantidade = new JLabel("Produtos & Quantidade:");
      StyleGuide.formataComponente(labelProdutosQuantidade);
    }
    return labelProdutosQuantidade;
  }

  public JLabel getLabelCliente() {
    if (labelCliente == null) {
      labelCliente = new JLabel("Cliente:");
      StyleGuide.formataComponente(labelCliente);
    }
    return labelCliente;
  }

  public JLabel getLabelFormaPagamento() {
    if (labelFormaPagamento == null) {
      labelFormaPagamento = new JLabel("Forma de Pagamento:");
      StyleGuide.formataComponente(labelFormaPagamento);
    }
    return labelFormaPagamento;
  }

  public JLabel getLabelValorTotal() {
    if (labelValorTotal == null) {
      labelValorTotal = new JLabel("Valor Total:");
      StyleGuide.formataComponente(labelValorTotal);
    }
    return labelValorTotal;
  }

  public JButton getBtnSalvar() {
    if (btnSalvar == null) {
      btnSalvar = new JButton("Salvar cliente");
      StyleGuide.formataComponente(btnSalvar);
    }
    return btnSalvar;
  }

 
  public JTable getTabelaCliente() {
    if (tabelaCliente == null) {
      DefaultTableModel modelo = new DefaultTableModel();
      modelo.addColumn("Cliente", new Class[] { String.class });
      modelo.addColumn("Produtos e Quantidade", new Class[] { String.class });
      modelo.addColumn("Forma de Pagamento", new Class[] { String.class });
      modelo.addColumn("Valor Total", new Class[] { String.class });
      tabelaCliente = new JTable(modelo);
    }
    return tabelaCliente;
  }

  public Map<String, Integer> converterParaMapa(String texto) {
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
        if (line.startsWith("Nome: ")) {
          clientes.add(line.substring(6)); // Adiciona apenas o nome do cliente (após "Nome: ")
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return clientes.toArray(new String[0]);

  }
}
