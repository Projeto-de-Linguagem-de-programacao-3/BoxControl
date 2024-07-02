package main.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

  private static final String url = "jdbc:mysql://localhost:3306/mercado";
  private static final String user = "root";
  private static final String password = "Eletromec021@";

  private static Connection conn;

  public static Connection getConexao() {
    try {
      if(conn == null) {
        conn = DriverManager.getConnection(url, user, password);
        return conn;
      } else {
        return conn;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
