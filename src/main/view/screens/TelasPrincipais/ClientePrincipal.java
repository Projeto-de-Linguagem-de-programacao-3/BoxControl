package main.view.screens.TelasPrincipais;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.*;
import main.view.components.StyleGuide;
import main.controller.actions.ButtonClientesSalvarListener;
import main.model.entity.DadosCliente;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class ClientePrincipal extends JPanel {
  private JLabel labelNome;
  private JTextField textNome;
  private JLabel labelCPF;
  private JTextField textCPF;
  private JLabel labelRG;
  private JTextField textRG;
  private JLabel labelNascimento;
  private JFormattedTextField textNascimento;
  private JLabel labelCredito;
  private JTextField textCredito;
  private JTable tabelaCliente;
  private JButton btnSalvar;

  public ClientePrincipal() {
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
    add(getLabelCPF(), constraints);
    constraints.gridx = 0;
    constraints.gridy = 3;
    add(getTextCPF(), constraints);

    constraints.gridx = 0;
    constraints.gridy = 4;
    add(getLabelRG(), constraints);
    constraints.gridx = 0;
    constraints.gridy = 5;
    add(getTextRG(), constraints);

    constraints.gridx = 0;
    constraints.gridy = 6;
    add(getLabelNascimento(), constraints);
    constraints.gridx = 0;
    constraints.gridy = 7;
    add(getTextNascimento(), constraints);

    constraints.gridx = 0;
    constraints.gridy = 8;
    add(getLabelCredito(), constraints);
    constraints.gridx = 0;
    constraints.gridy = 9;
    add(getTextCredito(), constraints);

    constraints.gridx = 0;
    constraints.gridy = 10;
    constraints.gridwidth = 2; // ocupa 2 colunas
    add(getBtnSalvar(), constraints);
    ButtonClientesSalvarListener buttonClientesSalvarListener = new ButtonClientesSalvarListener(this);
    btnSalvar.addActionListener(buttonClientesSalvarListener);

    constraints.gridx = 2;
    constraints.gridy = 0;
    constraints.gridwidth = 2;
    constraints.gridheight = 5;
    JScrollPane scrollPane = new JScrollPane(getTabelaCliente());
    add(scrollPane, constraints);

    preencheClienteName(textNome);

  }

  public JLabel getLabelNome() {
    if (labelNome == null) {
      labelNome = new JLabel("Nome:");
      StyleGuide.formataComponente(labelNome);
    }
    return labelNome;
  }

  public JTextField getTextNome() {
    if (textNome == null) {
      textNome = new JTextField(20); // Ajustar o tamanho como desejado
      StyleGuide.formataComponente(textNome);
    }
    return textNome;
  }

  public JLabel getLabelCPF() {
    if (labelCPF == null) {
      labelCPF = new JLabel("CPF:");
      StyleGuide.formataComponente(labelCPF);
    }
    return labelCPF;
  }

  public JTextField getTextCPF() {
    if (textCPF == null) {
      try {
        MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
        textCPF = new JFormattedTextField(mascaraCpf);
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }

    return textCPF;
  }

  public JLabel getLabelRG() {
    if (labelRG == null) {
      labelRG = new JLabel("RG:");
      StyleGuide.formataComponente(labelRG);
    }
    return labelRG;
  }

  public JTextField getTextRG() {
    if (textRG == null) {
      textRG = new JTextField(); // Ajustar o tamanho como desejado
      try {
        // Definir um MaskFormatter para o formato de data
        MaskFormatter dateFormatter = new MaskFormatter("##.###.###-##");
        textRG = new JFormattedTextField(dateFormatter);
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }
    return textRG;
  }

  public JLabel getLabelNascimento() {
    if (labelNascimento == null) {
      labelNascimento = new JLabel("Nascimento:");
      StyleGuide.formataComponente(labelNascimento);
    }
    return labelNascimento;
  }

  public JFormattedTextField getTextNascimento() {
    if (textNascimento == null) {
      textNascimento = new JFormattedTextField();
      try {
        // Definir um MaskFormatter para o formato de data
        MaskFormatter dateFormatter = new MaskFormatter("##/##/####");
        textNascimento = new JFormattedTextField(dateFormatter);
      } catch (ParseException e) {
        e.printStackTrace();
      }
      StyleGuide.formataComponente(textNascimento);
    }
    return textNascimento;
  }

  public JLabel getLabelCredito() {
    if (labelCredito == null) {
      labelCredito = new JLabel("Crédito:");
      StyleGuide.formataComponente(labelCredito);
    }
    return labelCredito;
  }

  public JTextField getTextCredito() {
    if (textCredito == null) {

      NumberFormat numberFormat = NumberFormat.getNumberInstance();
      numberFormat.setGroupingUsed(false);
      NumberFormatter numberFormatter = new NumberFormatter(numberFormat);
      numberFormatter.setValueClass(Double.class);
      numberFormatter.setAllowsInvalid(false); // Não permite caracteres não numéricos
      numberFormatter.setMinimum(0.0); // Define um valor mínimo

      textCredito = new JFormattedTextField(numberFormatter);
      textCredito.setColumns(10); // Ajustar o tamanho como desejado
      StyleGuide.formataComponente(textCredito);

    }
    return textCredito;
  }

  public JButton getBtnSalvar() {
    if (btnSalvar == null) {
      btnSalvar = new JButton("Salvar cliente");
      StyleGuide.formataComponente(btnSalvar);
    }
    return btnSalvar;
  }

  public JTable getTabelaCliente() {
    if (tabelaCliente == null) {
      DefaultTableModel modelo = new DefaultTableModel();
      modelo.addColumn("Nome", new Class[] { String.class });
      modelo.addColumn("CPF", new Class[] { String.class });
      modelo.addColumn("RG", new Class[] { String.class });
      modelo.addColumn("DataNascimento", new Class[] { String.class });
      modelo.addColumn("LimiteDeCredito", new Class[] { String.class });
      tabelaCliente = new JTable(modelo);
    }
    return tabelaCliente;
  }

  private void preencheClienteName(JTextField textFieldNome) {

    try {
      File file = new File("cliente.txt");
      Scanner scanner = new Scanner(file);

      while (scanner.hasNextLine()) {
        String linha = scanner.nextLine();
        if (linha.startsWith("Nome: ")) {
          String nome = linha.substring(6).trim(); // Extrai o nome após "Nome: " e remove espaços em branco
          System.out.println(nome);
        }
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }

}
