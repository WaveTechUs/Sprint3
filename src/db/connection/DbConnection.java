package db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	private static Connection conexao;
	public Connection conectar() {
		String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
        String nomeUsuario = "rm93102";
        String senha = "140104";

        try {
        	
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conexao = DriverManager.getConnection(url, nomeUsuario, senha);
            
        } catch (ClassNotFoundException e) {
            System.err.println("Erro ao carregar o driver JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        
        return conexao;
	}
	
	public void close() {
		try {
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
