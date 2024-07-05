package main.model.database;

import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import main.model.connection.Conexao;
import main.model.entity.Produto;

public class ProdutoDatabase {
  public void cadastrarProduto(Produto produto) {
    // Consulta SQL a ser feita
    String sql = "INSERT INTO produto (nome, tipo, precoCompra, precoVenda, fabricante, validade, quantidadeEstoque, estoqueInicial) VALUES (?,?,?,?,?,?,?,?)";
    
    // IMPORTANTE: Todas as datas no sql devem estar no formato ano/mes/dia.
    // O Código abaixo converte o formato 01/10/2004 para 2004/10/01
    SimpleDateFormat formatoOrigem = new SimpleDateFormat("dd/MM/yyyy");
    java.util.Date data = null;
    try {
        data = formatoOrigem.parse(produto.getValidade());
    } catch (ParseException e) {
        e.printStackTrace();
    }
    SimpleDateFormat formatoDestino = new SimpleDateFormat("yyyy/MM/dd");
    String dataFormatada = formatoDestino.format(data);

    // Cria o preparedStatement que vai fazer as operações
    PreparedStatement ps = null;

    // Faz a conexão trazendo os valores a serem inseridos, e depois finaliza tudo
    try {
      ps = Conexao.getConexao().prepareStatement(sql);
      ps.setString(1, produto.getNome());
      ps.setString(2, produto.getTipo());
      ps.setDouble(3, produto.getPrecoCompra());
      ps.setDouble(4, produto.getPrecoVenda());
      ps.setString(5, produto.getFabricante());
      ps.setString(6, dataFormatada);
      ps.setInt(7, produto.getQuantidadeEstoque());
      ps.setInt(8, produto.getEstoqueInicial());

      ps.execute();
      ps.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void novoPedido(int produtoId, double quantidade) {
    String sql = "UPDATE produto set quantidadeEstoque = ? WHERE idProduto = ?";
    PreparedStatement ps = null;
    try {
      ps = Conexao.getConexao().prepareStatement(sql);
      ps.setDouble(1, quantidade);
      ps.setInt(2, produtoId);
      ps.execute();
      ps.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void compraFeita(int produtoId, int quantidade) {
    String sql = "UPDATE produto set quantidadeEstoque = ? WHERE idProduto = ?";
    PreparedStatement ps = null;
    try {
      ps = Conexao.getConexao().prepareStatement(sql);
      ps.setInt(1, quantidade);
      ps.setInt(2, produtoId);
      ps.execute();
      ps.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void estornarCompra(int quantidade, int produtoId) {
    String sql = "UPDATE produto set quantidadeEstoque = (quantidadeEstoque + ?) WHERE idProduto = ?";
    PreparedStatement ps = null;
    try {
      ps = Conexao.getConexao().prepareStatement(sql);
      ps.setInt(1, quantidade);
      ps.setInt(2, produtoId);
      ps.execute();
      ps.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public List<Object[]> consultarProdutos() {
    List<Object[]> linhas = new ArrayList<>();
    String sql = "SELECT * FROM Produto ORDER BY idProduto";
    PreparedStatement ps = null;
    try {
      ps = Conexao.getConexao().prepareStatement(sql);
      ResultSet rs = ps.executeQuery(sql);
      ResultSetMetaData metaData = rs.getMetaData();
      int columnCount = metaData.getColumnCount();
      while(rs.next()) {
        Object[] linha = new Object[columnCount];
        for (int i = 1; i <= columnCount; i++) {
          linha[i - 1] = rs.getObject(i);
        }
        linhas.add(linha);
      }
      ps.close();
      rs.close();
      return linhas;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public List<Produto> listarProdutosComboBox() {
    List<Produto> produtos = new ArrayList<>();
    String sql = "SELECT idProduto, nome, quantidadeEstoque FROM Produto ORDER BY idProduto";
    PreparedStatement ps = null;
    try {
      ps = Conexao.getConexao().prepareStatement(sql);
      ResultSet rs = ps.executeQuery(sql);
      while (rs.next()) {
        int idProduto = rs.getInt("idProduto");
        String nomeProduto = rs.getString("nome");
        int quantidadeEstoque = rs.getInt("quantidadeEstoque");

        Produto produto = new Produto();
        produto.setId(idProduto);
        produto.setNome(nomeProduto);
        produto.setQuantidadeEstoque(quantidadeEstoque);
        produtos.add(produto);
      }
      ps.close();
      rs.close();
      return produtos;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public List<Produto> listarProdutosVenda() {
    List<Produto> produtos = new ArrayList<>();
    String sql = "SELECT idProduto, nome, precoVenda, quantidadeEstoque FROM Produto ORDER BY idProduto";
    PreparedStatement ps = null;
    try {
      ps = Conexao.getConexao().prepareStatement(sql);
      ResultSet rs = ps.executeQuery(sql);
      while (rs.next()) {
        int idProduto = rs.getInt("idProduto");
        String nomeProduto = rs.getString("nome");
        double precoVenda = rs.getInt("precoVenda");
        int quantidadeEstoque = rs.getInt("quantidadeEstoque");

        Produto produto = new Produto();
        produto.setId(idProduto);
        produto.setNome(nomeProduto);
        produto.setPrecoVenda(precoVenda);
        produto.setQuantidadeEstoque(quantidadeEstoque);
        produtos.add(produto);
      }
      ps.close();
      rs.close();
      return produtos;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
