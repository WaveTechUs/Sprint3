package models.estado;

public class Estado {
	String uf;
	String nomeEstado;
	
	public Estado(String uf, String nome_estado) {
		this.uf = uf;
		this.nomeEstado = nome_estado;
	}

	public String getUf() {
		return uf;
	}

	public String getNomeEstado() {
		return nomeEstado;
	}
}
