package testesJunit;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.junit.jupiter.api.Test;

import main.controller.actions.ButtonProdutoSalvarListener;
import main.model.entity.DadosProduto;
import main.view.screens.TelasPrincipais.ProdutoPrincipal;

// Crie uma classe de teste para ButtonProdutoSalvarListener
public class ButtonProdutoSalvarListenerTeste {

    @Test
    public void testActionPerformed() {
        // Crie instâncias de ProdutoPrincipal e DadosProduto
        ProdutoPrincipal produtoPrincipal = new ProdutoPrincipal();
        DadosProduto produto = new DadosProduto();

        // Defina valores de teste para os campos de texto da tela
        produtoPrincipal.getTextNome().setText("Produto Teste");
        produtoPrincipal.getTextTipo().setText("Eletrônico");
        produtoPrincipal.getTextPrecoCompra().setText("10.00");
        produtoPrincipal.getTextPrecoVenda().setText("15.00");
        produtoPrincipal.getTextFabricante().setText("Fabricante Teste");
        produtoPrincipal.getTextValidade().setText("2024-12-31");
        produtoPrincipal.getTextQuantidadeEstoque().setText("10");

        // Crie um ButtonProdutoSalvarListener e adicione-o como ActionListener ao ProdutoPrincipal
        ButtonProdutoSalvarListener listener = new ButtonProdutoSalvarListener(produtoPrincipal);
        produtoPrincipal.getBtnSalvar().addActionListener(listener);

        // Simule a ação de clicar no botão Salvar
        listener.actionPerformed(new ActionEvent(produtoPrincipal, ActionEvent.ACTION_PERFORMED, "Salvar"));

        // Verifique se os valores dos campos de texto foram salvos no objeto DadosProduto
        assertEquals("Produto Teste", produto.getNome());
        assertEquals("Eletrônico", produto.getTipo());
        assertEquals(10.0, produto.getPrecoCompra());
        assertEquals(15.0, produto.getPrecoVenda());
        assertEquals("Fabricante Teste", produto.getFabricante());
        assertEquals("2024/12/31", produto.getValidade());
        assertEquals(10, produto.getQuantidadeEstoque());

        // Verifique se a mensagem de resultado foi exibida corretamente
        String mensagemEsperada = "Dados salvos com sucesso!";
        String mensagemAtual = JOptionPane.getRootFrame().getTitle();
        assertEquals(mensagemEsperada, mensagemAtual);
    }
}
