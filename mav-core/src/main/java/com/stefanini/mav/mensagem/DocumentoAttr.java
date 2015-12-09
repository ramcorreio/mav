package com.stefanini.mav.mensagem;

import java.util.Date;

import com.stefanini.mav.es.MapAtributo;

public class DocumentoAttr {

	@MapAtributo(tamanho = 2)
	private String tipo;

	@MapAtributo(tamanho = 5)
	private String orgaoEmissor;

	@MapAtributo(tamanho = 2)
	private String ufOrgaoEmissor;

	@MapAtributo(tamanho = 8)
	private Date dataEmissao;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getOrgaoEmissor() {
		return orgaoEmissor;
	}

	public void setOrgaoEmissor(String orgaoEmissor) {
		this.orgaoEmissor = orgaoEmissor;
	}

	public String getUfOrgaoEmissor() {
		return ufOrgaoEmissor;
	}

	public void setUfOrgaoEmissor(String ufOrgaoEmissor) {
		this.ufOrgaoEmissor = ufOrgaoEmissor;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

}
