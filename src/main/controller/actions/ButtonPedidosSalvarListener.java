package main.controller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import main.model.entity.DadosPedidos;
import main.view.screens.TelasPrincipais.PedidosPrincipal;

public class ButtonPedidosSalvarListener implements ActionListener {
  private PedidosPrincipal pedidosPrincipal;

  public ButtonPedidosSalvarListener(PedidosPrincipal pedidosPrincipal) {
    this.pedidosPrincipal = pedidosPrincipal;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String produto = (String) pedidosPrincipal.getTextProduto().getSelectedItem();
    double precoCompra = Double.parseDouble(pedidosPrincipal.getTextPrecoCompra().getText());
    String fabricante = pedidosPrincipal.getTextFabricante().getText();
    String validade = pedidosPrincipal.getTextValidade().getText();
    int quantidade = Integer.parseInt(pedidosPrincipal.getTextQuantidade().getText());

    // Criar objeto DadosPedidos e definir os dados do pedido
    DadosPedidos pedido = new DadosPedidos();
    pedido.setProduto(produto);
    pedido.setPrecoCompra(precoCompra);
    pedido.setFabricante(fabricante);
    pedido.setValidade(validade);
    pedido.setQuantidade(quantidade);

    // Aqui você pode adicionar lógica para salvar os dados em outro formato ou
    // local, se necessário
    // Por exemplo, salvar em um banco de dados ou enviar para um serviço web

    // Exibir mensagem de sucesso ou falha
    JOptionPane.showMessageDialog(pedidosPrincipal, "Pedido salvo com sucesso!", "Resultado",
        JOptionPane.INFORMATION_MESSAGE);
  }
}
