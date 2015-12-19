package com.stefanini.mav.mensagem;

import com.stefanini.mav.es.MapAtributo;

public class AttrComum {

	@MapAtributo(tamanho = 2)
	private String tipo;

	@MapAtributo(tamanho = 5)
	private String orgaoEmissor;

	@MapAtributo(tamanho = 2)
	private String ufOrgaoEmissor;

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
}
