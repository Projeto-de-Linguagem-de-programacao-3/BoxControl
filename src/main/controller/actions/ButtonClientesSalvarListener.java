package main.controller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import main.model.entity.DadosCliente;
import main.view.screens.TelasPrincipais.ClientePrincipal;

public class ButtonClientesSalvarListener implements ActionListener {
  private ClientePrincipal clientePrincipal;

  public ButtonClientesSalvarListener(ClientePrincipal clientePrincipal) {
    this.clientePrincipal = clientePrincipal;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // Obter os dados inseridos pelo usuário
    String nome = clientePrincipal.getTextNome().getText();
    String cpf = clientePrincipal.getTextCPF().getText();
    String rg = clientePrincipal.getTextRG().getText();
    String dataNascimento = clientePrincipal.getTextNascimento().getText();
    String limiteCreditoText = clientePrincipal.getTextCredito().getText(); // Obter o texto do limite de crédito

    // Verificar se o CPF tem exatamente 8 caracteres
    // também tem que colocar os para valores menor
    if (cpf.length() != 11) {
      JOptionPane.showMessageDialog(clientePrincipal, "O CPF deve ter exatamente 11 números.", "Erro de validação",
          JOptionPane.ERROR_MESSAGE);
      return;
    }

    // Verificar se o RG tem exatamente 9 caracteres
    // também tem que colocar os para valores menor
    if (rg.length() != 10) {
      JOptionPane.showMessageDialog(clientePrincipal, "O RG deve ter exatamente 10 números.", "Erro de validação",
          JOptionPane.ERROR_MESSAGE);
      return;
    }
    // Verificar se o limite de crédito ultrapassa 100.000,00
    if (!limiteCreditoText.isEmpty() && Double.parseDouble(limiteCreditoText) > 100000) {
      JOptionPane.showMessageDialog(clientePrincipal, "O limite de crédito deve ser até 100.000,00.", "Erro de validação",
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
    JOptionPane.showMessageDialog(clientePrincipal, resultado, "Resultado:", JOptionPane.INFORMATION_MESSAGE); 
  }
  
}
