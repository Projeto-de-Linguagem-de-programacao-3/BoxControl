package main.view.screens.TelasPrincipais;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.view.components.StyleGuide;
import main.model.entity.DadosCliente;
import java.awt.Font;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;

public class ClientePrincipal extends JPanel {
  private JTextField Nome;
  private JTextField CPF;
  private JTextField RG;
  private JFormattedTextField DataNascimento;
  private JFormattedTextField LimiteCredito;

  public ClientePrincipal() {
    super();
    setBackground(StyleGuide.bgScreen);
    configurarComponentes();
  }

  private void configurarComponentes() {
    setLayout(null);

    JLabel lblNome = new JLabel("Nome:");
    lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
    lblNome.setBounds(20, 6, 120, 20);
    add(lblNome);

    Nome = new JTextField();
    Nome.setBounds(20, 32, 200, 20);
    add(Nome);

    JLabel lblCPF = new JLabel("CPF:");
    lblCPF.setFont(new Font("Tahoma", Font.PLAIN, 14));
    lblCPF.setBounds(20, 58, 120, 20);
    add(lblCPF);

    CPF = new JTextField();
    CPF.setBounds(20, 84, 200, 20);
    add(CPF);
    // Definir o DocumentFilter para permitir apenas números no campo CPF e limitar
    // a 11 caracteres
    ((AbstractDocument) CPF.getDocument()).setDocumentFilter(new NumericFilter(11));

    JLabel lblRG = new JLabel("RG:");
    lblRG.setFont(new Font("Tahoma", Font.PLAIN, 14));
    lblRG.setBounds(20, 110, 120, 20);
    add(lblRG);

    RG = new JTextField();
    RG.setBounds(20, 136, 200, 20);
    add(RG);
    // Definir o DocumentFilter para permitir apenas números no campo RG
    ((AbstractDocument) RG.getDocument()).setDocumentFilter(new NumericFilter(9));

    JLabel lblDataNascimento = new JLabel("Data de Nascimento:");
    lblDataNascimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
    lblDataNascimento.setBounds(20, 162, 150, 20);
    add(lblDataNascimento);

    try {
      // Definir um MaskFormatter para o formato de data
      MaskFormatter dateFormatter = new MaskFormatter("##/##/####");
      DataNascimento = new JFormattedTextField(dateFormatter);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    DataNascimento.setBounds(20, 188, 200, 20);
    add(DataNascimento);

    JLabel lblLimiteCredito = new JLabel("Limite de Crédito:");
    lblLimiteCredito.setFont(new Font("Tahoma", Font.PLAIN, 14));
    lblLimiteCredito.setBounds(20, 214, 120, 20);
    add(lblLimiteCredito);

    LimiteCredito = new JFormattedTextField();
    LimiteCredito.setBounds(20, 240, 200, 20);
    add(LimiteCredito);

    ((AbstractDocument) LimiteCredito.getDocument()).setDocumentFilter(new NumericFilter(20));

    JButton btnSalvar = new JButton("Salvar em Texto");
    btnSalvar.setBounds(20, 266, 150, 20);
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
    String limiteCreditoText = LimiteCredito.getText(); // Obter o texto do limite de crédito

    // Verificar se o CPF tem exatamente 8 caracteres
    // também tem que colocar os para valores menor
    if (cpf.length() != 8) {
      JOptionPane.showMessageDialog(this, "O CPF deve ter exatamente 8 números.", "Erro de validação",
          JOptionPane.ERROR_MESSAGE);
      return;
    }

    // Verificar se o RG tem exatamente 9 caracteres
    // também tem que colocar os para valores menor
    if (rg.length() != 9) {
      JOptionPane.showMessageDialog(this, "O RG deve ter exatamente 9 números.", "Erro de validação",
          JOptionPane.ERROR_MESSAGE);
      return;
    }
    // Verificar se o limite de crédito ultrapassa 100.000,00
    if (!limiteCreditoText.isEmpty() && Double.parseDouble(limiteCreditoText) > 100000) {
      JOptionPane.showMessageDialog(this, "O limite de crédito deve ser até 100.000,00.", "Erro de validação",
          JOptionPane.ERROR_MESSAGE);
      return;
    }

    // Converter o limite de crédito para double
    double limiteCredito = Double.parseDouble(limiteCreditoText);

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
