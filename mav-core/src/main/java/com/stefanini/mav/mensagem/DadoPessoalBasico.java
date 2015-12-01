package com.stefanini.mav.mensagem;

import java.io.Serializable;
import java.util.Date;

public class DadoPessoalBasico implements Serializable {

	private static final long serialVersionUID = -4387165935451019870L;

	private String cpf;

	private Date dataNascimento;

	private String filler;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}
}
