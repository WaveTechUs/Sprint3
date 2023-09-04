package util;

import static java.lang.Integer.parseInt;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

import dao.DAO;
import models.cidade.Cidade;
import models.cliente.Cliente;
import models.estado.Estado;

public class Util {
//	Armazenamento armazenamento = new Armazenamento();
	private static DAO dao = new DAO();

	public void menu() {
		boolean excecao;

		do {
			excecao = false;
			try {
				perguntaMenu();
			} catch (NumberFormatException e) {
				showMessageDialog(null, "Opção inválida");
				excecao = true;
			}
		} while (excecao);
	}

	private void perguntaMenu() {
		int opcao;

		do {
			opcao = parseInt(showInputDialog(gerarMenu()));
			if (opcao < 1 || opcao > 10) {
				showMessageDialog(null, "Opção inválida");
			} else {
				switch (opcao) {
				case 1:
					cadastrarEstado();
					break;
				case 2:
					pesquisarEstado();
					break;
				case 3:
					alterarEstado();
					break;
				case 4:
					cadastrarCidade();
					break;
				case 5:
					pesquisarCidade();
					break;
				case 6:
					alterarCidade();
					break;
				case 7:
					apagarCidade();
					break;
				case 8:
					cadastrarCliente();
					break;
				case 9:
					pesquisarCliente();
					break;
				}
			}
		} while (opcao != 10);
	}

	private void cadastrarEstado() {
		try {
			String estado = showInputDialog("Nome do estado");
			String uf = showInputDialog("Uf do estado");
			stringVazia(estado);
			stringVazia(uf);

			Estado Estado = new Estado(uf, estado);
			if (dao.adicionarEstado(Estado)) {
				showMessageDialog(null, "Estado registrada com sucesso");
			} else {
				showMessageDialog(null, "Erro ao cadastrar estado");
			}

		} catch (NumberFormatException e) {
			showMessageDialog(null, "Opção inválida");
			return;
		}
	}

	private void pesquisarEstado() {
		String uf = showInputDialog("UF do estado");
		String retornoPesquisa = dao.pesquisarEstado(uf);
		showMessageDialog(null, retornoPesquisa);
	}

	private void alterarEstado() {
		try {
			String uf = showInputDialog("UF do estado");
			String estado = showInputDialog("Novo nome do estado");
			stringVazia(uf);
			stringVazia(estado);
			if (dao.alterarEstado(uf, estado)) {
				showMessageDialog(null, "Estado alterado com sucesso");
			} else {
				showMessageDialog(null, "Estado ocorreu um erro ao alterar o estado");
			}

		} catch (NumberFormatException e) {
			showMessageDialog(null, "Opção inválida");
			return;
		}
	}

	private void cadastrarCidade() {
		try {
			String nome = showInputDialog("Nome da cidade");
			String uf = showInputDialog("UF");
			stringVazia(nome);
			stringVazia(uf);
			Cidade cidade = new Cidade(nome, uf);
			if (dao.adicionarCidade(cidade)) {
				showMessageDialog(null, "Estado registrada com sucesso");
			} else {
				showMessageDialog(null, "Erro ao cadastrar estado");
			}
		} catch (NumberFormatException e) {
			showMessageDialog(null, "Opção inválida");
			return;
		}
	}

	private void pesquisarCidade() {
		String cidade = showInputDialog("Nome da cidade");
		String retornoPesquisa = dao.pesquisarCidade(cidade);
		showMessageDialog(null, retornoPesquisa);
	}

	private void alterarCidade() {
		try {
			String nome = showInputDialog("Nome da cidade");
			String novoNome = showInputDialog("Novo nome da cidade");
			stringVazia(novoNome);
			stringVazia(nome);
			if (dao.alterarCidade(nome, novoNome)) {
				showMessageDialog(null, "Cidade alterada com sucesso");
			} else {
				showMessageDialog(null, "Ocorreu um erro ao alterar a cidade");
			}
		} catch (NumberFormatException e) {
			showMessageDialog(null, "Opção inválida");
			return;
		}
	}

	private void apagarCidade() {
		try {
			String nome = showInputDialog("Nome da cidade");
			stringVazia(nome);
			if (dao.apagarCidade(nome)) {
				showMessageDialog(null, "Cidade apagada com sucesso");
			} else {
				showMessageDialog(null, "Ocorreu um erro ao apagar a cidade");
			}
		} catch (NumberFormatException e) {
			showMessageDialog(null, "Opção inválida");
			return;
		}
	}

	private void cadastrarCliente() {
		try {
			String nome = showInputDialog("Nome do cliente");
			String cpf = showInputDialog("CPF do cliente");
			int idade = parseInt(showInputDialog("Idade do cliente"));
			String cidade = showInputDialog("Cidade do cliente");
			stringVazia(nome);
			stringVazia(cpf);
			stringVazia(cidade);
			if (dao.adicionarCliente(nome, cpf, idade, cidade)) {
				showMessageDialog(null, "Cliente cadastrado com sucesso");
			} else {
				showMessageDialog(null, "Ocorreu um erro ao cadastrar o cliente");
			}
		} catch (NumberFormatException e) {
			showMessageDialog(null, "Opção inválida");
			return;
		}
	}
	
	private void pesquisarCliente(){
		String nome = showInputDialog("Nome do cliente");
		String retornoPesquisa = dao.pesquisarCliente(nome);
		showMessageDialog(null, retornoPesquisa);
	}

	private void stringVazia(String input) {
		if (input.length() == 0)
			throw new NumberFormatException("Opção inválida");
	}

	private String gerarMenu() {
		String aux = "Menu de ações\n";
		aux += "1. Cadastrar estado\n";
		aux += "2. Pesquisar estado\n";
		aux += "3. Alterar estado\n";
		aux += "4. Cadastrar cidade\n";
		aux += "5. Pesquisar cidade\n";
		aux += "6. Alterar cidade\n";
		aux += "7. Apagar cidade\n";
		aux += "8. Cadastrar cliente\n";
		aux += "9. Pesquisar cliente\n";
		aux += "10. Encerrar programa\n";
		return aux;
	}
}
