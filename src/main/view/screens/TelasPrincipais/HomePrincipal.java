package main.view.screens.TelasPrincipais;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.controller.actions.ButtonHomeGerarBalanco;
import main.model.entity.Balanco;
import main.view.components.StyleGuide;

public class HomePrincipal extends JPanel {
    private Balanco balanco;
    private JButton botaoInternalFrame;

    private JLabel numeroClientes;
    private JLabel numeroProdutos;
    private JLabel numeroVendas;
    private JLabel estoqueTotal;
    private JLabel gastoEstoque;
    private JLabel gastoPedidos;
    private JLabel gastoTotal;
    private JLabel faturamento;
    private JLabel lucro;

    // private JTextField numeroClientes;
    // private JTextField numeroProdutos;
    // private JTextField numeroVendas;
    // private JTextField estoqueTotal;
    // private JTextField gastoEstoque;
    // private JTextField gastoPedidos;
    // private JTextField gastoTotal;
    // private JTextField faturamento;
    // private JTextField lucro;

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
        add(getBotaoInternalFrame());
        ButtonHomeGerarBalanco buttonHomeGerarBalanco = new ButtonHomeGerarBalanco(this);
        botaoInternalFrame.addActionListener(buttonHomeGerarBalanco);
    }

    public JButton getBotaoInternalFrame() {
        if (botaoInternalFrame == null) {
            botaoInternalFrame = new JButton("Gerar Balanço");
            StyleGuide.formataComponente(botaoInternalFrame);
        }
        return botaoInternalFrame;
    }

    public void gerarBalanco() {
        numeroClientes = new JLabel(balanco.getNumeroClientes());
        numeroProdutos = new JLabel(balanco.getNumeroProdutos());
        numeroVendas = new JLabel(balanco.getNumeroVendas());

        this.add(numeroClientes);
        this.add(numeroProdutos);
        this.add(numeroVendas);
    }

    public void setBalanco(Balanco balanco) {
        this.balanco = balanco;
    }
}
