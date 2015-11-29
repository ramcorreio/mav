package com.stefanini.mav.mensagem;

public class DadoPessoalDetalhado extends DadoPessoal {

	private static final long serialVersionUID = 4901056768962549954L;

	private Endereco endereco;
	
	public Endereco getEndereco() {
		return endereco;
	}

	protected void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}	
	
}
