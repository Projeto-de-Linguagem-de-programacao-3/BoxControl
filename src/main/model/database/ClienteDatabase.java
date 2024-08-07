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
import main.model.entity.Cliente;

public class ClienteDatabase {

  public void cadastrarCliente(Cliente cliente) {
    // Consulta SQL a ser feita
    String sql = "INSERT INTO cliente (nome, CPF, RG, DataNascimento, LimiteCredito, Estado) VALUES (?,?,?,?,?,?)";
    
    // IMPORTANTE: Todas as datas no sql devem estar no formato ano/mes/dia.
    // O Código abaixo converte o formato 01/10/2004 para 2004/10/01
    SimpleDateFormat formatoOrigem = new SimpleDateFormat("dd/MM/yyyy");
    Date data = null;
    try {
        data = formatoOrigem.parse(cliente.getDataNascimento());
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
      ps.setString(1, cliente.getNome());
      ps.setString(2, cliente.getCpf());
      ps.setString(3, cliente.getRg());
      ps.setString(4, dataFormatada);
      ps.setDouble(5, cliente.getLimiteCredito());
      ps.setString(6, "ativo");
      ps.execute();
      ps.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void alterarCliente(Cliente cliente) {
    String sql = "UPDATE Cliente SET nome = ?, CPF = ?, RG = ?, DataNascimento = ?, LimiteCredito = ? WHERE idCliente = ?";
    PreparedStatement ps = null;
    SimpleDateFormat formatoOrigem = new SimpleDateFormat("dd/MM/yyyy");
    Date data = null;
    try {
        data = formatoOrigem.parse(cliente.getDataNascimento());
    } catch (ParseException e) {
        e.printStackTrace();
    }
    SimpleDateFormat formatoDestino = new SimpleDateFormat("yyyy/MM/dd");
    String dataFormatada = formatoDestino.format(data);
    try {
      ps = Conexao.getConexao().prepareStatement(sql);
      ps.setString(1, cliente.getNome());
      ps.setString(2, cliente.getCpf());
      ps.setString(3, cliente.getRg());
      ps.setString(4, dataFormatada);
      ps.setDouble(5, cliente.getLimiteCredito());
      ps.setInt(6, cliente.getId());
      ps.execute();
      ps.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public List<Object[]> consultarClientes() {
    List<Object[]> linhas = new ArrayList<>();
    String sql = "SELECT idCliente, nome, CPF, RG, DATE_FORMAT(DataNascimento, '%d/%m/%Y'), limiteCredito, estado FROM Cliente ORDER BY idCliente";
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

  public List<Cliente> listarClientesComboBox() {
    List<Cliente> clientes = new ArrayList<>();
    String sql = "SELECT idCliente, nome, limiteCredito FROM Cliente WHERE estado = 'ativo' ORDER BY idCliente";
    PreparedStatement ps = null;
    try {
      ps = Conexao.getConexao().prepareStatement(sql);
      ResultSet rs = ps.executeQuery(sql);
      while (rs.next()) {
        int idCliente = rs.getInt("idCliente");
        String nomeCliente = rs.getString("nome");
        int limiteCredito = rs.getInt("limiteCredito");

        Cliente cliente = new Cliente();
        cliente.setId(idCliente);
        cliente.setNome(nomeCliente);
        cliente.setLimiteCredito(limiteCredito);
        clientes.add(cliente);
      }
      ps.close();
      rs.close();
      return clientes;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  
  public boolean clienteExiste(String cpf, String rg) {
    String sql = "SELECT COUNT(*) FROM cliente WHERE cpf = ? AND rg = ?";
    PreparedStatement ps = null;
    try {
      ps = Conexao.getConexao().prepareStatement(sql);
      ps.setString(1, cpf);
      ps.setString(2, rg);
      ResultSet rs = ps.executeQuery();
      if(rs.next()) {
        return rs.getInt(1) > 0;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  public boolean editarClienteExiste(String cpf, String rg, int id) {
    String sql = "SELECT idCliente FROM cliente WHERE cpf = ? AND rg = ?";
    PreparedStatement ps = null;
    try {
      ps = Conexao.getConexao().prepareStatement(sql);
      ps.setString(1, cpf);
      ps.setString(2, rg);
      ResultSet rs = ps.executeQuery();
      if(rs.next()) {
        return rs.getInt(1) == id;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  public void desativarCliente(int id) {
    String sql = "UPDATE cliente SET estado = ? WHERE idCliente = ?";
    PreparedStatement ps = null;
    try {
      ps = Conexao.getConexao().prepareStatement(sql);
      ps.setString(1, "inativo");
      ps.setInt(2, id);
      ps.execute();
      ps.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void ativarCliente(int id) {
    String sql = "UPDATE cliente SET estado = ? WHERE idCliente = ?";
    PreparedStatement ps = null;
    try {
      ps = Conexao.getConexao().prepareStatement(sql);
      ps.setString(1, "ativo");
      ps.setInt(2, id);
      ps.execute();
      ps.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
