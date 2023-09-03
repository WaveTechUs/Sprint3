package util;

import static java.lang.Integer.parseInt;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

import dao.DAO;
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
					apagarEstado();
					break;
				case 5:
					cadastrarCidade();
					break;
				case 6:
					pesquisarCidade();
					break;
				case 7:
					alterarCidade();
					break;
				case 8:
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
			dao.adicionarEstado(Estado);
//			if (dao.adicionarEstado(Estado)) {
//				showMessageDialog(null, "Estado registrada com sucesso");
//			} else {
//				showMessageDialog(null, "Estado já registrada");
//			}

		} catch (NumberFormatException e) {
			showMessageDialog(null, "Opção inválida");
			return;
		}
	}

	private void pesquisarEstado() {
		long cnpj = parseInt(showInputDialog("CNPJ"));

//		RetornoEstado retorno = armazenamento.pesquisarEstado(cnpj);
//
//		if (retorno.isOk()) {
//			Estado Estado = retorno.getDado();
//			showMessageDialog(null, Estado.getDados());
//		} else {
//			showMessageDialog(null, "Estado não encontrada");
//		}
	}

	private void alterarEstado() {
		long cnpj = parseInt(showInputDialog("CNPJ a ser alterado (Sem pontuação)"));
		long cnpjNovo = parseInt(showInputDialog("CNPJ novo (Sem pontuação)"));
		String nomeNovo = showInputDialog("Nome novo");
		stringVazia(nomeNovo);
		String senhaNova = showInputDialog("Senha novo");
		stringVazia(senhaNova);

//		RetornoEstado retorno = armazenamento.alterarEstado(cnpj, cnpjNovo, nomeNovo, senhaNova);
//		if (retorno.isOk()) {
//			showMessageDialog(null, "Estado alterada");
//		} else {
//			showMessageDialog(null, "Estado não encontrada");
//		}
	}

	private void apagarEstado() {
		long cnpj = parseInt(showInputDialog("CNPJ (Sem pontuação)"));

//		RetornoEstado retorno = armazenamento.apagarEstado(cnpj);
//
//		if (retorno.isOk()) {
//			showMessageDialog(null, "Estado foi apagada");
//		} else {
//			showMessageDialog(null, "Estado não foi encontrada");
//		}
	}

	private void cadastrarCidade() {
		try {
			String nome = showInputDialog("Nome");
			stringVazia(nome);
			long cnpj = parseInt(showInputDialog("CNPJ (Sem pontuação)"));
			String senha = showInputDialog("Senha");
			stringVazia(senha);
			String endereco = showInputDialog("Endereco");
			stringVazia(endereco);
			String descricao = showInputDialog("Descrição dos alimentos");
			stringVazia(descricao);
			double peso = Double.parseDouble(showInputDialog("Peso(kg) dos alimentos"));
			int statusRetirada = parseInt(showInputDialog("Retirada disponível? (1) Sim  - (2) Não"));
			boolean statusRetiradaBool;

			if (statusRetirada == 1) {
				statusRetiradaBool = true;
			} else if (statusRetirada == 2) {
				statusRetiradaBool = false;
			} else {
				throw new NumberFormatException("Opção inválida");
			}

//			Alimentos alimentos = new Alimentos(descricao, peso);
//			Cidade restaurante = new Cidade(nome, cnpj, senha, statusRetiradaBool, alimentos, endereco);
//
//			if (armazenamento.adicionarCidade(restaurante)) {
//				showMessageDialog(null, "Cidade registrado com sucesso");
//			} else {
//				showMessageDialog(null, "Cidade já registrado");
//			}

		} catch (NumberFormatException e) {
			showMessageDialog(null, "Opção inválida");
			return;
		}
	}

	private void pesquisarCidade() {
		long cnpj = parseInt(showInputDialog("CNPJ"));

//		RetornoCidade retorno = armazenamento.pesquisarCidade(cnpj);
//
//		if (retorno.isOk()) {
//			Cidade restaurante = retorno.getDado();
//			showMessageDialog(null, restaurante.getDados());
//		} else {
//			showMessageDialog(null, "Cidade não encontrado");
//		}
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

//		RetornoCidade retorno = armazenamento.alterarCidade(cnpj, cnpjNovo, nomeNovo, senhaNova, enderecoNovo,
//				descricaoNovo, pesoNovo, statusRetiradaBoolNovo);
//		if (retorno.isOk()) {
//			showMessageDialog(null, "Cidade alterado");
//		} else {
//			showMessageDialog(null, "Cidade não encontrado");
//		}
	}

	private void apagarCidade() {
		long cnpj = parseInt(showInputDialog("CNPJ (Sem pontuação)"));

//		RetornoCidade retorno = armazenamento.apagarCidade(cnpj);
//
//		if (retorno.isOk()) {
//			showMessageDialog(null, "Cidade foi apagado");
//		} else {
//			showMessageDialog(null, "Cidade não foi encontrado");
//		}
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
