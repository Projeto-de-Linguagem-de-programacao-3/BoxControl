package main.controller.actions.Excluir;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import main.model.database.PedidoDatabase;
import main.model.database.ProdutoDatabase;
import main.model.entity.Produto;
import main.view.screens.TelasPrincipais.PedidosPrincipal;

public class ButtonExcluirPedidoListener implements ActionListener {
  private PedidosPrincipal pedidosPrincipal;

  public ButtonExcluirPedidoListener(PedidosPrincipal pedidosPrincipal) {
    this.pedidosPrincipal = pedidosPrincipal;
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    int resposta = JOptionPane.showConfirmDialog(pedidosPrincipal, "Deseja devolver esse pedido? Ele sumirá do estoque do Produto", "confirmação", JOptionPane.YES_NO_OPTION);
      if(resposta == JOptionPane.YES_OPTION) {
        int colunaSelecionada = pedidosPrincipal.getTabelaPedidos().getSelectedRow();
        if(colunaSelecionada == -1) {
          JOptionPane.showMessageDialog(pedidosPrincipal, "Nenhum pedido selecionado!", "Erro de validação", JOptionPane.ERROR_MESSAGE);
          return;
        } else {
          int id = (Integer) pedidosPrincipal.getTabelaPedidos().getValueAt(colunaSelecionada, 0);
          int idProduto = (Integer) pedidosPrincipal.getTabelaPedidos().getValueAt(colunaSelecionada, 1);
          int quantidade = (Integer) pedidosPrincipal.getTabelaPedidos().getValueAt(colunaSelecionada, 5);
          ProdutoDatabase produtoDatabase = new ProdutoDatabase();
          PedidoDatabase pedidoDatabase = new PedidoDatabase();
          Produto produtoDoPedido = produtoDatabase.consultaProdutoPedido(idProduto);
          double quantidadeNova = produtoDoPedido.getQuantidadeEstoque() - quantidade;
          if(quantidadeNova < 0) {
            JOptionPane.showMessageDialog(pedidosPrincipal, "Impossível devolver pedido, o estoque desse pedido já está sendo utilizado!", "Erro de verificação", JOptionPane.ERROR_MESSAGE);
            return;
          }
          produtoDatabase.novoPedido(idProduto, quantidadeNova);
          pedidoDatabase.deletaPedido(id);
          JOptionPane.showMessageDialog(pedidosPrincipal, "Pedido excluído com sucesso!", "Resultado",
              JOptionPane.INFORMATION_MESSAGE);
              pedidosPrincipal.atualizarTabela();
        }
      } else {
        return;
      }
  }
  
}
