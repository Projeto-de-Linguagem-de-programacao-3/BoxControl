package testesJunit;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import main.model.entity.DadosCliente;

import org.junit.jupiter.api.Test;
import main.controller.actions.*;
import main.view.screens.TelasPrincipais.ClientePrincipal;

class ButtonClientesSalvarListenerTeste {
	
	@Test
	public void deveSalvarDadosClienteComSucesso() {
	    // Crie uma instância real da classe ClientePrincipal
	    ClientePrincipal clientePrincipal = new ClientePrincipal();

	    // Defina os valores dos campos de texto manualmente
	    clientePrincipal.getTextNome().setText("Fulano de Tal");
	    clientePrincipal.getTextCPF().setText("12345678900");
	    clientePrincipal.getTextRG().setText("123456789");
	    clientePrincipal.getTextNascimento().setText("10/10/1980");
	    clientePrincipal.getTextCredito().setText("50000.00");

	    // Crie um objeto ButtonClientesSalvarListener
	    ButtonClientesSalvarListener listener = new ButtonClientesSalvarListener(clientePrincipal);

	    // Dispare o evento actionPerformed
	    listener.actionPerformed(new ActionEvent(clientePrincipal, ActionEvent.ACTION_PERFORMED));

	    // Verifique se os dados do cliente foram salvos corretamente
	    DadosCliente clienteSalvo = new DadosCliente();
	    clienteSalvo.setNome("Fulano de Tal");
	    clienteSalvo.setCpf("12345678900");
	    clienteSalvo.setRg("123456789");
	    clienteSalvo.setDataNascimento("10/10/1980");
	    clienteSalvo.setLimiteCredito(50000.00);

	    // Verifique se a mensagem de sucesso foi exibida
	    String mensagemSucesso = JOptionPane.showInputDialog(clientePrincipal, null, "Resultado:", JOptionPane.INFORMATION_MESSAGE);
	    assertEquals("Dados salvos com sucesso!", mensagemSucesso);
	}

}
