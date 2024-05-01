package main.view.screens.TelasPrincipais;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.view.components.StyleGuide;
import main.model.entity.DadosCliente;

public class ClientePrincipal extends JPanel {
  private JTextField Nome;
  private JTextField CPF;
  private JTextField RG;
  private JTextField DataNascimento;
  private JFormattedTextField LimiteCredito;

  public ClientePrincipal() {
    super();
    setBackground(StyleGuide.bgScreen);
    configurarComponentes();
  }

  private void configurarComponentes() {
    setLayout(null);

    JLabel lblNome = new JLabel("Nome:");
    lblNome.setBounds(20, 20, 120, 20);
    add(lblNome);

    Nome = new JTextField();
    Nome.setBounds(150, 20, 200, 20);
    add(Nome);

    JLabel lblCPF = new JLabel("CPF:");
    lblCPF.setBounds(20, 50, 120, 20);
    add(lblCPF);

    CPF = new JTextField();
    CPF.setBounds(150, 50, 200, 20);
    add(CPF);
    // Definir o DocumentFilter para permitir apenas números no campo CPF
    ((AbstractDocument) CPF.getDocument()).setDocumentFilter(new NumericFilter());

    JLabel lblRG = new JLabel("RG:");
    lblRG.setBounds(20, 80, 120, 20);
    add(lblRG);

    RG = new JTextField();
    RG.setBounds(150, 80, 200, 20);
    add(RG);
    // Definir o DocumentFilter para permitir apenas números no campo RG
    ((AbstractDocument) RG.getDocument()).setDocumentFilter(new NumericFilter());

    JLabel lblDataNascimento = new JLabel("Data de Nascimento:");
    lblDataNascimento.setBounds(20, 110, 150, 20);
    add(lblDataNascimento);

    DataNascimento = new JTextField();
    DataNascimento.setBounds(150, 110, 200, 20);
    add(DataNascimento);
    // Definir o DocumentFilter para permitir apenas números no campo DataNascimento
    ((AbstractDocument) DataNascimento.getDocument()).setDocumentFilter(new NumericFilter());

    JLabel lblLimiteCredito = new JLabel("Limite de Crédito:");
    lblLimiteCredito.setBounds(20, 140, 120, 20);
    add(lblLimiteCredito);

    LimiteCredito = new JFormattedTextField();
    LimiteCredito.setBounds(150, 140, 200, 20);
    add(LimiteCredito);

    ((AbstractDocument) LimiteCredito.getDocument()).setDocumentFilter(new NumericFilter());

    JButton btnSalvar = new JButton("Salvar em Texto");
    btnSalvar.setBounds(150, 170, 150, 30);
    btnSalvar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        salvarCliente();
      }
    });
    add(btnSalvar);
  }

  private void salvarCliente() {
    // Obter os dados inseridos pelo usuário
    String nome = Nome.getText();
    String cpf = CPF.getText();
    String rg = RG.getText();
    String dataNascimento = DataNascimento.getText();
    double limiteCredito = Double.parseDouble(LimiteCredito.getText());

    // Criar um objeto DadosCliente e definir os dados do cliente
    DadosCliente cliente = new DadosCliente();
    cliente.setNome(nome);
    cliente.setCpf(cpf);
    cliente.setRg(rg);
    cliente.setDataNascimento(dataNascimento);
    cliente.setLimiteCredito(limiteCredito);

    // Chamar o método salvarTxt() para salvar os dados do cliente
    String resultado = cliente.salvarTxt();
    System.out.println(resultado);
  }

  // Implementação de DocumentFilter para permitir apenas números
  class NumericFilter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
        throws BadLocationException {
      if (string.matches("[0-9]+")) {
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
