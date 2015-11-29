package com.stefanini.mav.mensagem;

import java.util.Date;

public class Banco {

	private String banco;

	private String agencia;

	private String dvAgencia;

	private String contaCorrente;

	private String dvContaCorrente;

	private String tipoConta;

	private Date dataAbertura;

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getDvAgencia() {
		return dvAgencia;
	}

	public void setDvAgencia(String dvAgencia) {
		this.dvAgencia = dvAgencia;
	}

	public String getContaCorrente() {
		return contaCorrente;
	}

	public void setContaCorrente(String contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	public String getDvContaCorrente() {
		return dvContaCorrente;
	}

	public void setDvContaCorrente(String dvContaCorrente) {
		this.dvContaCorrente = dvContaCorrente;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

}
