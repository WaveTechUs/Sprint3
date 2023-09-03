package util;

import static java.lang.Integer.parseInt;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

import dao.DAO;
import models.cidade.Cidade;
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
			if (opcao < 1 || opcao > 9) {
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
				}
			}
		} while (opcao != 9);
	}

	private void cadastrarEstado() {
		try {
			String estado = showInputDialog("Nome do estado");
			String uf = showInputDialog("Uf do estado");
			stringVazia(estado);
			stringVazia(uf);
			
			Estado Estado = new Estado(uf,estado);
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

	private void apagarEstado() {
		try {
			String uf = showInputDialog("UF do estado");
			stringVazia(uf);
			if (dao.apagarEstado(uf)) {
				showMessageDialog(null, "Estado apagado com sucesso");
			} else {
				showMessageDialog(null, "Estado ocorreu um erro ao apagar o estado");
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
		long cnpj = parseInt(showInputDialog("CNPJ"));

	}

	private void alterarCidade() {
		long cnpj = parseInt(showInputDialog("CNPJ a ser alterado (Sem pontuação)"));
		String nomeNovo = showInputDialog("Nome");
		stringVazia(nomeNovo);
		long cnpjNovo = parseInt(showInputDialog("CNPJ (Sem pontuação)"));
		String senhaNova = showInputDialog("Senha");
		stringVazia(senhaNova);
		String enderecoNovo = showInputDialog("Endereco");
		stringVazia(enderecoNovo);
		String descricaoNovo = showInputDialog("Descrição dos alimentos");
		stringVazia(descricaoNovo);
		double pesoNovo = Double.parseDouble(showInputDialog("Peso(kg) dos alimentos"));
		int statusRetirada = parseInt(showInputDialog("Retirada disponível? (1) Sim  - (2) Não"));
		boolean statusRetiradaBoolNovo;

		if (statusRetirada == 1) {
			statusRetiradaBoolNovo = true;
		} else if (statusRetirada == 2) {
			statusRetiradaBoolNovo = false;
		} else {
			throw new NumberFormatException("Opção inválida");
		}

	}

	private void apagarCidade() {
		long cnpj = parseInt(showInputDialog("CNPJ (Sem pontuação)"));
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
		aux += "4. Apagar estado\n";
		aux += "5. Cadastrar cidade\n";
		aux += "6. Pesquisar cidade\n";
		aux += "7. Alterar cidade\n";
		aux += "8. Apagar cidade\n";
		aux += "9. Encerrar programa\n";
		return aux;
	}
}
