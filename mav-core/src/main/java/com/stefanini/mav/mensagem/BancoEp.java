package com.stefanini.mav.mensagem;

import com.stefanini.mav.es.MapAtributo;
import com.stefanini.mav.es.MapBean;

public class BancoEp {

	@MapAtributo(comparador = "S")
	private Boolean pagtoDoc;

	@MapAtributo(tamanho = 2)
	private String tipoConta;

	@MapBean
	private InfoBanco info;

	@MapAtributo
	private Integer c2;

	@MapAtributo
	private Integer c3;

	public Boolean getPagtoDoc() {
		return pagtoDoc;
	}

	public void setPagtoDoc(Boolean pagtoDoc) {
		this.pagtoDoc = pagtoDoc;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public InfoBanco getInfo() {
		return info;
	}

	public void setInfo(InfoBanco info) {
		this.info = info;
	}

	public Integer getC2() {
		return c2;
	}

	public void setC2(Integer c2) {
		this.c2 = c2;
	}

	public Integer getC3() {
		return c3;
	}

	public void setC3(Integer c3) {
		this.c3 = c3;
	}
}
