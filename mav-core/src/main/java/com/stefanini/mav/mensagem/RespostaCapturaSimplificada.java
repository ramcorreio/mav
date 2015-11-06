package com.stefanini.mav.mensagem;

import java.util.Date;

public class RespostaCapturaSimplificada extends MensagemBasica {

	private static final long serialVersionUID = -7402704207923363603L;

	private String filler;

	private String mensagemAutorizador;

	private Date data;

	private String codigoStatusProposta;

	private String parecer;

	private String produto;
	
	private DadoPessoal dadosPessoais;

	private DadoOperacaoCartao dadosOperacaoCartao;

	private DadoComplementar complemento;

	private Indicador indicadores;

	public RespostaCapturaSimplificada(Cabecalho cabecalho) {
		super(cabecalho);
	}

	public String getFiller() {
		return filler;
	}

	protected void setFiller(String filler) {
		this.filler = filler;
	}

	public String getMensagemAutorizador() {
		return mensagemAutorizador;
	}

	protected void setMensagemAutorizador(String mensagemAutorizador) {
		this.mensagemAutorizador = mensagemAutorizador;
	}
	
	public Date getData() {
		return data;
	}
	
	protected void setData(Date data) {
		this.data = data;
	}

	public String getCodigoStatusProposta() {
		return codigoStatusProposta;
	}

	protected void setCodigoStatusProposta(String codigoStatusProposta) {
		this.codigoStatusProposta = codigoStatusProposta;
	}

	public String getParecer() {
		return parecer;
	}

	protected void setParecer(String parecer) {
		this.parecer = parecer;
	}

	public String getProduto() {
		return produto;
	}

	protected void setProduto(String produto) {
		this.produto = produto;
	}
	
	public DadoPessoal getDadosPessoais() {
		return dadosPessoais;
	}
	
	protected void setDadosPessoais(DadoPessoal dadosPessoais) {
		this.dadosPessoais = dadosPessoais;
	}

	public DadoOperacaoCartao getDadosOperacaoCartao() {
		return dadosOperacaoCartao;
	}

	protected void setDadosOperacaoCartao(DadoOperacaoCartao dadosOperacaoCartao) {
		this.dadosOperacaoCartao = dadosOperacaoCartao;
	}

	public DadoComplementar getComplemento() {
		return complemento;
	}

	protected void setComplemento(DadoComplementar complemento) {
		this.complemento = complemento;
	}

	public Indicador getIndicadores() {
		return indicadores;
	}

	protected void setIndicadores(Indicador indicadores) {
		this.indicadores = indicadores;
	}

}
