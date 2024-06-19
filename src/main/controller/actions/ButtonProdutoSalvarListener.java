package main.controller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

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

    // Criar objeto DadosProduto e definir os dados do produto
    DadosProduto produto = new DadosProduto();
    produto.setNome(nome);
    produto.setTipo(tipo);
    produto.setPrecoCompra(precoCompra);
    produto.setPrecoVenda(precoVenda);
    produto.setFabricante(fabricante);
    produto.setValidade(validade);
    produto.setQuantidadeEstoque(quantidadeEstoque);

    // Aqui você pode adicionar lógica para salvar os dados em outro formato ou
    // local, se necessário
    // Por exemplo, salvar em um banco de dados ou enviar para um serviço web

    // Exibir mensagem de sucesso ou falha
    JOptionPane.showMessageDialog(produtoPrincipal, "Produto salvo com sucesso!", "Resultado",
        JOptionPane.INFORMATION_MESSAGE);
    // Atualizar tabela na interface, se necessário
  }
}
