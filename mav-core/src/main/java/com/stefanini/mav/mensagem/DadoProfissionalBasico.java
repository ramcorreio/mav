package com.stefanini.mav.mensagem;

import java.io.Serializable;
import java.util.Date;

public class DadoProfissionalBasico implements Serializable {

	private static final long serialVersionUID = -4614325625469735619L;

	private String empresa;

	private Date dataAdmissao;

	private TelefoneRamal telefone;

	private Endereco endereco;

	private Integer rendaLiquida;

	private String cargo;

	private String profissao;

	private Boolean aposentado;

	private Boolean pensionista;

	private String losango;

	private Date dataComprovanteRenda;

	private String tipoComprovanteRenda;

	private String ocupacaoNova;

	private String cnpj;

	private String filler;

	public String getEmpresa() {
		return empresa;
	}

	protected void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	protected void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	protected void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public TelefoneRamal getTelefone() {
		return telefone;
	}

	protected void setTelefone(TelefoneRamal telefone) {
		this.telefone = telefone;
	}

	public Integer getRendaLiquida() {
		return rendaLiquida;
	}

	protected void setRendaLiquida(Integer rendaLiquida) {
		this.rendaLiquida = rendaLiquida;
	}

	public String getCargo() {
		return cargo;
	}

	protected void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getProfissao() {
		return profissao;
	}

	protected void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public Boolean isAposentado() {
		return aposentado;
	}

	protected void setAposentado(Boolean aposentado) {
		this.aposentado = aposentado;

	}

	public Boolean isPensionista() {
		return pensionista;
	}

	protected void setPensionista(Boolean pensionista) {
		this.pensionista = pensionista;
	}

	public String getLosango() {
		return losango;
	}

	protected void setLosango(String losango) {
		this.losango = losango;
	}

	public Date getDataComprovanteRenda() {
		return dataComprovanteRenda;
	}

	protected void setDataComprovanteRenda(Date dataComprovanteRenda) {
		this.dataComprovanteRenda = dataComprovanteRenda;
	}

	public String getTipoComprovanteRenda() {
		return tipoComprovanteRenda;
	}

	protected void setTipoComprovanteRenda(String tipoComprovanteRenda) {
		this.tipoComprovanteRenda = tipoComprovanteRenda;
	}

	public String getOcupacaoNova() {
		return ocupacaoNova;
	}

	protected void setOcupacaoNova(String ocupacaoNova) {
		this.ocupacaoNova = ocupacaoNova;
	}

	public String getCnpj() {
		return cnpj;
	}

	protected void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getFiller() {
		return filler;
	}

	protected void setFiller(String filler) {
		this.filler = filler;
	}
}
