package br.com.zup.lojaSeuJoseDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.zup.pojo.Peca;

public class PecasDAO {

	EntityManager manager;

	public PecasDAO() {

		this.manager = Persistence.createEntityManagerFactory("loja_de_pecas").createEntityManager();
	}

	public boolean inserirPeca(Peca peca) {

		try {
			manager.getTransaction().begin();
			manager.persist(peca);
			manager.getTransaction().commit();
			
		} catch (EntityNotFoundException e) {
			System.err.println("N�o foi poss�vel cadastrar esse produto");
			System.err.println(e.getMessage());
			return false;
		}

		return true;
	}

	public List<Peca> listaPecasEstoque() throws SQLException {

		List<Peca> listaPecas = new ArrayList<>();

		try {
			
			Query selecao = manager.createQuery("select pecas from Peca as pecas");
		
			listaPecas = selecao.getResultList();
		
		} catch (EntityNotFoundException e) {
			System.err.println("N�o foi poss�vel listar suas pe�as...");
			System.err.println(e.getMessage());
		}
		return listaPecas;
	}

	public Peca buscaPecaByCodigo(int codBarras) throws SQLException {
		Peca pecaEncontrada = new Peca();

		try {
			
			pecaEncontrada = manager.find(Peca.class, codBarras);
			
		} catch (EntityNotFoundException e) {
			System.err.println("Pe�a n�o encontrada...");
			System.err.println(e.getMessage());
		}

		return pecaEncontrada;
	}

	public List<Peca> buscaPecaByTrechoNome(String trechoNome) throws SQLException {

		List<Peca> listaPecas = new ArrayList<>();

		try {

			Query selecao = manager.createQuery("select p from Peca as p"
					+ " where p.nome like :trechoNome");
		
			selecao.setParameter("trechoNome", trechoNome+"%");
			listaPecas = selecao.getResultList();
		
		} catch (EntityNotFoundException e) {
			System.err.println("Nenhuma pe�a encontrada...");
			System.err.println(e.getMessage());
		}
		return listaPecas;
	}

	public List<Peca> buscaPecaCarroEspecifico(String nomeCarro) throws SQLException {

		List<Peca> listaPecas = new ArrayList<>();

		try {
			Query selecao = manager.createQuery("select p from Peca as p"
					+ " where p.modeloCarro = :modeloCarro");
		
			selecao.setParameter("modeloCarro", nomeCarro);
			listaPecas = selecao.getResultList();
			
		} catch (EntityNotFoundException e) {
			System.err.println("N�o encontramos nenhuma pe�a para esse carro");
			System.err.println(e.getMessage());
		}
		return listaPecas;
	}

	public List<Peca> buscaPecaByCategoria(String categoria) throws SQLException {

		List<Peca> listaPecas = new ArrayList<>();

		try {
			
			Query selecao = manager.createQuery("select p from Peca as p"
					+ " where p.categoria = :categoria");
		
			selecao.setParameter("categoria", categoria);
			listaPecas = selecao.getResultList();
		
		} catch (EntityNotFoundException e) {
			System.err.println("N�o encontramos nenhuma pe�a para esse carro");
			System.err.println(e.getMessage());
		}
		return listaPecas;
	}

	public boolean removePeca(int codigoBarras) throws SQLException {

		Peca pecaRemovida = new Peca();
		
		try {
			
			pecaRemovida = manager.find(Peca.class, codigoBarras);
			manager.getTransaction().begin();
			manager.remove(pecaRemovida);
			manager.getTransaction().commit();
			
		} catch (EntityNotFoundException e) {
			System.err.println("Imposs�vel deletar este item");
			System.err.println(e.getMessage());
			return false;
		}

		return true;
	}

	public boolean atualizaEstoque(int qtdAtualEstoque, int codigoBarras) throws SQLException {

		Peca pecaEstoqueAtualizado = new Peca();
		
		try {
			
			pecaEstoqueAtualizado = manager.find(Peca.class, codigoBarras);
			pecaEstoqueAtualizado.setQuantidadeEstoque(qtdAtualEstoque);
			
			manager.getTransaction().begin();
			manager.merge(pecaEstoqueAtualizado);
			manager.getTransaction().commit();

		} catch (EntityNotFoundException e) {
			System.err.println("Erro ao dar baixa na pe�a");
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}

}
