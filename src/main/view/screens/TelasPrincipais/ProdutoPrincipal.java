package main.view.screens.TelasPrincipais;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import main.controller.actions.ButtonProdutoSalvarListener;
import main.view.components.StyleGuide;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;

public class ProdutoPrincipal extends JPanel {
    private JTextField textNome;
    private JTextField textTipo;
    private JTextField textPrecoCompra;
    private JTextField textPrecoVenda;
    private JTextField textFabricante;
    private JFormattedTextField textValidade;
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
        JScrollPane scrollPane = new JScrollPane(getTabelaProduto());
        scrollPane.setMinimumSize(new Dimension(100, 500));
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
            btnSalvar = new JButton("Salvar Produto");
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
            textPrecoCompra = new JTextField();
            StyleGuide.formataComponente(textPrecoCompra);
        }
        return textPrecoCompra;
    }

    public JTextField getTextPrecoVenda() {
        if (textPrecoVenda == null) {
            textPrecoVenda = new JTextField();
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

    public JFormattedTextField getTextValidade() {
        if (textValidade == null) {
            try {
                MaskFormatter mascaraValidade = new MaskFormatter("##/##/####");
                textValidade = new JFormattedTextField(mascaraValidade);
                StyleGuide.formataComponente(textValidade);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return textValidade;
    }

    public JTextField getTextQuantidadeEstoque() {
        if (textQuantidadeEstoque == null) {
            textQuantidadeEstoque = new JTextField();
            StyleGuide.formataComponente(textQuantidadeEstoque);
        }
        return textQuantidadeEstoque;
    }

    public JTable getTabelaProduto() {
        if (tabelaProduto == null) {
            String[] titulos = { "ID", "Nome", "Tipo", "Preço Compra", "Preço Venda", "Fabricante", "Validade",
                    "Quantidade Estoque" };
            DefaultTableModel modelo = new DefaultTableModel(titulos, 0);
            tabelaProduto = new JTable(modelo);
        }
        return tabelaProduto;
    }
}
