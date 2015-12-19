package com.stefanini.mav.mensagem;

import com.stefanini.mav.es.MapAtributo;

public class Patrimonio {

	@MapAtributo
	private String tipo;
	
	@MapAtributo(tamanho = 5)
	private String nome;
	
	@MapAtributo(tamanho = 11, scale = 2)
	private Double valor;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
}
