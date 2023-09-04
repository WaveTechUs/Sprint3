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
        
        String temEstado = pesquisarEstado(cidade.getFk_estado());
			if(temEstado.length() == 36) {
        	return false;
        }

        Connection conexao = db.conectar();
        
        try {
        	String sql = "INSERT INTO CIDADE (NOME_CIDADE, UF) VALUES (?, ?)";
        	PreparedStatement pCidade = conexao.prepareStatement(sql);
        	pCidade.setString(1, cidade.getNome_cidade());
        	pCidade.setString(2, cidade.getFk_estado());
        	int i = pCidade.executeUpdate();
        	if (i > 0) {
        		retorno = true;
        	}
        } catch (Exception ex){
        	System.out.println("Erro no adicionar cidade");
        } finally {
            db.close();
        }    
        return retorno;
    }    	
	
	public String pesquisarCidade(String nomeCidade) {
		String retorno = "Nenhuma cidade com esse nome registrado";
		String sql = "SELECT * FROM CIDADE WHERE NOME_CIDADE = ? ";
		Connection conexao = db.conectar();
		try {
			PreparedStatement p = conexao.prepareStatement(sql);
			p.setString(1, nomeCidade);
			ResultSet rs = p.executeQuery();
			String nomeCidadeRetorno = "";
			String ufRetorno = "";
			while(rs.next()) {
				nomeCidadeRetorno = rs.getString("NOME_CIDADE");
				ufRetorno = rs.getString("UF");
			}		
			if(nomeCidadeRetorno.length() == 0 || ufRetorno.length() == 0){
				retorno = "Nenhuma cidade com esse nome registrado";
			} else {
				retorno = nomeCidadeRetorno + "\n";
				retorno += ufRetorno + "\n";
			}
		} catch (Exception ex){
			retorno = "Ocorreu um erro";
		} finally {
			db.close();
		}
		return retorno;	
	}
	
	public boolean alterarCidade(String nome, String novoNome){
		boolean retorno = false;
		String sql = "UPDATE CIDADE SET NOME_CIDADE = ? WHERE NOME_CIDADE = ?";
		Connection conexao = db.conectar();
		try {
			PreparedStatement p = conexao.prepareStatement(sql);
			p.setString(1, novoNome);
			p.setString(2, nome);
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
	
	public boolean apagarCidade(String nome){
		boolean retorno = false;
		String sql = "DELETE FROM CIDADE WHERE NOME_CIDADE = ?";
		Connection conexao = db.conectar();
		try {
			PreparedStatement p = conexao.prepareStatement(sql);
			p.setString(1, nome);
			int i = p.executeUpdate();
			retorno = true;
		} catch (Exception ex){
			retorno = false;
		} finally {
			db.close();
		}
		return retorno;
	}

	public boolean adicionarCliente(String nome, String cpf, int idade, String cidade){
		boolean retorno = false;
		String temEstado = pesquisarCidade(cidade);
		if(temEstado.length() == 39) {
			return false;
		}
		Connection conexao = db.conectar();
		try {
			String sqlCidade = "SELECT ID_CIDADE FROM CIDADE WHERE NOME_CIDADE = ? ";
			PreparedStatement pCidade = conexao.prepareStatement(sqlCidade);
			pCidade.setString(1, cidade);
			
			ResultSet rs = pCidade.executeQuery();
			int idCidade = 0;
			while(rs.next()) {
				idCidade = rs.getInt("ID_CIDADE");
			}
			
			String sql = "INSERT INTO CLIENTE (NOME, CPF, IDADE, ID_CIDADE) VALUES (?,?,?,?)";
			PreparedStatement p = conexao.prepareStatement(sql);
			p.setString(1, nome);
			p.setString(2, cpf);
			p.setInt(3, idade);
			p.setInt(4, idCidade);
			int i = p.executeUpdate();
			retorno = true;
		} catch (Exception ex){
			System.out.println("Adicionar cliente");
			retorno = false;
		} finally {
			db.close();
		}
		return retorno;
	}
	
	public String pesquisarCliente(String nome){
		String retorno = "Nenhum cliente com esse nome registrado";
		String sql = "SELECT * FROM CLIENTE WHERE NOME = ? ";
		Connection conexao = db.conectar();
		try {
			PreparedStatement p = conexao.prepareStatement(sql);
			p.setString(1, nome);
			ResultSet rs = p.executeQuery();
			String nomeRetorno = "";
			String cpfRetorno = "";
			int idadeRetorno = 0;
			String cidadeRetorno = "";
			while(rs.next()) {
				nomeRetorno = rs.getString("NOME");
				cpfRetorno = rs.getString("CPF");
				idadeRetorno = rs.getInt("IDADE");
				cidadeRetorno = rs.getString("ID_CIDADE");
			}		
			if(nomeRetorno.length() == 0 || cpfRetorno.length() == 0 || idadeRetorno == 0 || cidadeRetorno.length() == 0){
				retorno = "Nenhum cliente com esse nome registrado";
			} else {
				retorno = nomeRetorno + "\n";
				retorno += cpfRetorno + "\n";
				retorno += idadeRetorno + "\n";
				retorno += cidadeRetorno + "\n";
			}
		} catch (Exception ex){
			retorno = "Ocorreu um erro";
		} finally {
			db.close();
		}
		return retorno;		
	}
}
