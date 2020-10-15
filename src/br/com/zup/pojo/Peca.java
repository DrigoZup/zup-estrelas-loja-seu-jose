package br.com.zup.pojo;

import br.com.zup.lojaSeuJoseEnums.Categoria;

public class Peca {

	private int codigoBarras;
	private String nome;
	private String modeloCarro;
	private String fabricante;
	private double precoCusto;
	private double precoVenda;
	private int quantidadeEstoque;
	private Categoria categoria;
	
	public Peca() {	
	}

	public int getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(int codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getModeloCarro() {
		return modeloCarro;
	}

	public void setModeloCarro(String modeloCarro) {
		this.modeloCarro = modeloCarro;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public double getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(double precoCusto) {
		this.precoCusto = precoCusto;
	}

	public double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(int quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public String mostraDados() {
		return String.format("Cod: %d - Nome: %s - Usada em: %s - Fab.: %s - Preço Custo: R$%.2f\n"
				+ "Preço Venda: R$%.2f - Estoque: %d peças - Categoria: %s\n",codigoBarras, nome, modeloCarro, fabricante, precoCusto, precoVenda,
				quantidadeEstoque, categoria);
	}
}

