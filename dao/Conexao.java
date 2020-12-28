package dao;
/**
 * @author Mauro Raibida
 * @version 1.0
 */
import interfaceDAO.IConst;
import java.sql.*;

public class Conexao implements IConst {
    public static Connection getConexao(String stringDeConexao, String usuario, String senha) {
    try {
	  return DriverManager.getConnection(stringDeConexao, usuario, senha);
    } catch (SQLException e) {		
        throw new RuntimeException(e);
      } 
  }
}
