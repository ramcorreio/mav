package com.stefanini.mav.es;

public class SuperBean {

	@MapAtributo(tamanho = 3)
	private Integer conta;
	
	@MapAtributo(tamanho = 10)
	private String texto;

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}

	public Integer getConta() {
		return conta;
	}

	public void setConta(Integer conta) {
		this.conta = conta;
	}
}
