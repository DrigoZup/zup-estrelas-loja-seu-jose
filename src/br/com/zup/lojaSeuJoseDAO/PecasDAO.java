package br.com.zup.lojaSeuJoseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.zup.factory.ConnectionFactory;
import br.com.zup.lojaSeuJoseEnums.Categoria;
import br.com.zup.pojo.Peca;

public class PecasDAO {
	
	private Connection conexao;
	
	public PecasDAO() {
		this.conexao = new ConnectionFactory().getConnection();
	}
	
	private static void construtorLista(ResultSet rs, List<Peca> listaPecas) throws SQLException {
		
		while (rs.next()) {
			Peca peca = new Peca();
			
			peca.setCodigoBarras(rs.getInt("codigo_de_barras"));
			peca.setNome(rs.getString("nome"));
			peca.setModeloCarro(rs.getString("modelo_do_carro"));
			peca.setFabricante(rs.getString("fabricante"));
			peca.setPrecoCusto(rs.getDouble("preco_de_custo"));
			peca.setPrecoVenda(rs.getDouble("preco_de_venda"));
			peca.setQuantidadeEstoque(rs.getInt("quantidade_em_estoque"));
			peca.setCategoria(Categoria.valueOf(rs.getString("categoria")));
			listaPecas.add(peca);
		}
	}
	
	public boolean inserirPeca(Peca peca) {
		
		String insertSQL = "insert into pecas"
				+ "(codigo_de_barras, nome, modelo_do_carro, fabricante, preco_de_custo, preco_de_venda,"
				+ "quantidade_em_estoque, categoria)"
				+ "value"
				+ "(?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement comando = this.conexao.prepareStatement(insertSQL);
			
			comando.setInt(1, peca.getCodigoBarras());
			comando.setString(2, peca.getNome());
			comando.setString(3, peca.getModeloCarro());
			comando.setString(4, peca.getFabricante());
			comando.setDouble(5, peca.getPrecoCusto());
			comando.setDouble(6, peca.getPrecoVenda());
			comando.setInt(7, peca.getQuantidadeEstoque());
			comando.setString(8, peca.getCategoria().toString());
			
			comando.execute();
			comando.close();
		} catch (SQLException e) {
			System.err.println("Não foi possível cadastrar esse produto");
			System.err.println(e.getMessage());
			return false;
		}
		
		return true;
	}
	
	public List<Peca> listaPecasEstoque() throws SQLException{
		
		List<Peca> listaPecas = new ArrayList<>();
		String listaSQL = "select* from pecas";
		
		try {
			PreparedStatement comando = this.conexao.prepareStatement(listaSQL);
			ResultSet resultadoConsulta = comando.executeQuery();
			
			construtorLista(resultadoConsulta, listaPecas);
			comando.close();
		} catch (SQLException e) {
			System.err.println("Não foi possível listar suas peças...");
			System.err.println(e.getMessage());
		}
		return listaPecas;
	}
	
	public Peca buscaPecaByCodigo(int codBarras) throws SQLException {
		Peca pecaEncontrada = new Peca();
		
		String pesquisaSQL = "select* from pecas where codigo_de_barras = ?";
		
		try {
			PreparedStatement comando = this.conexao.prepareStatement(pesquisaSQL);
			comando.setInt(1, codBarras);
			
			ResultSet resultadoConsulta = comando.executeQuery();
			while (resultadoConsulta.next()) {
				pecaEncontrada.setCodigoBarras(resultadoConsulta.getInt("codigo_de_barras"));
				pecaEncontrada.setNome(resultadoConsulta.getString("nome"));
				pecaEncontrada.setModeloCarro(resultadoConsulta.getString("modelo_do_carro"));
				pecaEncontrada.setFabricante(resultadoConsulta.getString("fabricante"));
				pecaEncontrada.setPrecoCusto(resultadoConsulta.getDouble("preco_de_custo"));
				pecaEncontrada.setPrecoVenda(resultadoConsulta.getDouble("preco_de_venda"));
				pecaEncontrada.setQuantidadeEstoque(resultadoConsulta.getInt("quantidade_em_estoque"));
				pecaEncontrada.setCategoria(Categoria.valueOf(resultadoConsulta.getString("categoria")));
			}
			comando.close();
		} catch (SQLException e) {
			System.err.println("Peça não encontrada...");
			System.err.println(e.getMessage());
		}
		
		return pecaEncontrada;
	}

	public List<Peca> buscaPecaByTrechoNome(String trechoNome) throws SQLException {
		
		List<Peca> listaPecas = new ArrayList<>();
		
		String consultaSQL = "select* from pecas where nome like ?";
		
		try {
			
			PreparedStatement comando = this.conexao.prepareStatement(consultaSQL);
			comando.setString(1, trechoNome+"%");
			
			ResultSet resultadoConsulta = comando.executeQuery();
			
			construtorLista(resultadoConsulta, listaPecas);
			comando.close();
		} catch (SQLException e) {
			System.err.println("Nenhuma cidade encontrada...");
			System.err.println(e.getMessage());
		}
		return listaPecas;
	}

	public List<Peca> buscaPecaCarroEspecifico(String nomeCarro) throws SQLException {
		
		List<Peca> listaPecas = new ArrayList<>();
		String consultaSQL = "select* from pecas where modelo_do_carro = ?";
		
		try {
			
			PreparedStatement comando = this.conexao.prepareStatement(consultaSQL);
			comando.setString(1, nomeCarro);
			
			ResultSet resultadoConsulta = comando.executeQuery();
			
			construtorLista(resultadoConsulta, listaPecas);
			
			comando.close();
		} catch (SQLException e) {
			System.err.println("Não encontramos nenhuma peça para esse carro");
			System.err.println(e.getMessage());
		}
		return listaPecas;
	}

	public List<Peca> buscaPecaByCategoria(String categoria) throws SQLException {
		
		List<Peca> listaPecas = new ArrayList<>();
		String consultaSQL = "select* from pecas where categoria = ?";
		
		try {
			
			PreparedStatement comando = this.conexao.prepareStatement(consultaSQL);
			comando.setString(1, categoria);
			
			ResultSet resultadoConsulta = comando.executeQuery();
			
			construtorLista(resultadoConsulta, listaPecas);
			
			comando.close();
		} catch (SQLException e) {
			System.err.println("Não encontramos nenhuma peça para esse carro");
			System.err.println(e.getMessage());
		}
		return listaPecas;
	}

	public boolean removePeca(int codigoBarras) throws SQLException {
		
		String removeSQL = "delete from pecas where codigo_de_barras = ?";
		
		try {
			PreparedStatement comando = this.conexao.prepareStatement(removeSQL);
			comando.setInt(1, codigoBarras);
			
			comando.execute();
		} catch (SQLException e) {
			System.err.println("Impossível deletar este item");
			System.err.println(e.getMessage());
			return false;
		}
		
		return true;
	}

	public boolean atualizaEstoque(int qtdAtualEstoque, int codigoBarras) throws SQLException {
		
		String attEstoqueSQL = "update pecas set quantidade_em_estoque = ? where codigo_de_barras = ?";
		
		try {
			
			PreparedStatement comando = this.conexao.prepareStatement(attEstoqueSQL);
			comando.setInt(1, qtdAtualEstoque);
			comando.setInt(2, codigoBarras);
			comando.execute();
			
		} catch (SQLException e) {
			System.err.println("Erro ao dar baixa na peça");
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	
	
	
	
	
	
	
}
