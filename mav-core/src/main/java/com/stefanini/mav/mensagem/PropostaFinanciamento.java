package com.stefanini.mav.mensagem;

public class PropostaFinanciamento extends MensagemBasica {

	private static final long serialVersionUID = 2703729128402664733L;
	
	private DadoClienteDetalhado dadosPessoais;
	
	private Indicador indicadores;

	private DadoProfissional dadosProfissionais;

	protected PropostaFinanciamento(String id, Cabecalho cabecalho) {
		super(id, cabecalho);
	}
	
	public DadoClienteDetalhado getDadosPessoais() {
		return dadosPessoais;
	}
	
	protected void setDadosPessoais(DadoClienteDetalhado dadosPessoais) {
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
}
