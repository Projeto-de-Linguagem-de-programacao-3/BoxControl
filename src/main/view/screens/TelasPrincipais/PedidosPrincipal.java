package main.view.screens.TelasPrincipais;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import main.controller.actions.ButtonPedidosSalvarListener;
import main.view.components.StyleGuide;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class PedidosPrincipal extends JPanel {
    private JComboBox<String> textProduto;
    private JTextField textPrecoCompra;
    private JTextField textFabricante;
    private JFormattedTextField textValidade;
    private JTextField textQuantidade;

    private JLabel labelProduto;
    private JLabel labelPrecoCompra;
    private JLabel labelFabricante;
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

        // Adicionando componentes ao painel usando GridBagConstraints
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
        add(getTextValidade(), constraints);
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
        constraints.gridwidth = 2; // Span 2 colunas
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

    // Getters para elementos JLabel
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
    }

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

    public JFormattedTextField getTextValidade() {
        if (textValidade == null) {
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

    public JButton getBtnSalvar() {
        if (btnSalvar == null) {
            btnSalvar = new JButton("Salvar Pedido");
            StyleGuide.formataComponente(btnSalvar);
        }
        return btnSalvar;
    }

    public JTable getTabelaCliente() {
        if (tabelaCliente == null) {
            String[] titulos = { "Produto", "Preço de compra", "Fabricante", "Validade", "Quantidade" };
            DefaultTableModel modelo = new DefaultTableModel(titulos, 0);
            tabelaCliente = new JTable(modelo);
        }
        return tabelaCliente;
    }

    private String[] carregarProdutos() {
        List<String> produtos = new ArrayList<>();
        // Lógica para carregar produtos (exemplo genérico)
        produtos.add("Produto 1");
        produtos.add("Produto 2");
        produtos.add("Produto 3");
        return produtos.toArray(new String[0]);
    }
}
