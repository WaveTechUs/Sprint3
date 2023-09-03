package models.cliente;


public class Cliente {
	private int id_cliente;
	private String nome;
	private String cpf;
	private int idade;
	private int fkCidade;

	public Cliente(int id, String nome, String cpf, int idade, int fkEndereco) {
		super();
		this.id_cliente = id;
		this.nome = nome;
		this.cpf = cpf;
		this.idade = idade;
		this.fkCidade = fkEndereco;
	}
	
	public int getId() {
		return id_cliente;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public int getIdade() {
		return idade;
	}

	public int getFkCidade() {
		return fkCidade;
	}

}
