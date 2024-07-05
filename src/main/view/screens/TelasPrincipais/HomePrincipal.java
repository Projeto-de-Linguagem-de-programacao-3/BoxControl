package main.view.screens.TelasPrincipais;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.controller.actions.Inserir.ButtonHomeGerarBalanco;
import main.model.entity.Balanco;
import main.view.components.StyleGuide;

public class HomePrincipal extends JPanel {
    private Balanco balanco;
    private JButton botaoInternalFrame;

    private JLabel labelNumeroClientes;
    private JLabel labelNumeroProdutos;
    private JLabel labelNumeroVendas;
    private JLabel labelEstoqueTotal;
    private JLabel labelGastoEstoque;
    private JLabel labelGastoPedidos;
    private JLabel labelGastoTotal;
    private JLabel labelFaturamento;
    private JLabel labelLucro;

    private JTextField textNumeroClientes;
    private JTextField textNumeroProdutos;
    private JTextField textNumeroVendas;
    private JTextField textEstoqueTotal;
    private JTextField textGastoEstoque;
    private JTextField textGastoPedidos;
    private JTextField textGastoTotal;
    private JTextField textFaturamento;
    private JTextField textLucro;

    public HomePrincipal() {
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
        constraints.gridwidth = 3;
        add(getBotaoInternalFrame(), constraints);
        ButtonHomeGerarBalanco buttonHomeGerarBalanco = new ButtonHomeGerarBalanco(this);
        botaoInternalFrame.addActionListener(buttonHomeGerarBalanco);

        // Primeira Coluna
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        add(getLabelNumeroClientes(),constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        add(getTextNumeroClientes(),constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        add(getLabelNumeroProdutos(),constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        add(getTextNumeroProdutos(),constraints);
        
        constraints.gridx = 2;
        constraints.gridy = 2;
        add(getLabelNumeroVendas(),constraints);

        constraints.gridx = 2;
        constraints.gridy = 3;
        add(getTextNumeroVendas(),constraints);

        // Segunda coluna
        constraints.gridx = 0;
        constraints.gridy = 4;
        add(getLabelEstoqueTotal(),constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        add(getTextEstoqueTotal(),constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        add(getLabelGastoEstoque(),constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        add(getTextGastoEstoque(),constraints);

        constraints.gridx = 2;
        constraints.gridy = 4;
        add(getLabelGastoPedidos(),constraints);

        constraints.gridx = 2;
        constraints.gridy = 5;
        add(getTextGastoPedidos(),constraints);

        // Terceira coluna
        constraints.gridx = 0;
        constraints.gridy = 6;
        add(getLabelGastoTotal(),constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        add(getTextGastoTotal(),constraints);

        constraints.gridx = 1;
        constraints.gridy = 6;
        add(getLabelFaturamento(),constraints);

        constraints.gridx = 1;
        constraints.gridy = 7;
        add(getTextFaturamento(),constraints);

        constraints.gridx = 2;
        constraints.gridy = 6;
        add(getLabelLucro(),constraints);

        constraints.gridx = 2;
        constraints.gridy = 7;
        add(getTextLucro(),constraints);
    }

    public JButton getBotaoInternalFrame() {
        if (botaoInternalFrame == null) {
            botaoInternalFrame = new JButton("Gerar Balanço");
            StyleGuide.formataComponente(botaoInternalFrame);
        }
        return botaoInternalFrame;
    }

    public void setBalanco(Balanco balanco) {
        this.balanco = balanco;
    }

    public Balanco getBalanco() {
        return balanco;
    }

    public JLabel getLabelNumeroClientes() {
        if (labelNumeroClientes == null) {
            labelNumeroClientes = new JLabel("Numero de clientes:");
            StyleGuide.formataComponente(labelNumeroClientes);
        }
        return labelNumeroClientes;
    }
    
    public JLabel getLabelNumeroProdutos() {
        if (labelNumeroProdutos == null) {
            labelNumeroProdutos = new JLabel("Numero de produtos:");
            StyleGuide.formataComponente(labelNumeroProdutos);
        }
        return labelNumeroProdutos;
    }
    
    public JLabel getLabelNumeroVendas() {
        if (labelNumeroVendas == null) {
            labelNumeroVendas = new JLabel("Numero de vendas:");
            StyleGuide.formataComponente(labelNumeroVendas);
        }
        return labelNumeroVendas;
    }
    
    public JLabel getLabelEstoqueTotal() {
        if (labelEstoqueTotal == null) {
            labelEstoqueTotal = new JLabel("Estoque total:");
            StyleGuide.formataComponente(labelEstoqueTotal);
        }
        return labelEstoqueTotal;
    }
    
    public JLabel getLabelGastoEstoque() {
        if (labelGastoEstoque == null) {
            labelGastoEstoque = new JLabel("Gasto em estoque:");
            StyleGuide.formataComponente(labelGastoEstoque);
        }
        return labelGastoEstoque;
    }
    
    public JLabel getLabelGastoPedidos() {
        if (labelGastoPedidos == null) {
            labelGastoPedidos = new JLabel("Gasto em pedidos:");
            StyleGuide.formataComponente(labelGastoPedidos);
        }
        return labelGastoPedidos;
    }
    
    public JLabel getLabelGastoTotal() {
        if (labelGastoTotal == null) {
            labelGastoTotal = new JLabel("Gasto total:");
            StyleGuide.formataComponente(labelGastoTotal);
        }
        return labelGastoTotal;
    }
    
    public JLabel getLabelFaturamento() {
        if (labelFaturamento == null) {
            labelFaturamento = new JLabel("Faturamento:");
            StyleGuide.formataComponente(labelFaturamento);
        }
        return labelFaturamento;
    }
    
    public JLabel getLabelLucro() {
        if (labelLucro == null) {
            labelLucro = new JLabel("Lucro:");
            StyleGuide.formataComponente(labelLucro);
        }
        return labelLucro;
    }
    
    public JTextField getTextNumeroClientes() {
        if (textNumeroClientes == null) {
            textNumeroClientes = new JTextField();
            textNumeroClientes.setEditable(false);
        }
        return textNumeroClientes;
    }
    
    public JTextField getTextNumeroProdutos() {
        if (textNumeroProdutos == null) {
            textNumeroProdutos = new JTextField();
            textNumeroProdutos.setEditable(false);
        }
        return textNumeroProdutos;
    }
    
    public JTextField getTextNumeroVendas() {
        if (textNumeroVendas == null) {
            textNumeroVendas = new JTextField();
            textNumeroVendas.setEditable(false);
        }
        return textNumeroVendas;
    }
    
    public JTextField getTextEstoqueTotal() {
        if (textEstoqueTotal == null) {
            textEstoqueTotal = new JTextField();
            textEstoqueTotal.setEditable(false);
        }
        return textEstoqueTotal;
    }
    
    public JTextField getTextGastoEstoque() {
        if (textGastoEstoque == null) {
            textGastoEstoque = new JTextField();
            textGastoEstoque.setEditable(false);
        }
        return textGastoEstoque;
    }
    
    public JTextField getTextGastoPedidos() {
        if (textGastoPedidos == null) {
            textGastoPedidos = new JTextField();
            textGastoPedidos.setEditable(false);
        }
        return textGastoPedidos;
    }
    
    public JTextField getTextGastoTotal() {
        if (textGastoTotal == null) {
            textGastoTotal = new JTextField();
            textGastoTotal.setEditable(false);
        }
        return textGastoTotal;
    }
    
    public JTextField getTextFaturamento() {
        if (textFaturamento == null) {
            textFaturamento = new JTextField();
            textFaturamento.setEditable(false);
        }
        return textFaturamento;
    }
    
    public JTextField getTextLucro() {
        if (textLucro == null) {
            textLucro = new JTextField();
            textLucro.setEditable(false);
        }
        return textLucro;
    }
    
    public void gerarBalanco() {
        textNumeroClientes.setText(balanco.getNumeroClientes());
        textNumeroProdutos.setText(balanco.getNumeroProdutos());
        textNumeroVendas.setText(balanco.getNumeroVendas());
        textEstoqueTotal.setText(balanco.getEstoqueTotal());
        textGastoEstoque.setText(balanco.getGastoEstoque());
        textGastoPedidos.setText(balanco.getGastoPedidos());
        textGastoTotal.setText(balanco.getGastoTotal());
        textFaturamento.setText(balanco.getFaturamento());
        textLucro.setText(balanco.getLucro());
    }
}
