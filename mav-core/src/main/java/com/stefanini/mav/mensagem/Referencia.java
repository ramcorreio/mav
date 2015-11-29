package com.stefanini.mav.mensagem;

import java.io.Serializable;

public class Referencia implements Serializable {
	
	private static final long serialVersionUID = -1240797107998543075L;

	private String nome;
	
	private Telefone telefone;
	
	public String getNome() {
		return nome;
	}
	
	protected void setNome(String nome) {
		this.nome = nome;
	}
	
	public Telefone getTelefone() {
		return telefone;
	}
	
	protected void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

}
