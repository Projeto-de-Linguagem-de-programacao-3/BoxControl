package main.controller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import main.model.database.ProdutoDatabase;
import main.model.entity.Produto;
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

    if(!verificarData(validade)) {
      return;
    }

    // Criar objeto DadosProduto e definir os dados do produto
    Produto produto = new Produto();
    produto.setNome(nome);
    produto.setTipo(tipo);
    produto.setPrecoCompra(precoCompra);
    produto.setPrecoVenda(precoVenda);
    produto.setFabricante(fabricante);
    produto.setValidade(validade);
    produto.setQuantidadeEstoque(quantidadeEstoque);
    produto.setEstoqueInicial(quantidadeEstoque);

    ProdutoDatabase produtoDatabase = new ProdutoDatabase();
    produtoDatabase.cadastrarProduto(produto);

    // Exibir mensagem de sucesso ou falha
    JOptionPane.showMessageDialog(produtoPrincipal, "Produto salvo com sucesso!", "Resultado",
        JOptionPane.INFORMATION_MESSAGE);
    produtoPrincipal.atualizarTabela();
  }

  private boolean verificarData(String validade) {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); // Defina o formato da data
    Date dataValidade = null;
    try {
        dataValidade = formatter.parse(validade);
    } catch (ParseException error) {
      error.printStackTrace();
      JOptionPane.showMessageDialog(produtoPrincipal, "Data de validade inválida!",
              "Erro de validação", JOptionPane.ERROR_MESSAGE);
      return false;
    }

    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date()); // Define a data atual
    cal.add(Calendar.MONTH, 1); // Adiciona um mês
    Date dataUmMesDepois = cal.getTime();
    if (dataValidade.before(dataUmMesDepois)) {
      JOptionPane.showMessageDialog(produtoPrincipal, "O produto deve ter pelo menos um mês de validade!",
              "Erro de validação", JOptionPane.ERROR_MESSAGE);
      return false;
    } else {
      return true;
    }
  }
}
