package com.stefanini.mav.mensagem;

import com.stefanini.mav.es.MapAtributo;
import com.stefanini.mav.es.MapBean;

public class Referencia {

	@MapAtributo(tamanho = 30)
	private String nome;

	@MapBean
	private TelefoneRamal telefone;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TelefoneRamal getTelefone() {
		return telefone;
	}

	public void setTelefone(TelefoneRamal telefone) {
		this.telefone = telefone;
	}

}
