package com.stefanini.mav.mensagem;

public class DadoCliente extends DadoPessoalBasico {

	private static final long serialVersionUID = 4901056768962549954L;

	private DadoComplementar complemento;
	
	private Boolean cobraTac;
	
	private Boolean elegibilidadeSeguro;
	
	private String codigoProdutoLosango;
	
	private Integer qtdNumeroSorte;
	
	public DadoComplementar getComplemento() {
		return complemento;
	}

	public void setComplemento(DadoComplementar complemento) {
		this.complemento = complemento;
	}

	public Boolean isCobraTac() {
		return cobraTac;
	}

	public void setCobraTac(Boolean cobraTac) {
		this.cobraTac = cobraTac;
	}

	public Boolean isElegibilidadeSeguro() {
		return elegibilidadeSeguro;
	}

	public void setElegibilidadeSeguro(Boolean elegibilidadeSSeguro) {
		this.elegibilidadeSeguro = elegibilidadeSSeguro;
	}

	public String getCodigoProdutoLosango() {
		return codigoProdutoLosango;
	}

	public void setCodigoProdutoLosango(String codigoProdutoLosango) {
		this.codigoProdutoLosango = codigoProdutoLosango;
	}

	public Integer getQtdNumeroSorte() {
		return qtdNumeroSorte;
	}

	public void setQtdNumeroSorte(Integer qtdNumeroSorte) {
		this.qtdNumeroSorte = qtdNumeroSorte;
	}	
}
