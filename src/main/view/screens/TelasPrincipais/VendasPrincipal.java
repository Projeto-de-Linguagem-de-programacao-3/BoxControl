package main.view.screens.TelasPrincipais;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import main.controller.actions.ButtonVendasSalvarListener;
import main.view.components.CaixaProdutos;
import main.view.components.StyleGuide;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.NumberFormat;
// import java.util.ArrayList;
import java.util.HashMap;
// import java.util.List;
import java.util.Map;

public class VendasPrincipal extends JPanel {
  private JTextField textProdutosQuantidade;
  // private CaixaProdutos caixaProdutos;
  private JComboBox<String> comboBoxCliente;
  private JComboBox<String> comboBoxFormaPagamento;
  private JTextField textValorTotal;

  private JLabel labelProdutosQuantidade;
  private JLabel labelCliente;
  private JLabel labelFormaPagamento;
  private JLabel labelValorTotal;

  private JButton btnSalvar;

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
    constraints.gridy = 5;
    add(getLabelFormaPagamento(), constraints);
    constraints.gridx = 0;
    constraints.gridy = 6;
    add(getComboBoxFormaPagamento(), constraints);

    constraints.gridx = 0;
    constraints.gridy = 7;
    add(getLabelValorTotal(), constraints);
    constraints.gridx = 0;
    constraints.gridy = 8;
    add(getTextValorTotal(), constraints);

    constraints.gridx = 0;
    constraints.gridy = 9;
    constraints.gridwidth = 2; // ocupa 2 colunas
    add(getBtnSalvar(), constraints);
    ButtonVendasSalvarListener buttonVendasSalvarListener = new ButtonVendasSalvarListener(this);
    btnSalvar.addActionListener(buttonVendasSalvarListener);

    constraints.gridx = 2;
    constraints.gridy = 0;
    constraints.gridwidth = 2;
    constraints.gridheight = 9;
    JScrollPane scrollPane = new JScrollPane(getTabelaCliente());
    scrollPane.setMinimumSize(new Dimension(100, 500));
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
      comboBoxCliente = new JComboBox<>();
      StyleGuide.formataComponente(comboBoxCliente);
    }
    return comboBoxCliente;
  }

  public JComboBox<String> getComboBoxFormaPagamento() {
    if (comboBoxFormaPagamento == null) {
      String[] formasPagamento = { "À vista: 2% de desconto", "Cartão", "Fiado" };
      comboBoxFormaPagamento = new JComboBox<>(formasPagamento);
      StyleGuide.formataComponente(comboBoxFormaPagamento);
    }
    return comboBoxFormaPagamento;
  }

  public JTextField getTextValorTotal() {
    if (textValorTotal == null) {
      NumberFormat numberFormat = NumberFormat.getNumberInstance();
      numberFormat.setGroupingUsed(false);
      NumberFormatter numberFormatter = new NumberFormatter(numberFormat);
      numberFormatter.setValueClass(Double.class);
      numberFormatter.setAllowsInvalid(false); // Não permite caracteres não numéricos
      numberFormatter.setMinimum(0.0); // Define um valor mínimo
      textValorTotal = new JFormattedTextField(numberFormatter);
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
      btnSalvar = new JButton("Salvar venda");
      StyleGuide.formataComponente(btnSalvar);
    }
    return btnSalvar;
  }

  public JTable getTabelaCliente() {
    if (tabelaCliente == null) {
      String[] titulos = { "Cliente", "Produtos e Quantidade", "Forma de Pagamento", "Valor Total" };
      DefaultTableModel modelo = new DefaultTableModel(titulos, 0);
      tabelaCliente = new JTable(modelo);
      // Call method to populate table (optional)
      // preencheVendasTable(modelo);
    }
    return tabelaCliente;
  }

  public void atualizarTabela() {
    DefaultTableModel modelo = (DefaultTableModel) getTabelaCliente().getModel();
    modelo.setRowCount(0); // Limpa todas as linhas existentes na tabela
    // Preencha a tabela novamente com os dados atualizados (se necessário)
    // preencheVendasTable(modelo);
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

  public CaixaProdutos getCaixaProdutos() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getCaixaProdutos'");
  }
}
