package main.model.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.model.connection.Conexao;
import main.model.entity.Balanco;

public class BalancoDatabase {
  public Balanco consultaResumo() {
    Balanco balanco;
    String sql = "SELECT COUNT(distinct cliente.idCliente) AS numeroClientes, COUNT(distinct produto.idProduto) AS numeroProdutos, COUNT(distinct venda.idVenda) AS numeroVendas, SUM(produto.quantidadeEstoque) AS estoqueTotal, SUM(produto.quantidadeEstoque * produto.precoCompra) as gastoEstoque, SUM(pedidoproduto.quantidade * pedidoproduto.precoCompra) as gastoPedidos, SUM(produto.quantidadeEstoque * produto.precoCompra) + SUM(pedidoproduto.quantidade * pedidoproduto.precoCompra) as gastoTotal, SUM(venda.valorTotal) as faturamento, SUM(venda.valorTotal) - (SUM(produto.quantidadeEstoque * produto.precoCompra) + SUM(pedidoproduto.quantidade * pedidoproduto.precoCompra)) as lucro FROM cliente, produto, pedidoproduto, venda";
    PreparedStatement ps = null;
    try {
      ps = Conexao.getConexao().prepareStatement(sql);
      ResultSet rs = ps.executeQuery(sql);
      if(rs.next()) {
        String numeroClientes = rs.getString("numeroClientes");
        String numeroProdutos = rs.getString("numeroProdutos");
        String numeroVendas = rs.getString("numeroVendas");
        String estoqueTotal = rs.getString("estoqueTotal");
        String gastoEstoque = rs.getString("gastoEstoque");
        String gastoPedidos = rs.getString("gastoPedidos");
        String gastoTotal = rs.getString("gastoTotal");
        String faturamento = rs.getString("faturamento");
        String lucro = rs.getString("lucro");
        balanco = new Balanco(numeroClientes, numeroProdutos, numeroVendas, estoqueTotal, gastoEstoque, gastoPedidos, gastoTotal, faturamento, lucro);
        return balanco;
      }
      return null;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }  
  }
}
