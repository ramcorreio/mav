package com.stefanini.mav.mensagem;

import com.stefanini.mav.es.MapAtributo;

public class Telefone {

	@MapAtributo(tamanho = 3)
	private Integer ddd;

	@MapAtributo(tamanho = 9)
	private Integer numero;

	public Integer getDdd() {
		return ddd;
	}

	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

}
