package com.stefanini.mav.mensagem;

public class PropostaFinanciamento extends MensagemBasica {

	private static final long serialVersionUID = 2703729128402664733L;
	
	private Indicador indicadores;

	protected PropostaFinanciamento(String id, Cabecalho cabecalho) {
		super(id, cabecalho);
	}
	
	public Indicador getIndicadores() {
		return indicadores;
	}
	
	protected void setIndicadores(Indicador indicadores) {
		this.indicadores = indicadores;
	}
}
