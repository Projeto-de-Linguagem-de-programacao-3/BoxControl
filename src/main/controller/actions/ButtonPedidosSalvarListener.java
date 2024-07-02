package main.controller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import main.model.database.PedidoDatabase;
import main.model.entity.Pedido;
import main.model.entity.Produto;
import main.view.screens.TelasPrincipais.PedidosPrincipal;

public class ButtonPedidosSalvarListener implements ActionListener {
  private PedidosPrincipal pedidosPrincipal;

  public ButtonPedidosSalvarListener(PedidosPrincipal pedidosPrincipal) {
    this.pedidosPrincipal = pedidosPrincipal;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Produto produto = (Produto) pedidosPrincipal.getTextProduto().getSelectedItem();
    double precoCompra = Double.parseDouble(pedidosPrincipal.getTextPrecoCompra().getText());
    String fabricante = pedidosPrincipal.getTextFabricante().getText();
    String validade = pedidosPrincipal.getTextValidade().getText();
    int quantidade = Integer.parseInt(pedidosPrincipal.getTextQuantidade().getText());

    // Criar objeto DadosPedidos e definir os dados do pedido
    Pedido pedido = new Pedido();
    pedido.setProduto(produto.getId());
    pedido.setPrecoCompra(precoCompra);
    pedido.setFabricante(fabricante);
    pedido.setValidade(validade);
    pedido.setQuantidade(quantidade);

    PedidoDatabase pedidoDatabase = new PedidoDatabase();
    pedidoDatabase.cadastrarPedido(pedido, produto.getQuantidadeEstoque());

    // Exibir mensagem de sucesso ou falha
    JOptionPane.showMessageDialog(pedidosPrincipal, "Pedido salvo com sucesso!", "Resultado",
        JOptionPane.INFORMATION_MESSAGE);
    pedidosPrincipal.atualizarTabela();
  }
}
