package testesJunit;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.junit.jupiter.api.Test;

import main.controller.actions.ButtonPedidosSalvarListener;
import main.model.entity.DadosPedidos;
import main.view.screens.TelasPrincipais.PedidosPrincipal;

// Crie uma classe de teste para ButtonPedidosSalvarListener
public class ButtonPedidosSalvarListenerTest {

    // Teste o método actionPerformed sem usar mocks
    @Test
    public void testActionPerformed() {
        // Crie instâncias de PedidosPrincipal e DadosPedidos
        PedidosPrincipal pedidosPrincipal = new PedidosPrincipal();
        DadosPedidos pedido = new DadosPedidos();

        // Defina valores de teste para os campos de texto da tela
        pedidosPrincipal.getTextProduto().setText("Produto Teste");
        pedidosPrincipal.getTextPrecoCompra().setText("10.00");
        pedidosPrincipal.getTextFabricante().setText("Fabricante Teste");
        pedidosPrincipal.getTextValidade().setText("2024/12/31");
        pedidosPrincipal.getTextQuantidade().setText("1");

        // Crie um ButtonPedidosSalvarListener e adicione-o como ActionListener ao PedidosPrincipal
        ButtonPedidosSalvarListener listener = new ButtonPedidosSalvarListener(pedidosPrincipal);
        pedidosPrincipal.getBtnSalvar().addActionListener(listener);

        // Simule a ação de clicar no botão Salvar
        listener.actionPerformed(new ActionEvent(pedidosPrincipal, ActionEvent.ACTION_PERFORMED, "Salvar"));

        // Verifique se os valores dos campos de texto foram salvos no objeto DadosPedidos
        assertEquals("Produto Teste", pedido.getProduto());
        assertEquals(10.0, pedido.getPrecoCompra());
        assertEquals("Fabricante Teste", pedido.getFabricante());
        assertEquals("2024-12-31", pedido.getValidade());
        assertEquals(1, pedido.getQuantidade());

        // Verifique se a mensagem de resultado foi exibida corretamente
        String mensagemEsperada = "Dados salvos com sucesso!";
        String mensagemAtual = JOptionPane.getRootFrame().getTitle();
        assertEquals(mensagemEsperada, mensagemAtual);
    }
}

