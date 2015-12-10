package com.stefanini.mav.mensagem;

import com.stefanini.mav.es.MapAtributo;

public class InfoSeguro {

	@MapAtributo(tamanho = 2)
	private String tipo;

	@MapAtributo(tamanho = 4)
	private String codigo;

	@MapAtributo(tamanho = 15)
	private Integer valor;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

}
