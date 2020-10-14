package br.com.zup.pojo;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import br.com.zup.lojaSeuJoseDAO.PecasDAO;

public class Principal {

	public static final String MENUPRINCIPAL=(
			"===== Sistema de Gerenciamento =====\n\n" 
				+ "Escolha uma op��o\n"
				+ "1 - Gerenciamento de Mercadoria\n" 
				+ "2 - Gerenciamento de Vendas\n" 
				+ "0 - Sair"
			);
	
	public static final String MENUGERENCIAESTOQUE=(
			"===== Controle de Estoque =====\n\n" 
					+ "Escolha uma op��o\n" 
					+ "1 - Cadastrar nova Pe�a\n"
					+ "2 - Listagem de Estoque\n"
					+ "3 - Busca Pe�a pelo c�digo de barras\n"
					+ "4 - Busca Pe�a por inicio do nome\n"
					+ "5 - Busca Pe�a de carro espec�fico\n"
					+ "6 - Busca Pe�a pela categoria\n"
					+ "7 - Remover Pe�as\n"
					+ "0 - Voltar ao Menu Principal"
			);
	
	public static void cadastraPeca(Scanner teclado) throws SQLException {

		System.out.println("Digite o codigo de barra do produto a ser cadastrado:");
		int codBarra = teclado.nextInt();
		System.out.println("Digite o nome da pe�a:");
		String nome = teclado.next();
		System.out.println("Para qual carro � essa pe�a?");
		String carroPeca = teclado.next();
		System.out.println("Qual a marca da pe�a?");
		String marca = teclado.next();
		System.out.println("Digite o pre�o de custo: ");
		double precoCusto = teclado.nextDouble();
		System.out.println("Digite o pre�o de venda: ");
		double precoVenda = teclado.nextDouble();
		System.out.println("Quantas pe�as dessa voc� tem para o estoque?");
		int estoque = teclado.nextInt();
		System.out.println("Essa pe�a pertence a qual categoria?");
		String categoria = teclado.next();

		Peca pecaCadastrada = new Peca();
		pecaCadastrada.setCodigoBarras(codBarra);
		pecaCadastrada.setNome(nome);
		pecaCadastrada.setModeloCarro(carroPeca);
		pecaCadastrada.setFabricante(marca);
		pecaCadastrada.setPrecoCusto(precoCusto);
		pecaCadastrada.setPrecoVenda(precoVenda);
		pecaCadastrada.setQuantidadeEstoque(estoque);
		pecaCadastrada.setCategoria(categoria);

		PecasDAO pecaDao = new PecasDAO();
		pecaDao.inserirPeca(pecaCadastrada);

		System.out.println("A pe�a " + nome + " foi inserida no banco de dados!\n");
	}

	public static void listaPecasBancoDados() throws SQLException {
		
		PecasDAO pecasDB = new PecasDAO();
		List<Peca> listagem = pecasDB.listaPecasEstoque();
		
		for (Peca peca : listagem) {
			
			System.out.println(peca.mostraDados());
		}
	}
	
	public static void buscaPecaByCodigo(Scanner teclado) throws SQLException {
		
		System.out.println("Qual o c�digo de barras do produto que\n"
				+ " voc� quer encontrar?");
		int codigoProcurado = teclado.nextInt();
		
		PecasDAO pecaProcurada = new PecasDAO();
		Peca pecaEncontrada = pecaProcurada.buscaPecaByCodigo(codigoProcurado);
		
		System.out.println(pecaEncontrada.mostraDados());
	}

	public static void buscaPecasByTrechoNome(Scanner teclado) throws SQLException {
		System.out.println("Digite a(s) letra(s) iniciais do nome da Pe�a: ");
		String trechoNomePeca = teclado.next();
		
		PecasDAO pecasDB = new PecasDAO();
		List<Peca> listagem = pecasDB.buscaPecaByTrechoNome(trechoNomePeca);
		
		for (Peca pecas : listagem) {
			System.out.println(pecas.mostraDados());
		}	
	}
	
	public static void buscaPecasCarroEspecifico(Scanner teclado) throws SQLException {
		System.out.println("Digite o nome do carro: ");
		String nomeCarro = teclado.next();
		
		PecasDAO pecasDB = new PecasDAO();
		List<Peca> listagem = pecasDB.buscaPecaCarroEspecifico(nomeCarro);
		
		for (Peca pecas : listagem) {
			System.out.println(pecas.mostraDados());
		}
	}

	public static void buscaPecasByCategoria(Scanner teclado) throws SQLException {
		System.out.println("Qual a categoria de pe�as que deseja encontrar?");
		String categoria = teclado.next();
		
		PecasDAO pecasDB = new PecasDAO();
		List<Peca> listagem = pecasDB.buscaPecaByCategoria(categoria);
		
		for (Peca pecas : listagem) {
			System.out.println(pecas.mostraDados());
		}
	}
	
	public static void removerPecas(Scanner teclado) throws SQLException {
		
		System.out.println("Digite o codigo de barras da pe�a que deseja remover: ");
		int codigoBarras = teclado.nextInt();
		
		PecasDAO pecaDB = new PecasDAO();
		pecaDB.removePeca(codigoBarras);
		System.out.println("A pe�a referente ao c�digo de barra "+codigoBarras+" foi removida do banco de dados.");
	}
	
	public static void main(String[] args) throws SQLException {

		Scanner teclado = new Scanner(System.in);
		int opcaoMenuPrincipal = 0;

		do {
			System.out.println(MENUPRINCIPAL);
			opcaoMenuPrincipal = teclado.nextInt();
			switch (opcaoMenuPrincipal) {
			
			case 1: {
				int opcaoMenuEstoque = 0;
				do {
					System.out.println(MENUGERENCIAESTOQUE);
					opcaoMenuEstoque = teclado.nextInt();
					switch (opcaoMenuEstoque) {
					
					case 1: {
		
						cadastraPeca(teclado);
						break;
					}
					
					case 2: {
						
						listaPecasBancoDados();
						break;
					}
					
					case 3: {
												
						buscaPecaByCodigo(teclado);
						break;
					}
					
					case 4: {
						
						buscaPecasByTrechoNome(teclado);
						break;
					}
					
					case 5: {
						
						buscaPecasCarroEspecifico(teclado);
						break;
					}
					
					case 6: {
						
						buscaPecasByCategoria(teclado);
						break;
					}
					
					case 7: {
						
						removerPecas(teclado);
						break;
					}
					
					case 0: {
						main(args);
						return;
					}
					default:
						System.out.println("Op��o Inv�lida");
					}
				}while (opcaoMenuEstoque != 0);
			}
			
			case 0: {
				System.out.println("O programa foi encerrado");
				break;
			}
			
			default:
				System.out.println("Op��o Inv�lida");
			}
		} while(opcaoMenuPrincipal != 0);
		
		teclado.close();
	}
}
