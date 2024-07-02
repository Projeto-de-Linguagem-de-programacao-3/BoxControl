package main.model.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import main.model.connection.Conexao;
import main.model.entity.Vendas;

public class VendasDatabase {
  public void cadastrarVenda(Vendas venda) {
    // Consulta SQL a ser feita
    String sql = "INSERT INTO venda (Cliente_idCliente, formaDePagamento, valorTotal) VALUES (?,?,?)";

    // Cria o preparedStatement que vai fazer as operações
    PreparedStatement ps = null;

    // Faz a conexão trazendo os valores a serem inseridos, e depois finaliza tudo
    try {
      ps = Conexao.getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setInt(1, venda.getCliente().getId());
      ps.setString(2, venda.getFormaPagamento());
      ps.setDouble(3, venda.getValorTotal());
      int rowsAffected = ps.executeUpdate();
      System.out.println("Linhas afetadas: " + rowsAffected);
      ResultSet generatedKeys = ps.getGeneratedKeys();
      if (generatedKeys.next()) {
        venda.setId(generatedKeys.getLong(1));
      } else {
          System.out.println("Não foi possível obter o ID gerado para o novo produto.");
      }
      ps.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void cadastrarItemVenda(Vendas venda) {
    String sql = "INSERT INTO itemVenda (Produto_idProduto, Venda_idVenda, Quantidade) VALUES (?,?,?)";
    PreparedStatement ps = null;
    try {
      ps = Conexao.getConexao().prepareStatement(sql);
      ps.setInt(1, venda.getProduto().getId());
      ps.setLong(2, venda.getId());
      ps.setString(3, venda.getQuantidade());
      ps.execute();
      int quantidadeCompra = Integer.parseInt(venda.getQuantidade());
      int quantidadeNova = venda.getProduto().getQuantidadeEstoque() - quantidadeCompra;
      ProdutoDatabase produtoDatabase = new ProdutoDatabase();
      produtoDatabase.compraFeita(venda.getProduto().getId(),quantidadeNova);
      ps.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public List<Object[]> consultarVendas() {
    List<Object[]> linhas = new ArrayList<>();
    String sql = "SELECT cliente.nome as cliente, produto.nome as produto, itemvenda.quantidade, venda.formaDePagamento, venda.ValorTotal FROM venda JOIN itemvenda ON (venda.idVenda = itemvenda.Venda_idVenda) JOIN produto ON (produto.idProduto = itemvenda.Produto_idProduto) JOIN cliente ON (venda.Cliente_idCliente = cliente.idCliente)";
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
}
