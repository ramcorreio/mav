package com.stefanini.mav.mensagem;

public class RespostaErro extends MensagemBasica {

	private static final long serialVersionUID = 9221336402909229843L;

	private String descricao;

	private Indicador indicadores;

	protected RespostaErro(String id, Cabecalho cabecalho) {
		super(id, cabecalho);
	}

	public String getDescricao() {
		return descricao;
	}

	protected void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Indicador getIndicadores() {
		return indicadores;
	}

	protected void setIndicadores(Indicador indicadores) {
		this.indicadores = indicadores;
	}

}
