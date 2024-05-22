package main.view.screens.TelasPrincipais;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.*;

import main.controller.actions.ButtonPedidosSalvarListener;
import main.view.components.StyleGuide;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PedidosPrincipal extends JPanel {
    private JComboBox<String> textProduto;
    private JTextField textPrecoCompra;
    private JTextField textFabricante;
    private JTextField textValidade;
    private JTextField textQuantidade;

    private JLabel labelProduto;
    private JLabel labelPrecoCompra;
    private JLabel labelFabricante;
    private JLabel labelValidade;
    private JLabel labelQuantidade;

    private JButton btnSalvar;

    private JTable tabelaCliente;

    public PedidosPrincipal() {
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
        add(getLabelProduto(), constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(getTextProduto(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(getLabelPrecoCompra(), constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        add(getTextPrecoCompra(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        add(getLabelFabricante(), constraints);
        constraints.gridx = 0;
        constraints.gridy = 5;
        add(getTextFabricante(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        add(getLabelValidade(), constraints);
        constraints.gridx = 0;
        constraints.gridy = 7;
        add(getTextValidade(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 8;
        add(getLabelQuantidade(), constraints);
        constraints.gridx = 0;
        constraints.gridy = 9;
        add(getTextQuantidade(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 10;
        constraints.gridwidth = 2; // ocupa 2 colunas
        add(getBtnSalvar(), constraints);
        ButtonPedidosSalvarListener buttonPedidosSalvarListener = new ButtonPedidosSalvarListener(this);
        btnSalvar.addActionListener(buttonPedidosSalvarListener);

        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.gridheight = 10;
        JScrollPane scrollPane = new JScrollPane(getTabelaCliente());
        scrollPane.setMinimumSize(new Dimension(100, 500));
        add(scrollPane, constraints);
    }

    public JLabel getLabelProduto() {
        if (labelProduto == null) {
            labelProduto = new JLabel("Produto:");
            StyleGuide.formataComponente(labelProduto);
        }
        return labelProduto;
    }

    public JComboBox<String> getTextProduto() {
        if (textProduto == null) {
            textProduto = new JComboBox<>(carregarProdutos());
            textProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
        }
        return textProduto;
    }// Getters for JLabel elements

    public JLabel getLabelPrecoCompra() {
        if (labelPrecoCompra == null) {
            labelPrecoCompra = new JLabel("Preço de Compra:");
            StyleGuide.formataComponente(labelPrecoCompra);
        }
        return labelPrecoCompra;
    }

    public JTextField getTextPrecoCompra() {
        if (textPrecoCompra == null) {
            textPrecoCompra = new JTextField();

            StyleGuide.formataComponente(textPrecoCompra);
        }
        return textPrecoCompra;
    }

    public JLabel getLabelFabricante() {
        if (labelFabricante == null) {
            labelFabricante = new JLabel("Fabricante:");
            StyleGuide.formataComponente(labelFabricante);
        }
        return labelFabricante;
    }

    public JTextField getTextFabricante() {
        if (textFabricante == null) {
            textFabricante = new JTextField();
            StyleGuide.formataComponente(textFabricante);
        }
        return textFabricante;
    }

    public JTextField getTextValidade() {
        if (textValidade == null) {
            textValidade = new JTextField();
            try {
                MaskFormatter mascaraValidade = new MaskFormatter("##/##/####");
                textValidade = new JFormattedTextField(mascaraValidade);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            StyleGuide.formataComponente(textValidade);
        }
        return textValidade;
    }

    public JTextField getTextQuantidade() {
        if (textQuantidade == null) {
            textQuantidade = new JTextField();
            StyleGuide.formataComponente(textQuantidade);
        }
        return textQuantidade;
    }

    public JLabel getLabelQuantidade() {
        if (labelQuantidade == null) {
            labelQuantidade = new JLabel("Quantidade:");
            StyleGuide.formataComponente(labelQuantidade);
        }
        return labelQuantidade;
    }

    public JLabel getLabelValidade() {
        if (labelValidade == null) {
            labelValidade = new JLabel("Validade:");

            StyleGuide.formataComponente(labelValidade);
        }
        return labelValidade;
    }

    public JButton getBtnSalvar() {
        if (btnSalvar == null) {
            btnSalvar = new JButton("Salvar Pedido");
            StyleGuide.formataComponente(btnSalvar);
        }
        return btnSalvar;
    }

    public JTable getTabelaCliente() {
        if (tabelaCliente == null) {
            String[] titulos = {"Produto", "Preço de compra", "Fabricante", "Validade", "Quantidade"};
            DefaultTableModel modelo = new DefaultTableModel(titulos, 0);
            tabelaCliente = new JTable(modelo);
            preenchePedidosTabela(modelo);
        }
        return tabelaCliente;
    }

    private void preenchePedidosTabela(DefaultTableModel modelo) {

    try {
      File file = new File("pedido.txt");
      Scanner scanner = new Scanner(file);
      Object[] dadosLinha = new Object[5];
      int i = 0;
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        if (!line.isEmpty()) {
          if(line.startsWith("Pedido de Produto")) {
            continue;
          }
          String[] dados = line.split(":");
          dadosLinha[i] = dados[1];
          i++;
          System.out.println(dadosLinha);
          if(line.startsWith("Quantidade")) {
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
    preenchePedidosTabela(modelo);
  }

    private String[] carregarProdutos() {

    List<String> clientes = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader("Produtos.txt"))) {
      String line;
      String combination = "";
      while ((line = br.readLine()) != null) {
        if (line.startsWith("ID: ")) {
            combination = line.substring(4);
        }
        if (line.startsWith("Nome: ")) {
            combination += line.substring(5);
            clientes.add(combination); // Adiciona apenas o nome do cliente (após "Nome: ")
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return clientes.toArray(new String[0]);

  }

}
