package com.stefanini.mav.mensagem;

public class PropostaFinanciamento extends MensagemBasica {

	private static final long serialVersionUID = 2703729128402664733L;
	
	private DadoPessoalDetalhado dadosPessoais;
	
	private Indicador indicadores;

	private DadoProfissional dadosProfissionais;
	
	private DadoConjuge dadosConjuge;

	protected PropostaFinanciamento(String id, Cabecalho cabecalho) {
		super(id, cabecalho);
	}
	
	public DadoPessoalDetalhado getDadosPessoais() {
		return dadosPessoais;
	}
	
	protected void setDadosPessoais(DadoPessoalDetalhado dadosPessoais) {
		this.dadosPessoais = dadosPessoais;
	}
	
	public Indicador getIndicadores() {
		return indicadores;
	}
	
	protected void setIndicadores(Indicador indicadores) {
		this.indicadores = indicadores;
	}
	
	public DadoProfissional getDadosProfissionais() {
		return dadosProfissionais;
	}
	
	protected void setDadosProfissionais(DadoProfissional dadosProfissionais) {
		this.dadosProfissionais = dadosProfissionais;
	}
	
	public DadoConjuge getDadosConjuge() {
		return dadosConjuge;
	}
	
	public void setDadosConjuge(DadoConjuge dadosConjuge) {
		this.dadosConjuge = dadosConjuge;
	}
}
