package main.controller.actions.Editar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import main.model.database.ClienteDatabase;
import main.model.database.ProdutoDatabase;
import main.model.entity.Cliente;
import main.model.entity.Produto;
import main.view.screens.TelasPrincipais.ProdutoPrincipal;

public class ButtonEditarProdutoListener implements ActionListener {
  private ProdutoPrincipal produtoPrincipal;

  public ButtonEditarProdutoListener(ProdutoPrincipal produtoPrincipal) {
    this.produtoPrincipal = produtoPrincipal;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    int resposta = JOptionPane.showConfirmDialog(produtoPrincipal, "Deseja alterar os dados desse cliente?", "confirmação", JOptionPane.YES_NO_OPTION);
      if(resposta == JOptionPane.YES_OPTION) {
        int colunaSelecionada = produtoPrincipal.getTabelaProduto().getSelectedRow();
        if(colunaSelecionada == -1) {
          JOptionPane.showMessageDialog(produtoPrincipal, "Nenhum cliente selecionado!", "Erro de validação", JOptionPane.ERROR_MESSAGE);
          return;
        } else {
          int id = (Integer) produtoPrincipal.getTabelaProduto().getValueAt(colunaSelecionada, 0);
          double precoCompra;
          double precoVenda;
          String nome = produtoPrincipal.getTextNome().getText();
          String tipo = produtoPrincipal.getTextTipo().getText();
          try {
            precoCompra = Double.parseDouble(produtoPrincipal.getTextPrecoCompra().getText());
            precoVenda = Double.parseDouble(produtoPrincipal.getTextPrecoVenda().getText());
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
          produto.setId(id);
          produto.setNome(nome);
          produto.setTipo(tipo);
          produto.setPrecoCompra(precoCompra);
          produto.setPrecoVenda(precoVenda);
          produto.setFabricante(fabricante);
          produto.setValidade(validade);

          ProdutoDatabase produtoDatabase = new ProdutoDatabase();
          if(!produtoDatabase.editarProdutoExiste(nome, tipo, fabricante, id)) {
            JOptionPane.showMessageDialog(produtoPrincipal, "Nome, Tipo e fabricante ja exitem! Altere um desses dados", "Erro de validação",
              JOptionPane.ERROR_MESSAGE);
            return;
          }
          produtoDatabase.alterarProduto(produto);

          // Exibir mensagem de sucesso ou falha
          JOptionPane.showMessageDialog(produtoPrincipal, "Produto salvo com sucesso!", "Resultado",
              JOptionPane.INFORMATION_MESSAGE);
          produtoPrincipal.atualizarTabela();
        }
      } else {
        return;
      }
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
