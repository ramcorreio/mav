package com.stefanini.mav.mensagem;

import java.util.Date;

import com.stefanini.mav.es.MapAtributo;

public class Prestacao {

	@MapAtributo(tamanho = 8)
	private Date vencimento;

	@MapAtributo(tamanho = 12)
	private String nossoNumero;

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public String getNossoNumero() {
		return nossoNumero;
	}

	public void setNossoNumero(String nossoNumero) {
		this.nossoNumero = nossoNumero;
	}

}
