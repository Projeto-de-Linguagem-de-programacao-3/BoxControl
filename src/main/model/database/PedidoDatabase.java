package main.model.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.model.connection.Conexao;
import main.model.entity.Pedido;

public class PedidoDatabase {
  ProdutoDatabase produtoDatabase = new ProdutoDatabase();
  public void cadastrarPedido(Pedido pedido, int quantidade) {
    // Consulta SQL a ser feita
    String sql = "INSERT INTO pedidoproduto (Produto_idProduto, precoCompra, fabricante, validade, quantidade) VALUES (?,?,?,?,?)";
    
    // IMPORTANTE: Todas as datas no sql devem estar no formato ano/mes/dia.
    // O Código abaixo converte o formato 01/10/2004 para 2004/10/01
    SimpleDateFormat formatoOrigem = new SimpleDateFormat("dd/MM/yyyy");
    Date data = null;
    try {
        data = formatoOrigem.parse(pedido.getValidade());
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
      ps.setInt(1, pedido.getProduto());
      ps.setDouble(2, pedido.getPrecoCompra());
      ps.setString(3, pedido.getFabricante());
      ps.setString(4, dataFormatada);
      ps.setDouble(5, pedido.getQuantidade());

      ps.execute();
      double quantidadeNova = quantidade + pedido.getQuantidade();
      produtoDatabase.novoPedido(pedido.getProduto(),quantidadeNova);
      ps.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public List<Object[]> consultarPedidos() {
    List<Object[]> linhas = new ArrayList<>();
    String sql = "SELECT idPedidoProduto, Produto_idProduto, precoCompra, fabricante, DATE_FORMAT(validade, '%d/%m/%Y'), quantidade FROM Pedidoproduto ORDER BY idPedidoProduto";
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
      return linhas;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public void deletaPedido(int id) {
    String sql = "DELETE FROM pedidoproduto WHERE idPedidoProduto = ?";
    PreparedStatement ps = null;
    try {
      ps = Conexao.getConexao().prepareStatement(sql);
      ps.setInt(1, id);
      ps.execute();
      ps.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
