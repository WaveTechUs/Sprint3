package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import db.connection.DbConnection;
import models.estado.Estado;

public class DAO {
	DbConnection db = new DbConnection();
	
	public boolean adicionarEstado(Estado estado) {
		boolean retorno = false;
		String sql = "INSERT INTO ESTADO VALUES (?,?)";
		Connection conexao = db.conectar();
		try {
			PreparedStatement p = conexao.prepareStatement(sql);
			p.setString(1, estado.getUf());
			p.setString(2, estado.getNomeEstado());
			int i = p.executeUpdate();
		} catch (Exception ex){
			retorno = false;
		} finally {
			db.close();
		}
		return retorno;
	}
}
