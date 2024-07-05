package main.controller.actions.Editar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import main.model.database.ClienteDatabase;
import main.view.screens.TelasPrincipais.ClientePrincipal;

public class ButtonAtivarClienteListener implements ActionListener {
  private ClientePrincipal clientePrincipal;

  public ButtonAtivarClienteListener(ClientePrincipal clientePrincipal) {
    this.clientePrincipal = clientePrincipal;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    int resposta = JOptionPane.showConfirmDialog(clientePrincipal, "Deseja ativar esse cliente?", "confirmação", JOptionPane.YES_NO_OPTION);
      if(resposta == JOptionPane.YES_OPTION) {
        int colunaSelecionada = clientePrincipal.getTabelaCliente().getSelectedRow();
        if(colunaSelecionada == -1) {
          JOptionPane.showMessageDialog(clientePrincipal, "Nenhum cliente selecionado!", "Erro de validação", JOptionPane.ERROR_MESSAGE);
          return;
        } else {
          int id = (Integer) clientePrincipal.getTabelaCliente().getModel().getValueAt(colunaSelecionada, 0);
          ClienteDatabase clienteDatabase = new ClienteDatabase();
          clienteDatabase.ativarCliente(id);
          JOptionPane.showMessageDialog(clientePrincipal, "Cliente ativado com sucesso!", "Resultado:",
          JOptionPane.INFORMATION_MESSAGE);
          clientePrincipal.atualizarTabela();
        }
      } else {
        return;
      }
  }
  
}
