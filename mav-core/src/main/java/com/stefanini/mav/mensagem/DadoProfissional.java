package com.stefanini.mav.mensagem;

import java.util.Date;

public class DadoProfissional {

	private Date dataAdmissao;

	private Telefone telefone;

	private String empresa;

	private Endereco endereco;

	private Integer rendaLiquida;

	private String cargoCliente;

	private String profissaoCliente;

	private Boolean aposentado;

	private Boolean pensionista;

	private String losango;

	private String opcaoBeneficio;

	private String numeroBeneficio;

	private Date dataComprovanteRenda;

	private String tipoComprovanteRenda;

	private String ocupacaoNova;
	
	private String cnpjCliente;

	private String filler;

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	protected void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	
	public String getEmpresa() {
		return empresa;
	}

	protected void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public Endereco getEndereco() {
		return endereco;

	}

	protected void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public Telefone getTelefone() {
		return telefone;
	}

	protected void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}	
	
	public Integer getRendaLiquida() {
		return rendaLiquida;
	}

	protected void setRendaLiquida(Integer rendaLiquida) {
		this.rendaLiquida = rendaLiquida;

	}
	
	public String getCargoCliente() {
		return cargoCliente;
	}

	protected void setCargoCliente(String cargoCliente) {
		this.cargoCliente = cargoCliente;
	}
	
	public String getProfissaoCliente() {
		return profissaoCliente;
	}

	protected void setProfissaoCliente(String profissaoCliente) {
		this.profissaoCliente = profissaoCliente;
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
	
	public String getOpcaoBeneficio() {
		return opcaoBeneficio;
	}

	protected void setOpcaoBeneficio(String opcaoBeneficio) {
		this.opcaoBeneficio = opcaoBeneficio;

	}
	
	public String getNumeroBeneficio() {
		return numeroBeneficio;
	}

	protected void setNumeroBeneficio(String numeroBeneficio) {
		this.numeroBeneficio = numeroBeneficio;

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
	
	public String getCnpjCliente() {
		return cnpjCliente;
	}
	
	protected void setCnpjCliente(String cnpjCliente) {
		this.cnpjCliente = cnpjCliente;
	}
	
	public String getFiller() {
		return filler;
	}

	protected void setFiller(String filler) {
		this.filler = filler;

	}

}
