package com.stefanini.mav.mensagem;

public class SolicitacaoCapturaSimplificada extends MensagemBasica {

	private static final long serialVersionUID = -7402704207923363603L;

	private DadoPessoal dadosPessoais;

	private DadoOperacaoCartao dadosOperacaoCartao;

	private DadoComplementar complemento;

	private Indicador indicadores;

	public SolicitacaoCapturaSimplificada(String id, Cabecalho cabecalho) {
		super(id, cabecalho);
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
