package com.stefanini.mav.mensagem;

import java.util.Date;

import com.stefanini.mav.es.MapAtributo;
import com.stefanini.mav.es.MapBean;

public class Banco {

	@MapBean
	private InfoBanco info;

	@MapAtributo(tamanho = 2)
	private String tipoConta;

	@MapAtributo(tamanho = 8)
	private Date dataAbertura;
	
	public InfoBanco getInfo() {
		return info;
	}
	
	public void setInfo(InfoBanco info) {
		this.info = info;
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
