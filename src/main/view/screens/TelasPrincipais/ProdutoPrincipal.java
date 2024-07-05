package main.view.screens.TelasPrincipais;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import main.controller.actions.Editar.ButtonEditarProdutoListener;
import main.controller.actions.Inserir.ButtonProdutoSalvarListener;
import main.model.database.ProdutoDatabase;
import main.view.components.StyleGuide;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;

public class ProdutoPrincipal extends JPanel {
    private JTextField textNome;
    private JTextField textTipo;
    private JFormattedTextField textPrecoCompra;
    private JFormattedTextField textPrecoVenda;
    private JTextField textFabricante;
    private JFormattedTextField textValidade;
    private JFormattedTextField textQuantidadeEstoque;

    private JLabel labelNome;
    private JLabel labelTipo;
    private JLabel labelPrecoCompra;
    private JLabel labelPrecoVenda;
    private JLabel labelFabricante;
    private JLabel labelValidade;
    private JLabel labelQuantidadeEstoque;

    private JButton btnSalvar;
    private JButton btnAtualizarTabela;
    private JButton btnEditarProduto;

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
        add(getBtnAtualizarTabela(), constraints);
        btnAtualizarTabela.addActionListener((ActionEvent e) -> {
            atualizarTabela();
        });

        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.gridheight = 13;
        JScrollPane scrollPane = new JScrollPane(getTabelaProduto());
        scrollPane.setMinimumSize(new Dimension(100, 500));
        add(scrollPane, constraints);
        getTabelaProduto().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = getTabelaProduto().getSelectedRow();
                if (selectedRow != -1) {
                    Object id = getTabelaProduto().getValueAt(selectedRow, 0);
                    Object nome = getTabelaProduto().getValueAt(selectedRow, 1);
                    Object tipo = getTabelaProduto().getValueAt(selectedRow, 2);
                    Object precoCompra = getTabelaProduto().getValueAt(selectedRow, 3);
                    Object precoVenda = getTabelaProduto().getValueAt(selectedRow, 4);
                    Object fabricante = getTabelaProduto().getValueAt(selectedRow, 5);
                    Object validade = getTabelaProduto().getValueAt(selectedRow, 6);
                    Object quantidade = getTabelaProduto().getValueAt(selectedRow, 7);
                    
                    // Exemplo de preenchimento dos campos (adaptar conforme seus campos)
                    getTextNome().setText(nome.toString());
                    getTextTipo().setText(tipo.toString());
                    getTextPrecoCompra().setText(precoCompra.toString());
                    getTextPrecoVenda().setText(precoVenda.toString());
                    getTextFabricante().setText(fabricante.toString());
                    getTextValidade().setText(validade.toString());
                    getTextQuantidadeEstoque().setText(quantidade.toString());
                }
            }
        });

        constraints.gridx = 2;
        constraints.gridy = 14;
        constraints.gridheight = 1;
        add(getBtnEditarProduto(),constraints);
        ButtonEditarProdutoListener buttonEditarProdutoListener = new ButtonEditarProdutoListener(this);
        btnEditarProduto.addActionListener(buttonEditarProdutoListener);
        atualizarTabela();
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

    public JFormattedTextField getTextPrecoCompra() {
        if (textPrecoCompra == null) {
            NumberFormat numberFormat = NumberFormat.getNumberInstance();
            numberFormat.setGroupingUsed(false);
            NumberFormatter numberFormatter = new NumberFormatter(numberFormat);
            numberFormatter.setValueClass(Double.class);
            numberFormatter.setAllowsInvalid(false);
            numberFormatter.setMinimum(0.0);
            textPrecoCompra = new JFormattedTextField(numberFormatter);
            StyleGuide.formataComponente(textPrecoCompra);
        }
        return textPrecoCompra;
    }

    public JFormattedTextField getTextPrecoVenda() {
        if (textPrecoVenda == null) {
            NumberFormat numberFormat = NumberFormat.getNumberInstance();
            numberFormat.setGroupingUsed(false);
            NumberFormatter numberFormatter = new NumberFormatter(numberFormat);
            numberFormatter.setValueClass(Double.class);
            numberFormatter.setAllowsInvalid(false);
            numberFormatter.setMinimum(0.0);
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

    public JFormattedTextField getTextQuantidadeEstoque() {
        if (textQuantidadeEstoque == null) {
            NumberFormatter numberFormatter = new NumberFormatter();
            numberFormatter.setValueClass(Double.class);
            numberFormatter.setAllowsInvalid(false);
            numberFormatter.setMinimum(0.0);
            textQuantidadeEstoque = new JFormattedTextField(numberFormatter);
            StyleGuide.formataComponente(textQuantidadeEstoque);
        }
        return textQuantidadeEstoque;
    }

    public JButton getBtnAtualizarTabela() {
        if(btnAtualizarTabela == null) {
            btnAtualizarTabela = new JButton("Atualizar tabela de produtos");
            StyleGuide.formataComponente(btnAtualizarTabela);
        }
        return btnAtualizarTabela;
    }

    public JButton getBtnEditarProduto() {
        if(btnEditarProduto == null) {
            btnEditarProduto = new JButton("Editar Produto");
          StyleGuide.formataComponente(btnEditarProduto);
        }
        return btnEditarProduto;
      }

    public JTable getTabelaProduto() {
        if (tabelaProduto == null) {
            String[] titulos = { "ID", "Nome", "Tipo", "Preço Compra", "Preço Venda", "Fabricante", "Validade",
                    "Quantidade Estoque" };
            DefaultTableModel modelo = new DefaultTableModel(titulos, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tabelaProduto = new JTable(modelo);
        }
        return tabelaProduto;
    }

    public void atualizarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) getTabelaProduto().getModel();
        modelo.setRowCount(0); // Limpa a tabela antes de adicionar novos dados
        
        ProdutoDatabase produtoDatabase = new ProdutoDatabase();
        List<Object[]> dadosCliente = produtoDatabase.consultarProdutos();
        for (Object[] linha : dadosCliente) {
        modelo.addRow(linha);
        }
    }
}
