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
    
    
    DadosPedidos pedido = new DadosPedidos();
    pedido.setProduto(produto);
    pedido.setPrecoCompra(precoCompra);
    pedido.setFabricante(fabricante);
    pedido.setValidade(validade);
    pedido.setQuantidade(quantidade);
    
    
    String resultado = pedido.salvarTxt();
    JOptionPane.showMessageDialog(pedidosPrincipal, resultado, "Resultado:", JOptionPane.INFORMATION_MESSAGE);
    pedidosPrincipal.atualizarTabela();
    
  }
  
}
