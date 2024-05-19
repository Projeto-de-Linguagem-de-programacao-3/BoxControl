package main.view.screens.TelasPrincipais;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.*;
import main.view.components.StyleGuide;
import main.controller.actions.ButtonClientesSalvarListener;
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
    constraints.weightx=1;
    constraints.weighty=1;
    constraints.fill=GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(10,10,10,10);

    constraints.gridx=0; 
    constraints.gridy=0; 
    add(getLabelNome(),constraints);
    constraints.gridx=0; 
    constraints.gridy=1; 
    add(getTextNome(), constraints);

    constraints.gridx=0; 
    constraints.gridy=2; 
    add(getLabelCPF(), constraints);
    constraints.gridx=0; 
    constraints.gridy=3; 
    add(getTextCPF(), constraints);

    constraints.gridx=0; 
    constraints.gridy=4; 
    add(getLabelRG(), constraints);
    constraints.gridx=0; 
    constraints.gridy=5; 
    add(getTextRG(), constraints);

    constraints.gridx=0; 
    constraints.gridy=6; 
    add(getLabelNascimento(), constraints);
    constraints.gridx=0; 
    constraints.gridy=7; 
    add(getTextNascimento(), constraints);

    constraints.gridx=0; 
    constraints.gridy=8; 
    add(getLabelCredito(), constraints);
    constraints.gridx=0;
    constraints.gridy=9;
    add(getTextCredito(), constraints);

    constraints.gridx=0; 
    constraints.gridy=10; 
    constraints.gridwidth=2; // ocupa 2 colunas
    add(getBtnSalvar(), constraints);
    ButtonClientesSalvarListener buttonClientesSalvarListener = new ButtonClientesSalvarListener(this);
    btnSalvar.addActionListener(buttonClientesSalvarListener);

    constraints.gridx=2;
    constraints.gridy=0;
    constraints.gridwidth=2;
    constraints.gridheight=5;
    JScrollPane scrollPane = new JScrollPane(getTabelaCliente());
    add(scrollPane, constraints);
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
        textCPF = new JTextField(); // Ajustar o tamanho como desejado
        ((AbstractDocument) textCPF.getDocument()).setDocumentFilter(new NumericFilter(11));
        StyleGuide.formataComponente(textCPF);
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
        ((AbstractDocument) textRG.getDocument()).setDocumentFilter(new NumericFilter(9));
        StyleGuide.formataComponente(textRG);
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
        textCredito = new JTextField();
        ((AbstractDocument) textCredito.getDocument()).setDocumentFilter(new NumericFilter(20));
        StyleGuide.formataComponente(textCredito);
    }
    return textCredito;
}

public JButton getBtnSalvar() {
  if(btnSalvar == null) {
    btnSalvar = new JButton("Salvar cliente");
    StyleGuide.formataComponente(btnSalvar);
  }
  return btnSalvar;
}

public JTable getTabelaCliente() {
  if(tabelaCliente == null) {
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.addColumn("Nome", new Class[]{String.class});
    modelo.addColumn("CPF", new Class[]{String.class});
    modelo.addColumn("RG", new Class[]{String.class});
    modelo.addColumn("DataNascimento", new Class[]{String.class});
    modelo.addColumn("LimiteDeCredito", new Class[]{String.class});
    tabelaCliente = new JTable(modelo);
  }
  return tabelaCliente;
}

  // Implementação de DocumentFilter para permitir apenas números e limitar o
  // comprimento
  // é dor da cebeça. terminar essa parte 01/5/24
  class NumericFilter extends DocumentFilter {
    private int maxLength; // Adição aqui

    public NumericFilter(int maxLength) { // Adição aqui
      this.maxLength = maxLength; // Adição aqui
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
        throws BadLocationException {
      if (string.matches("[0-9]+") && (fb.getDocument().getLength() + string.length()) <= maxLength) {
        super.insertString(fb, offset, string, attr);
      }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
        throws BadLocationException {
      if (text.matches("[0-9]+") || text.equals("")) {
        super.replace(fb, offset, length, text, attrs);
      }
    }
  }
}
