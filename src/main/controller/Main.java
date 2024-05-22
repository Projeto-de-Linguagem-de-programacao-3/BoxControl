package main.controller;

import java.awt.BorderLayout;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JPanel;

import main.controller.actions.ButtonMenuListener;
import main.model.entity.DadosFinanceiro;
import main.view.components.MenuLateral;
import main.view.components.TelaSwitch;
import main.view.screens.Frame;

// Aqui fica só o necessário para o início do código

public class Main {
  public static void main(String[] args) {
    // Criando o arquivo txt caso não exista para verificação de balanço
    criarArquivo("cliente.txt");
    criarArquivo("Produtos.txt");
    criarArquivo("Vendas.txt");
    criarArquivo("pedido.txt");
    criarArquivoFinanceiro();

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

  private static void criarArquivo(String nomeArquivo) {
    String caminhoArquivo = nomeArquivo; // Substitua "caminho/para/" pelo seu caminho real

    try {
        Files.createFile(Paths.get(caminhoArquivo));
        System.out.println("Arquivo " + nomeArquivo + " criado com sucesso!");
    } catch (FileAlreadyExistsException e) {
        System.out.println("O arquivo " + nomeArquivo + " já existe.");
    } catch (IOException e) {
        e.printStackTrace();
    }}

    private static void criarArquivoFinanceiro() {
      String caminhoArquivo = "financeiro.txt"; // Substitua "caminho/para/" pelo seu caminho real
  
      try {
          Files.createFile(Paths.get(caminhoArquivo));
          DadosFinanceiro dadosFinanceiro = new DadosFinanceiro();
          dadosFinanceiro.registraBasico();
          System.out.println("Arquivo " + caminhoArquivo + " criado com sucesso!");
      } catch (FileAlreadyExistsException e) {
          System.out.println("O arquivo " + caminhoArquivo + " já existe.");
      } catch (IOException e) {
          e.printStackTrace();
      }
}

}
