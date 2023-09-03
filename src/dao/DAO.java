package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.connection.DbConnection;
import models.cidade.Cidade;
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
			retorno = true;
		} catch (Exception ex){
			retorno = false;
		} finally {
			db.close();
		}
		return retorno;
	}
	
	public String pesquisarEstado(String uf) {
		String retorno = "Nenhum estado com esse UF registrado";
		String sql = "SELECT * FROM ESTADO WHERE UF = ? ";
		Connection conexao = db.conectar();
		try {
			PreparedStatement p = conexao.prepareStatement(sql);
			p.setString(1, uf);
			ResultSet rs = p.executeQuery();
			String ufRetorno = "";
			String estadoRetorno = "";
			while(rs.next()) {
				ufRetorno = rs.getString("Uf");
				estadoRetorno = rs.getString("NOME_ESTADO");
			}		
			if(ufRetorno.length() == 0 || estadoRetorno.length() == 0){
				retorno = "Nenhum estado com esse UF registrado";
			} else {
				retorno = ufRetorno + "\n";
				retorno += estadoRetorno + "\n";
			}
		} catch (Exception ex){
			retorno = "Ocorreu um erro";
		} finally {
			db.close();
		}
		return retorno;	
	}
	
	public boolean alterarEstado(String uf, String nomeEstado){
		boolean retorno = false;
		String sql = "UPDATE ESTADO SET NOME_ESTADO = ? WHERE UF = ?";
		Connection conexao = db.conectar();
		try {
			PreparedStatement p = conexao.prepareStatement(sql);
			p.setString(1, nomeEstado);
			p.setString(2, uf);
			int i = p.executeUpdate();
			retorno = true;
			if(i == 0) {
				retorno = false;
			}
		} catch (Exception ex){
			retorno = false;
		} finally {
			db.close();
		}
		return retorno;
	} 
	
	public boolean apagarEstado(String uf){
		boolean retorno = false;
		String sql = "DELETE FROM ESTADO WHERE UF = ?";
		Connection conexao = db.conectar();
		try {
			PreparedStatement p = conexao.prepareStatement(sql);
			p.setString(1, uf);
			int i = p.executeUpdate();
			retorno = true;
		} catch (Exception ex){
			retorno = false;
		} finally {
			db.close();
		}
		return retorno;
	}
	
	public boolean adicionarCidade(Cidade cidade){
		boolean retorno = false;
		String sqlIdEstado = "SELECT ID_ESTADO FROM ESTADO WHERE UF = ?";
		Connection conexao = db.conectar();
		try {
			PreparedStatement p = conexao.prepareStatement(sqlIdEstado);
			p.setString(1, cidade.getFk_estado());
			ResultSet rs = p.executeQuery();
			int idEstado = 0;
			while(rs.next()) {
				idEstado = rs.getInt("ID_ESTADO");
			}
			String sql = "INSERT INTO CIDADE VALUES (?,?)";
			p = conexao.prepareStatement(sql);
			p.setString(1, cidade.getNome_cidade());
			p.setInt(2, idEstado);
			int i = p.executeUpdate();
			retorno = true;
		} catch (Exception ex){
			retorno = false;
		} finally {
			db.close();
		}	
		return retorno;
	}	

}
