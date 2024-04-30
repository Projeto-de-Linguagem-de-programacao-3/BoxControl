package main.controller;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import main.controller.actions.ButtonMenuListener;
import main.view.components.MenuLateral;
import main.view.components.TelaSwitch;
import main.view.screens.Frame;

// Aqui fica só o necessário para o início do código

public class Main {
  public static void main(String[] args) {
    // Instancia das classes principais
    Frame frame = new Frame();
    JPanel layout = new JPanel();
    MenuLateral menuLateral = new MenuLateral();
    TelaSwitch telaSwitch = new TelaSwitch();

    // Instancia do(s) controlador(es)
    ButtonMenuListener ButtonMenuListener = new ButtonMenuListener(menuLateral, telaSwitch);

    // Layout do JPanel que vai funcionar como container, usando Border Layout
    layout.setLayout(new BorderLayout());
    frame.setLayout(new BorderLayout());

    // O menu fica na esquerda (West), e as telas principais ocupam todo o resto
    // Se não deixar como center ela fica quebrada no canto
    frame.add(menuLateral, BorderLayout.WEST);
    frame.add(telaSwitch, BorderLayout.CENTER);

    // Adicionando o mesmo actionListener para os botões
    menuLateral.getBotaoClientes().addActionListener(ButtonMenuListener);
    menuLateral.getBotaoProdutos().addActionListener(ButtonMenuListener);
    menuLateral.getBotaoPedidos().addActionListener(ButtonMenuListener);
    menuLateral.getBotaoVendas().addActionListener(ButtonMenuListener);
    menuLateral.getBotaoHome().addActionListener(ButtonMenuListener);

    // Mesmo tendo colocado no construtor do frame, se não colocar aqui dá erro
    frame.setVisible(true);
  }
}
