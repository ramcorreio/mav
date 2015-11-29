package com.stefanini.mav.mensagem;

public class DadoConjuge extends DadoPessoal {
	
	private static final long serialVersionUID = -1628342254293004067L;
	
	private DadoProfissionalBasico dadoProfissional;
	
	public DadoProfissionalBasico getDadoProfissional() {
		return dadoProfissional;
	}
	
	protected void setDadoProfissional(DadoProfissionalBasico dadoProfissional) {
		this.dadoProfissional = dadoProfissional;
	}

}
