package main.controller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import main.model.entity.DadosFinanceiro;
import main.model.entity.DadosProduto;
import main.view.screens.TelasPrincipais.ProdutoPrincipal;

public class ButtonProdutoSalvarListener implements ActionListener {
  private ProdutoPrincipal produtoPrincipal;

  public ButtonProdutoSalvarListener(ProdutoPrincipal produtoPrincipal) {
    this.produtoPrincipal = produtoPrincipal;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String nome = produtoPrincipal.getTextNome().getText();
    String tipo = produtoPrincipal.getTextTipo().getText();
    double precoCompra = Double.parseDouble(produtoPrincipal.getTextPrecoCompra().getText());
    double precoVenda = Double.parseDouble(produtoPrincipal.getTextPrecoVenda().getText());
    String fabricante = produtoPrincipal.getTextFabricante().getText();
    String validade = produtoPrincipal.getTextValidade().getText();
    int quantidadeEstoque = Integer.parseInt(produtoPrincipal.getTextQuantidadeEstoque().getText());

    DadosProduto produto = new DadosProduto();

    produto.setNome(nome);
    produto.setNome(tipo);
    produto.setPrecoCompra(precoCompra);
    produto.setPrecoVenda(precoVenda);
    produto.setFabricante(fabricante);
    produto.setValidade(validade);
    produto.setQuantidadeEstoque(quantidadeEstoque);

    String resultado = produto.salvarTxt();
    JOptionPane.showMessageDialog(produtoPrincipal, resultado, "Resultado:", JOptionPane.INFORMATION_MESSAGE);
    produtoPrincipal.atualizarTabela();
  }
  
}
