package models.cidade;

public class Cidade{
    int id_cidade;
    String nome_cidade;
    String fk_estado;

    public Cidade(int id_cidade, String nome_cidade, String fk_estado){
        this.id_cidade = id_cidade;
        this.nome_cidade = nome_cidade;
        this.fk_estado = fk_estado;
    }

	public int getId_cidade() {
		return id_cidade;
	}

	public String getNome_cidade() {
		return nome_cidade;
	}

	public String getFk_estado() {
		return fk_estado;
	}

}
