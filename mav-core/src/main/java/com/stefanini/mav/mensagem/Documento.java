package com.stefanini.mav.mensagem;

import java.io.Serializable;
import java.util.Date;

public class Documento implements Serializable {

	private static final long serialVersionUID = -7227905734239905303L;

	private String numero;

	private String tipo;

	private String orgaoEmissor;

	private String ufOrgaoEmissor;

	private Date dataEmissao;
	
	private Date dataVancimento;

	@Override
	public boolean equals(Object obj) {

		if (!Documento.class.isInstance(obj)) {

			return false;
		}

		Documento outro = Documento.class.cast(obj);

		return numero.equals(outro.numero) 
				&& tipo.equals(outro.tipo) 
				&& orgaoEmissor.equals(outro.orgaoEmissor)
				&& ufOrgaoEmissor.equals(outro.ufOrgaoEmissor) 
				&& dataEmissao.equals(outro.dataEmissao);
	}

	public String getNuDocIdentificacao() {
		return numero;
	}

	protected void setNuDocIdentificacao(String nuDocIdentificacao) {
		this.numero = nuDocIdentificacao;
	}

	public String getTpDocIdentificacao() {
		return tipo;
	}

	protected void setTpDocIdentificacao(String tpDocIdentificacao) {
		this.tipo = tpDocIdentificacao;
	}

	public String getOrgaoEmissor() {
		return orgaoEmissor;
	}

	protected void setOrgaoEmissor(String orgaoEmissor) {
		this.orgaoEmissor = orgaoEmissor;
	}

	public String getUfOrgaoEmissor() {
		return ufOrgaoEmissor;
	}

	protected void setUfOrgaoEmissor(String ufOrgaoEmissor) {
		this.ufOrgaoEmissor = ufOrgaoEmissor;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	protected void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	
	public Date getDataVancimento() {
		return dataVancimento;
	}
	
	protected void setDataVancimento(Date dataVancimento) {
		this.dataVancimento = dataVancimento;
	}
}
