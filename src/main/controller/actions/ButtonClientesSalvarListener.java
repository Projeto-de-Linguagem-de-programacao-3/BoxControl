package main.controller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import main.model.database.ClienteDatabase;
import main.model.entity.Cliente;
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

    // Verificar se o CPF tem exatamente 11 caracteres
    if (cpf.length() != 14) {
      JOptionPane.showMessageDialog(clientePrincipal, "O CPF deve ter exatamente 11 números.", "Erro de validação",
          JOptionPane.ERROR_MESSAGE);
      return;
    }

    // Verificar se o RG tem exatamente 10 caracteres
    if (rg.length() != 13) {
      JOptionPane.showMessageDialog(clientePrincipal, "O RG deve ter exatamente 10 números.", "Erro de validação",
          JOptionPane.ERROR_MESSAGE);
      return;
    }

    // Verificar se o limite de crédito é válido
    double limiteCredito = 0;
    try {
      if (!limiteCreditoText.isEmpty()) {
        limiteCredito = Double.parseDouble(limiteCreditoText);
        if (limiteCredito <= 0) {
          JOptionPane.showMessageDialog(clientePrincipal, "O limite de crédito deve ser maior que 0.",
              "Erro de validação", JOptionPane.ERROR_MESSAGE);
          return;
        }
      }
    } catch (NumberFormatException ex) {
      JOptionPane.showMessageDialog(clientePrincipal,
          "Por favor, insira um valor numérico válido para o limite de crédito.", "Erro de validação",
          JOptionPane.ERROR_MESSAGE);
      return;
    }

    // Criar um objeto DadosCliente e definir os dados do cliente
    Cliente cliente = new Cliente();
    cliente.setNome(nome);
    cliente.setCpf(cpf);
    cliente.setRg(rg);
    cliente.setDataNascimento(dataNascimento);
    cliente.setLimiteCredito(limiteCredito);

    ClienteDatabase clienteDatabase = new ClienteDatabase();
    clienteDatabase.cadastrarCliente(cliente);

    // Exibir mensagem de sucesso
    JOptionPane.showMessageDialog(clientePrincipal, "Cliente salvo com sucesso!", "Resultado:",
        JOptionPane.INFORMATION_MESSAGE);
    clientePrincipal.atualizarTabela(); // Método fictício para atualizar a tabela na interface
  }
}
