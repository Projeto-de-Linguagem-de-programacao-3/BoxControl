package main.view.screens.TelasPrincipais;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import main.controller.actions.Excluir.ButtonExcluirPedidoListener;
import main.controller.actions.Inserir.ButtonPedidosSalvarListener;
import main.model.database.PedidoDatabase;
import main.model.database.ProdutoDatabase;
import main.model.entity.Produto;
import main.view.components.StyleGuide;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;

public class PedidosPrincipal extends JPanel {
    private JComboBox<Produto> textProduto;
    private JFormattedTextField textPrecoCompra;
    private JTextField textFabricante;
    private JFormattedTextField textValidade;
    private JFormattedTextField textQuantidade;

    private JLabel labelProduto;
    private JLabel labelPrecoCompra;
    private JLabel labelFabricante;
    private JLabel labelValidade;

    private JLabel labelQuantidade;
    ProdutoDatabase produtoDatabase = new ProdutoDatabase();

    private JButton btnSalvar;

    private JTable tabelaCliente;
    private JButton btnAtualizarProduto;
    private JButton btnDeletarPedido;

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
        constraints.gridwidth = 2; // Span 2 colunas
        add(getBtnSalvar(), constraints);
        ButtonPedidosSalvarListener buttonPedidosSalvarListener = new ButtonPedidosSalvarListener(this);
        btnSalvar.addActionListener(buttonPedidosSalvarListener);

        constraints.gridx = 2;
        constraints.gridy = 0;
        add(getBtnAtualizarProduto(), constraints);
        btnAtualizarProduto.addActionListener((ActionEvent e) -> {
            carregarProdutos();
        });

        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.gridheight = 9;
        JScrollPane scrollPane = new JScrollPane(getTabelaPedidos());
        scrollPane.setMinimumSize(new Dimension(100, 500));
        add(scrollPane, constraints);

        constraints.gridx = 2;
        constraints.gridy = 10;
        constraints.gridheight = 1;
        add(getBtnDeletarPedido(), constraints);
        ButtonExcluirPedidoListener buttonExcluirPedidoListener = new ButtonExcluirPedidoListener(this);
        btnDeletarPedido.addActionListener(buttonExcluirPedidoListener);

        atualizarTabela();
    }

    // Getters para elementos JLabel
    public JLabel getLabelProduto() {
        if (labelProduto == null) {
            labelProduto = new JLabel("Produto:");
            StyleGuide.formataComponente(labelProduto);
        }
        return labelProduto;
    }

    public JComboBox<Produto> getTextProduto() {
        if (textProduto == null) {
            textProduto = new JComboBox<Produto>();
            textProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
            carregarProdutos();
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

    public JFormattedTextField getTextPrecoCompra() {
        if (textPrecoCompra == null) {
            NumberFormatter numberFormatter = new NumberFormatter();
            numberFormatter.setValueClass(Double.class);
            numberFormatter.setAllowsInvalid(false);
            numberFormatter.setMinimum(0.0);
            textPrecoCompra = new JFormattedTextField(numberFormatter);
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

    public JLabel getLabelValidade() {
        if (labelValidade == null) {
            labelValidade = new JLabel("Validade:");
            StyleGuide.formataComponente(labelValidade);
        }
        return labelValidade;
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

    public JFormattedTextField getTextQuantidade() {
        if (textQuantidade == null) {
            NumberFormat numberFormat = NumberFormat.getNumberInstance();
            numberFormat.setGroupingUsed(false);
            NumberFormatter numberFormatter = new NumberFormatter(numberFormat);
            numberFormatter.setValueClass(Double.class);
            numberFormatter.setAllowsInvalid(false);
            numberFormatter.setMinimum(0.0);
            textQuantidade = new JFormattedTextField(numberFormatter);
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

    public JButton getBtnAtualizarProduto() {
        if(btnAtualizarProduto == null) {
            btnAtualizarProduto = new JButton("Atualizar lista de produtos");
            StyleGuide.formataComponente(btnAtualizarProduto);
        }
        return btnAtualizarProduto;
    }

    public JTable getTabelaPedidos() {
        if (tabelaCliente == null) {
            String[] titulos = { "ID", "Produto", "Preço de compra", "Fabricante", "Validade", "Quantidade" };
            DefaultTableModel modelo = new DefaultTableModel(titulos, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tabelaCliente = new JTable(modelo);
        }
        return tabelaCliente;
    }

    public JButton getBtnDeletarPedido() {
        if(btnDeletarPedido == null) {
            btnDeletarPedido = new JButton("Devolver Pedido");
            StyleGuide.formataComponente(btnDeletarPedido);
        }
        return btnDeletarPedido;
    }

    public void atualizarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) getTabelaPedidos().getModel();
        modelo.setRowCount(0); // Limpa a tabela antes de adicionar novos dados
        
        PedidoDatabase pedidoDatabase = new PedidoDatabase();
        List<Object[]> dadosCliente = pedidoDatabase.consultarPedidos();
        for (Object[] linha : dadosCliente) {
        modelo.addRow(linha);
        }
    }

    private void carregarProdutos() {
        List<Produto> produtos = produtoDatabase.listarProdutosComboBox();
        textProduto.removeAllItems();       
        for (Produto produto : produtos) {
            textProduto.addItem(produto);
        }
    }
}
