package com.stefanini.mav.mensagem;

public class DadoClienteDetalhado extends DadoClienteBasico {

	private static final long serialVersionUID = 4901056768962549954L;

	private boolean emancipado;

	private String tipoPersonalidade;

	private String usuarioCpf;

	private String nuDocIdentificacao;

	private String tpDocIdentificacao;

	private String orgaoEmissor;

	private String ufOrgaoEmissor;

	public boolean isEmancipado() {
		return emancipado;
	}

	protected void setEmancipado(boolean emancipado) {
		this.emancipado = emancipado;
	}

	public String getTipoPersonalidade() {
		return tipoPersonalidade;
	}

	public void setTipoPersonalidade(String tipoPersonalidade) {
		this.tipoPersonalidade = tipoPersonalidade;
	}

	public String getUsuarioCpf() {
		return usuarioCpf;
	}

	protected void setUsuarioCpf(String usuarioCpf) {
		this.usuarioCpf = usuarioCpf;

	}
	
	public String getNuDocIdentificacao() {
		return nuDocIdentificacao;
	}

	protected void setNuDocIdentificacao(String nuDocIdentificacao) {
		this.nuDocIdentificacao = nuDocIdentificacao;
	}
	
	public String getTpDocIdentificacao() {
		return tpDocIdentificacao;
	}

	protected void setTpDocIdentificacao(String tpDocIdentificacao) {
		this.tpDocIdentificacao = tpDocIdentificacao;
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

}
