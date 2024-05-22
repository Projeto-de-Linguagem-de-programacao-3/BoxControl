package main.view.screens.TelasPrincipais;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.*;
import main.controller.actions.ButtonProdutoSalvarListener;
import main.view.components.StyleGuide;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Scanner;

public class ProdutoPrincipal extends JPanel {
    private JTextField textNome;
    private JTextField textTipo;
    private JTextField textPrecoCompra;
    private JTextField textPrecoVenda;
    private JTextField textFabricante;
    private JTextField textValidade;
    private JTextField textQuantidadeEstoque;

    private JLabel labelNome;
    private JLabel labelTipo;
    private JLabel labelPrecoCompra;
    private JLabel labelPrecoVenda;
    private JLabel labelFabricante;
    private JLabel labelValidade;
    private JLabel labelQuantidadeEstoque;

    private JButton btnSalvar;

    private JTable tabelaProduto;

    public ProdutoPrincipal() {
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
        add(getLabelNome(), constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(getTextNome(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(getLabelTipo(), constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        add(getTextTipo(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        add(getLabelPrecoCompra(), constraints);
        constraints.gridx = 0;
        constraints.gridy = 5;
        add(getTextPrecoCompra(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        add(getLabelPrecoVenda(), constraints);
        constraints.gridx = 0;
        constraints.gridy = 7;
        add(getTextPrecoVenda(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 8;
        add(getLabelFabricante(), constraints);
        constraints.gridx = 0;
        constraints.gridy = 9;
        add(getTextFabricante(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 10;
        add(getLabelValidade(), constraints);
        constraints.gridx = 0;
        constraints.gridy = 11;
        add(getTextValidade(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 12;
        add(getLabelQuantidadeEstoque(), constraints);
        constraints.gridx = 0;
        constraints.gridy = 13;
        add(getTextQuantidadeEstoque(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 14;
        constraints.gridwidth = 2; // ocupa 2 colunas
        add(getBtnSalvar(), constraints);
        ButtonProdutoSalvarListener buttonProdutoSalvarListener = new ButtonProdutoSalvarListener(this);
        btnSalvar.addActionListener(buttonProdutoSalvarListener);

        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.gridheight = 14;
        JScrollPane scrollPane = new JScrollPane(getTabelaCliente());
        scrollPane.setMinimumSize(new Dimension(100,500));
        add(scrollPane, constraints);
    }

    public JLabel getLabelNome() {
        if (labelNome == null) {
            labelNome = new JLabel("Nome:");
            StyleGuide.formataComponente(labelNome);
        }
        return labelNome;
    }

    public JLabel getLabelTipo() {
        if (labelTipo == null) {
            labelTipo = new JLabel("Tipo:");
            StyleGuide.formataComponente(labelTipo);
        }
        return labelTipo;
    }

    public JLabel getLabelPrecoCompra() {
        if (labelPrecoCompra == null) {
            labelPrecoCompra = new JLabel("Preço de Compra:");
            StyleGuide.formataComponente(labelPrecoCompra);
        }
        return labelPrecoCompra;
    }

    public JLabel getLabelPrecoVenda() {
        if (labelPrecoVenda == null) {
            labelPrecoVenda = new JLabel("Preço de Venda:");
            StyleGuide.formataComponente(labelPrecoVenda);
        }
        return labelPrecoVenda;
    }

    public JLabel getLabelFabricante() {
        if (labelFabricante == null) {
            labelFabricante = new JLabel("Fabricante:");
            StyleGuide.formataComponente(labelFabricante);
        }
        return labelFabricante;
    }

    public JLabel getLabelValidade() {
        if (labelValidade == null) {
            labelValidade = new JLabel("Validade:");
            StyleGuide.formataComponente(labelValidade);
        }
        return labelValidade;
    }

    public JLabel getLabelQuantidadeEstoque() {
        if (labelQuantidadeEstoque == null) {
            labelQuantidadeEstoque = new JLabel("Quantidade em Estoque:");
            StyleGuide.formataComponente(labelQuantidadeEstoque);
        }
        return labelQuantidadeEstoque;
    }

    public JButton getBtnSalvar() {
        if (btnSalvar == null) {
            btnSalvar = new JButton("Salvar cliente");
            StyleGuide.formataComponente(btnSalvar);
        }
        return btnSalvar;
    }

    public JTextField getTextNome() {
        if (textNome == null) {
            textNome = new JTextField();
            StyleGuide.formataComponente(textNome);
        }
        return textNome;
    }

    public JTextField getTextTipo() {
        if (textTipo == null) {
            textTipo = new JTextField();
            StyleGuide.formataComponente(textTipo);
        }
        return textTipo;
    }

    public JTextField getTextPrecoCompra() {
        if (textPrecoCompra == null) {
            NumberFormat numberFormat = NumberFormat.getNumberInstance();
            numberFormat.setGroupingUsed(false);
            NumberFormatter numberFormatter = new NumberFormatter(numberFormat);
            numberFormatter.setValueClass(Double.class);
            numberFormatter.setAllowsInvalid(false); // Não permite caracteres não numéricos
            numberFormatter.setMinimum(0.0); // Define um valor mínimo
            textPrecoCompra = new JFormattedTextField(numberFormatter);
            StyleGuide.formataComponente(textPrecoCompra);
        }
        return textPrecoCompra;
    }

    public JTextField getTextPrecoVenda() {
        if (textPrecoVenda == null) {
            NumberFormat numberFormat = NumberFormat.getNumberInstance();
            numberFormat.setGroupingUsed(false);
            NumberFormatter numberFormatter = new NumberFormatter(numberFormat);
            numberFormatter.setValueClass(Double.class);
            numberFormatter.setAllowsInvalid(false); // Não permite caracteres não numéricos
            numberFormatter.setMinimum(0.0); // Define um valor mínimo
            textPrecoVenda = new JFormattedTextField(numberFormatter);
            StyleGuide.formataComponente(textPrecoVenda);
        }
        return textPrecoVenda;
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
            StyleGuide.formataComponente(textValidade);
            try {
                MaskFormatter mascaraValidade = new MaskFormatter("##/##/####");
                textValidade = new JFormattedTextField(mascaraValidade);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return textValidade;
    }

    public JTextField getTextQuantidadeEstoque() {
        if (textQuantidadeEstoque == null) {
            NumberFormat numberFormat = NumberFormat.getNumberInstance();
            numberFormat.setGroupingUsed(false);
            NumberFormatter numberFormatter = new NumberFormatter(numberFormat);
            numberFormatter.setValueClass(Double.class);
            numberFormatter.setAllowsInvalid(false); // Não permite caracteres não numéricos
            numberFormatter.setMinimum(0.0); // Define um valor mínimo

            textQuantidadeEstoque = new JFormattedTextField(numberFormatter);
            StyleGuide.formataComponente(textQuantidadeEstoque);
        }
        return textQuantidadeEstoque;
    }

    public JTable getTabelaCliente() {
        if (tabelaProduto == null) {
            String[] titulos = {"ID", "Nome", "Tipo", "Preço Compra", "Preço Venda", "Fabricante", "Validade", "Quantidade Estoque"};
            DefaultTableModel modelo = new DefaultTableModel(titulos, 0);
            tabelaProduto = new JTable(modelo);
            preencheProdutoTable(modelo);
        }
        return tabelaProduto;
    }

    private void preencheProdutoTable(DefaultTableModel modelo) {
        try {
      File file = new File("Produtos.txt");
      Scanner scanner = new Scanner(file);
      Object[] dadosLinha = new Object[8];
      int i = 0;
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        if (!line.isEmpty()) {
          if(line.startsWith("Produto")) {
            continue;
          }
          String[] dados = line.split(":");
          dadosLinha[i] = dados[1];
          i++;
          System.out.println(dadosLinha);
          if(line.startsWith("Quantidade em Estoque")) {
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
        preencheProdutoTable(modelo);
    }
}
