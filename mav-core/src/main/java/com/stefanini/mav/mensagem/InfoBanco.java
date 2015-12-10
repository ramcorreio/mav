package com.stefanini.mav.mensagem;

import java.util.Date;

import com.stefanini.mav.es.MapAtributo;

public class InfoBanco {

	@MapAtributo(tamanho = 4)
	private Integer numero;

	@MapAtributo(tamanho = 4)
	private Integer agencia;

	@MapAtributo(tamanho = 1)
	private String dvAgencia;

	@MapAtributo(tamanho = 13)
	private Integer contaCorrente;

	@MapAtributo(tamanho = 2)
	private String dvContaCorrente;	

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getAgencia() {
		return agencia;
	}

	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}

	public String getDvAgencia() {
		return dvAgencia;
	}

	public void setDvAgencia(String dvAgencia) {
		this.dvAgencia = dvAgencia;
	}

	public Integer getContaCorrente() {
		return contaCorrente;
	}

	public void setContaCorrente(Integer contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	public String getDvContaCorrente() {
		return dvContaCorrente;
	}

	public void setDvContaCorrente(String dvContaCorrente) {
		this.dvContaCorrente = dvContaCorrente;
	}
}
