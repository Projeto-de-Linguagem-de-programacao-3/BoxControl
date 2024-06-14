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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendasPrincipal extends JPanel {
  private JTextField textProdutosQuantidade;
  private CaixaProdutos caixaProdutos;
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

    /** constraints.gridx = 0;
    constraints.gridy = 4;
    constraints.gridwidth = 2;
    add(getCaixaProdutos(), constraints); */

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
    scrollPane.setMinimumSize(new Dimension(100,500));
    add(scrollPane, constraints);
  }

  public CaixaProdutos getCaixaProdutos() {
    if(caixaProdutos == null) {
      caixaProdutos = new CaixaProdutos();
    }
    return caixaProdutos;
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
      btnSalvar = new JButton("Salvar cliente");
      StyleGuide.formataComponente(btnSalvar);
    }
    return btnSalvar;
  }

 
  public JTable getTabelaCliente() {
    if (tabelaCliente == null) {
      String[] titulos = {"Cliente", "Produtos e Quantidade", "Forma de Pagamento", "Valor Total"};
      DefaultTableModel modelo = new DefaultTableModel(titulos, 0);
      tabelaCliente = new JTable(modelo);
      preencheVendasTable(modelo);
    }
    return tabelaCliente;
  }

  private void preencheVendasTable(DefaultTableModel modelo) {
        try {
      File file = new File("Vendas.txt");
      Scanner scanner = new Scanner(file);
      Object[] dadosLinha = new Object[5];
      int i = 0;
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        if (!line.isEmpty()) {
          if(line.startsWith("Informações da venda")) {
            continue;
          }
          String[] dados = line.split(":");
          dadosLinha[i] = dados[1];
          i++;
          System.out.println(dadosLinha);
          if(line.startsWith("Valor Total")) {
              modelo.addRow(dadosLinha);
              i = 0;
          }
        }
      }

      scanner.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    }

    public void atualizarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) getTabelaCliente().getModel();
        modelo.setRowCount(0);
        preencheVendasTable(modelo);
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

  public String[] carregarClientes() {

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
