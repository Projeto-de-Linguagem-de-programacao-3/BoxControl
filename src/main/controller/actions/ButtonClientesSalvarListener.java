package main.controller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    if (nome.isEmpty() || cpf.contains("_") || rg.contains("_") || dataNascimento.isEmpty() || limiteCreditoText.isEmpty()) {
      JOptionPane.showMessageDialog(clientePrincipal, "Preencha todos os valores!", "Erro de validação",
        JOptionPane.ERROR_MESSAGE);
        return;
    }
    String dataVerificar;
    try {
      dataVerificar = converterStringParaDataValida(dataNascimento);
    } catch (Exception erroString) {
      JOptionPane.showMessageDialog(clientePrincipal, "Preencha a data de nascimento!", "Erro de validação",
        JOptionPane.ERROR_MESSAGE);
      return;
    }
    if (!temEntre19e100(dataVerificar)) {
        JOptionPane.showMessageDialog(clientePrincipal, "O cliente deve ter pelo menos 18 anos e no máximo 116 anos", "Erro de validação",
        JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Verificar se o CPF tem exatamente 11 caracteres
    if (cpf.trim().length() != 14) {
      JOptionPane.showMessageDialog(clientePrincipal, "O CPF deve ter exatamente 11 números.", "Erro de validação",
          JOptionPane.ERROR_MESSAGE);
      return;
    }

    // Verificar se o RG tem exatamente 10 caracteres
    if (rg.trim().length() != 13) {
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

    if(!cliente.isValid()) {
      JOptionPane.showMessageDialog(clientePrincipal, "Erro: Todos os campos devem ser preenchidos corretamente.", "Erro de validação",
       JOptionPane.ERROR_MESSAGE);
    }

    ClienteDatabase clienteDatabase = new ClienteDatabase();
    if(clienteDatabase.clienteExiste(cpf, rg)) {
      JOptionPane.showMessageDialog(clientePrincipal, "CPF e RG já existem!", "Erro de validação",
        JOptionPane.ERROR_MESSAGE);
      return;
    }
    clienteDatabase.cadastrarCliente(cliente);

    // Exibir mensagem de sucesso
    JOptionPane.showMessageDialog(clientePrincipal, "Cliente salvo com sucesso!", "Resultado:",
        JOptionPane.INFORMATION_MESSAGE);
    clientePrincipal.atualizarTabela();
  }

  private String converterStringParaDataValida(String dateString) {
    SimpleDateFormat formatoOrigem = new SimpleDateFormat("dd/MM/yyyy");
    Date data = null;
    try {
      data = formatoOrigem.parse(dateString);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    SimpleDateFormat formatoDestino = new SimpleDateFormat("yyyy/MM/dd");
    String dataFormatada = formatoDestino.format(data);
    return dataFormatada;
  }

  private boolean temEntre19e100(String dateString) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    sdf.setLenient(false);
    try {
        Date birthDate = sdf.parse(dateString);
        Calendar birthCal = Calendar.getInstance();
        birthCal.setTime(birthDate);
        
        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR);

        if (today.get(Calendar.MONTH) < birthCal.get(Calendar.MONTH) || 
            (today.get(Calendar.MONTH) == birthCal.get(Calendar.MONTH) && today.get(Calendar.DAY_OF_MONTH) < birthCal.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }

        return age >= 18 && age <= 116;
    } catch (ParseException e) {
        return false;
    }
  }
}
