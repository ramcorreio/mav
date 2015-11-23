package com.stefanini.mav.mensagem;

import java.io.Serializable;
import java.util.Date;

public class DadoClienteBasico implements Serializable {

	private static final long serialVersionUID = -4387165935451019870L;

	private String cpf;

	private Date dataNascimento;

	private String filler;

	public String getCpf() {
		return cpf;
	}

	protected void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	protected void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getFiller() {
		return filler;
	}

	protected void setFiller(String filler) {
		this.filler = filler;
	}
}
