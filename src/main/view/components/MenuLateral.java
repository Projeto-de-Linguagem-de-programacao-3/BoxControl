package main.view.components;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MenuLateral extends JPanel {
  // Botões da tela
  JButton botaoHome;
  JButton botaoClientes;
  JButton botaoProdutos;
  JButton botaoPedidos;
  JButton botaoVendas;

  // Variável das cores, talvez coloque em um arquivo separado, para centralizar a estilização
  private Color bgButton = new Color(26,34,51);
  private Color bgPanel = new Color(47,72,88);

  public MenuLateral() {
    // Crio um GridBagLayout para o JPanel, e crio as constraints
    // As constraints servem para dizer em qual posição o elemento vai ficar
    setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();

    // Adiciono uma borda para ficar ao redor de todos os elementos
    setBorder(new EmptyBorder(0,30,5,30));

    // Coloco a cor do background
    setBackground(bgPanel);
    
    // weightx e weighty são quantas casas do grid cada elemento vai ocupar, o padrão é 1
    constraints.weightx=1;
    constraints.weighty=1;
    // Defino a ordem dos elementos como horizontal
    constraints.fill=GridBagConstraints.HORIZONTAL;
    // Adiciono um gap entre cada botão
    constraints.insets = new Insets(0,10,30,10);

    // Para cada botão abaixo, eu defino a sua posição no grid, e adiciono com os valores 
    // Das constraints.
    constraints.gridx=0;
    constraints.gridy=0;
    add(getBotaoHome(),constraints);

    constraints.gridx=0;
    constraints.gridy=1;
    add(getBotaoClientes(),constraints);

    constraints.gridx=0;
    constraints.gridy=2;
    add(getBotaoProdutos(),constraints);

    constraints.gridx=0;
    constraints.gridy=3;
    add(getBotaoPedidos(),constraints);

    constraints.gridx=0;
    constraints.gridy=4;
    add(getBotaoVendas(),constraints);
  }

  public JButton getBotaoHome() {
    if(botaoHome == null) {
      ImageIcon icon = new ImageIcon("src/resources/homeIcon.png");
      botaoHome = new JButton("Home",icon);
      formataBotao(botaoHome);
    }
    return botaoHome;
  }

  // Getters dos botões
  public JButton getBotaoClientes() {
    if(botaoClientes == null) {
      ImageIcon icon = new ImageIcon("src/resources/usuarioIcon.png");
      botaoClientes = new JButton("Clientes",icon);
      formataBotao(botaoClientes);
    }
    return botaoClientes;
  }

  public JButton getBotaoProdutos() {
    if(botaoProdutos == null) {
      ImageIcon icon = new ImageIcon("src/resources/produtoIcon.png");
      botaoProdutos = new JButton("Produtos",icon);
      formataBotao(botaoProdutos);
    }
    return botaoProdutos;
  }

  public JButton getBotaoPedidos() {
    if(botaoPedidos == null) {
      ImageIcon icon = new ImageIcon("src/resources/pedidoIcon.png");
      botaoPedidos = new JButton("Pedidos",icon);
      formataBotao(botaoPedidos);
    }
    return botaoPedidos;
  }

  public JButton getBotaoVendas() {
    if(botaoVendas == null) {
      ImageIcon icon = new ImageIcon("src/resources/vendasIcon.png");
      botaoVendas = new JButton("Vendas",icon);
      formataBotao(botaoVendas);
    }
    return botaoVendas;
  }

  // Uma classe única que vai formatar todos os botões de forma igual
  private void formataBotao(JButton botao) {
    // Cor de fundo do botão
    botao.setBackground(bgButton);
    // Cor da letra
    botao.setForeground(Color.WHITE);
    // Borda interna do botão
    botao.setBorder(new EmptyBorder(10,20,10,50));
    // O cursor vai ficar diferente quando passar o mouse em cima
    botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
    // Distancia entre o texto e os icones
    botao.setIconTextGap(15);
    // Fonte para o texto, deixei arial 16 bold, podem alterar a vontade
    Font font = new Font("Arial", Font.BOLD, 16);
    botao.setFont(font);
    // Tira o quadrado branco que aparece quando clicamos em um botão
    botao.setFocusPainted(false);
  }
}
