package main.view.components;

import java.awt.BorderLayout;
import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class UpperScreen extends JPanel {

  // Declaração de variáveis para armazenar componentes da tela
  ImageIcon iconeScreen;
  JLabel tituloScreen;
  JLabel dataAtual;

  public UpperScreen(String title, String icone) {
    super();
    // Define o layout como BorderLayout com espaçamento de 10 pixels
    setLayout(new BorderLayout(10, 10));
    // Adiciona borda vazia com espaçamento de 5 pixels em cima, 10 nas laterais
    setBorder(new EmptyBorder(5, 10, 10, 10));
    // Define a cor de fundo usando a constante StyleGuide.bgMenu (assumindo que
    // está definida em outra classe)
    setBackground(StyleGuide.bgMenu);
    // Define a cor da fonte do título usando a constante StyleGuide.titleScreen
    // (assumindo que está definida em outra classe)
    setForeground(StyleGuide.titleScreen);
    // Adiciona o título na posição oeste (esquerda) e a data na posição leste
    // (direita)
    add(getTituloScreen(title, icone), BorderLayout.WEST);
    add(getDataAtual(), BorderLayout.EAST);
    // Torna o painel visível
    setVisible(true);
  }

  public JLabel getTituloScreen(String title, String icone) {
    // Verifica se o tituloScreen já foi inicializado, evitando redundância
    if (tituloScreen == null) {
      // Cria o ícone a partir do caminho fornecido
      ImageIcon icon = new ImageIcon(icone);
      // Cria o rótulo com o título e configura seu ícone, espaçamento entre ícone e
      // texto, fonte e cor
      tituloScreen = new JLabel(title);
      tituloScreen.setIcon(icon);
      tituloScreen.setIconTextGap(20);
      tituloScreen.setFont(new Font("Arial", Font.BOLD, 50));
      tituloScreen.setForeground(StyleGuide.titleScreen);
    }
    // Retorna o rótulo do título
    return tituloScreen;
  }

  public JLabel getDataAtual() {
    // Verifica se o dataAtual já foi inicializado, evitando redundância
    if (dataAtual == null) {
      // Pega a data atual
      LocalDate today = LocalDate.now();
      // Define o formato para exibição da data (dia da semana, dia, mês por extenso e
      // ano)
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd 'de' MMMM");
      // Formata a data de acordo com o padrão definido
      String formattedDate = today.format(formatter);
      // Converte a primeira letra do dia da semana para maiúscula
      String output = formattedDate.substring(0, 1).toUpperCase() + formattedDate.substring(1);
      // Cria o rótulo com a data formatada, configura sua cor, alinhamento vertical
      // do texto e fonte
      dataAtual = new JLabel(output);
      dataAtual.setForeground(StyleGuide.titleScreen);
      dataAtual.setVerticalTextPosition(JLabel.TOP);
      dataAtual.setFont(new Font("Arial", Font.BOLD, 16));
    }
    // Retorna o rótulo da data atual
    return dataAtual;
  }
}