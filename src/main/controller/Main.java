package main.controller;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import main.controller.actions.ButtonMenuListener;
import main.view.components.MenuLateral;
import main.view.components.TelaSwitch;
import main.view.screens.Frame;

public class Main {
    public static void main(String[] args) {
        // Instancia das classes principais
        Frame frame = new Frame();
        JPanel layout = new JPanel();
        MenuLateral menuLateral = new MenuLateral();
        TelaSwitch telaSwitch = new TelaSwitch();

        // Instancia do(s) controlador(es)
        ButtonMenuListener buttonMenuListener = new ButtonMenuListener(menuLateral, telaSwitch);

        // Layout do JPanel que vai funcionar como container, usando Border Layout
        layout.setLayout(new BorderLayout());
        frame.setLayout(new BorderLayout());

        // O menu fica na esquerda (West), e as telas principais ocupam todo o resto
        frame.add(menuLateral, BorderLayout.WEST);
        frame.add(telaSwitch, BorderLayout.CENTER);

        // Adicionando o mesmo actionListener para os botões
        menuLateral.getBotaoClientes().addActionListener(buttonMenuListener);
        menuLateral.getBotaoProdutos().addActionListener(buttonMenuListener);
        menuLateral.getBotaoPedidos().addActionListener(buttonMenuListener);
        menuLateral.getBotaoVendas().addActionListener(buttonMenuListener);
        menuLateral.getBotaoHome().addActionListener(buttonMenuListener);

        // Mesmo tendo colocado no construtor do frame, se não colocar aqui dá erro
        frame.setVisible(true);

        // Inicialização dos dados financeiros
        // DadosFinanceiro dadosFinanceiro = new DadosFinanceiro();

    }
}
