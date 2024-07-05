package main.controller.actions.Inserir;

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
    double precoCompra;
    double precoVenda;
    int quantidadeEstoque;
    String nome = produtoPrincipal.getTextNome().getText();
    String tipo = produtoPrincipal.getTextTipo().getText();
    String quantidadeEstoqueTxt = produtoPrincipal.getTextQuantidadeEstoque().getText();
    quantidadeEstoqueTxt = quantidadeEstoqueTxt.replace(".", "");
    System.out.println(quantidadeEstoqueTxt);
    try {
      precoCompra = Double.parseDouble(produtoPrincipal.getTextPrecoCompra().getText());
      precoVenda = Double.parseDouble(produtoPrincipal.getTextPrecoVenda().getText());
      quantidadeEstoque = Integer.parseInt(quantidadeEstoqueTxt);
    } catch (Exception error) {
      JOptionPane.showMessageDialog(produtoPrincipal, "Preencha todos os valores!", "Erro de validação",
        JOptionPane.ERROR_MESSAGE);
        return;
    }
    String fabricante = produtoPrincipal.getTextFabricante().getText();
    String validade = produtoPrincipal.getTextValidade().getText();

    if(nome.isEmpty() || tipo.isEmpty() || fabricante.isEmpty()) {
      JOptionPane.showMessageDialog(produtoPrincipal, "Preencha todos os valores!", "Erro de validação",
        JOptionPane.ERROR_MESSAGE);
        return;
    }

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
    if(produtoDatabase.produtoExiste(nome, tipo, fabricante)) {
      JOptionPane.showMessageDialog(produtoPrincipal, "Nome, Tipo e fabricante ja exitem! Altere um desses dados", "Erro de validação",
        JOptionPane.ERROR_MESSAGE);
      return;
    }
    produtoDatabase.cadastrarProduto(produto);

    // Exibir mensagem de sucesso ou falha
    JOptionPane.showMessageDialog(produtoPrincipal, "Produto salvo com sucesso!", "Resultado",
        JOptionPane.INFORMATION_MESSAGE);
    produtoPrincipal.atualizarTabela();
  }

  private boolean verificarData(String validade) {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); // Defina o formato da data
    formatter.setLenient(false);
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
