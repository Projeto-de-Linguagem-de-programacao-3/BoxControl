package main.view.screens.TelasPrincipais;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.*;
import main.view.components.StyleGuide;
import main.controller.actions.ButtonClientesSalvarListener;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;


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
    constraints.gridheight = 10;
    JScrollPane scrollPane = new JScrollPane(getTabelaCliente());
    scrollPane.setMinimumSize(new Dimension(100, 300));
    add(scrollPane, constraints);

    // Preencher a tabela inicialmente
    atualizarTabela();
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
        StyleGuide.formataComponente(textCPF);
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
      try {
        MaskFormatter dateFormatter = new MaskFormatter("##.###.###-##");
        textRG = new JFormattedTextField(dateFormatter);
        StyleGuide.formataComponente(textRG);
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
      try {
        MaskFormatter dateFormatter = new MaskFormatter("##/##/####");
        textNascimento = new JFormattedTextField(dateFormatter);
        StyleGuide.formataComponente(textNascimento);
      } catch (ParseException e) {
        e.printStackTrace();
      }
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
      NumberFormatter numberFormatter = new NumberFormatter();
      numberFormatter.setValueClass(Double.class);
      numberFormatter.setAllowsInvalid(false);
      numberFormatter.setMinimum(0.0);

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
      String[] titulos = { "ID", "Nome", "CPF", "RG", "Data de Nascimento", "Limite de Crédito" };
      DefaultTableModel modelo = new DefaultTableModel(titulos, 0);
      tabelaCliente = new JTable(modelo);
    }
    return tabelaCliente;
  }

  public void atualizarTabela() {
    DefaultTableModel modelo = (DefaultTableModel) getTabelaCliente().getModel();
    modelo.setRowCount(0); // Limpa a tabela antes de adicionar novos dados

    // Aqui você pode carregar os dados dos clientes de uma fonte de dados, como um
    // banco de dados
    // ou, se necessário, de um arquivo (como no exemplo anterior)

    // Exemplo de dados fictícios para preenchimento inicial da tabela
    Object[] dadosCliente1 = { 1, "João da Silva", "123.456.789-00", "12.345.678-9", "01/01/1980", 1000.0 };
    Object[] dadosCliente2 = { 2, "Maria Oliveira", "987.654.321-00", "98.765.432-1", "15/06/1990", 1500.0 };

    modelo.addRow(dadosCliente1);
    modelo.addRow(dadosCliente2);
  }
}
