package br.com.zup.lojaSeuJoseDAO;

import java.sql.SQLException;

import br.com.zup.pojo.Peca;
import br.com.zup.pojo.Venda;

public class VendaDAO {
	

	
	public VendaDAO() {		
	}

	public Venda realizarVenda(int codigoBarras, int qtdComprada) throws SQLException {
		
		PecasDAO pecaDB = new PecasDAO();
		Peca pecaVendida = pecaDB.buscaPecaByCodigo(codigoBarras);
		
		Venda venda = new Venda();
		
		if (pecaVendida != null) {	
			venda.setCodigoBarras(pecaVendida.getCodigoBarras());
			venda.setNomeProduto(pecaVendida.getNome());
			venda.setQtdItensComprados(qtdComprada);
			venda.setValorCompra(pecaVendida.getPrecoVenda()*qtdComprada);
			
			try {
				
				int estoqueAtualizado = pecaVendida.getQuantidadeEstoque() - qtdComprada;
				pecaVendida.setQuantidadeEstoque(estoqueAtualizado);
				pecaDB.atualizaEstoque(estoqueAtualizado, codigoBarras);
				
				} catch (SQLException e) {
					System.err.println("Falha na Venda...");
					System.err.println(e.getMessage());
				}
		} else {
			System.out.println("N�o temos essa pe�a no nosso estoque...");
		}
		
		return venda;
	}

	
}
