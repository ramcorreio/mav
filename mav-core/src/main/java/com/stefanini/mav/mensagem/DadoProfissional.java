package com.stefanini.mav.mensagem;

public class DadoProfissional extends DadoProfissionalBasico {

	private static final long serialVersionUID = 2368895176487487395L;

	private String opcaoBeneficio;

	private String numeroBeneficio;

	public String getOpcaoBeneficio() {
		return opcaoBeneficio;
	}

	protected void setOpcaoBeneficio(String opcaoBeneficio) {
		this.opcaoBeneficio = opcaoBeneficio;

	}

	public String getNumeroBeneficio() {
		return numeroBeneficio;
	}

	protected void setNumeroBeneficio(String numeroBeneficio) {
		this.numeroBeneficio = numeroBeneficio;

	}
}
