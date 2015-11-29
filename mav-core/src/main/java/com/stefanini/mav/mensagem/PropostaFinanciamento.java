package com.stefanini.mav.mensagem;

import java.util.Date;
import java.util.List;

public class PropostaFinanciamento extends MensagemBasica {

	private static final long serialVersionUID = 2703729128402664733L;
	
	private DadoPessoalDetalhado dadosPessoais;
	
	private Indicador indicadores;

	private DadoProfissional dadosProfissionais;
	
	private DadoConjuge dadosConjuge;

	private Boolean possuiExperienciaCredito;

	private String fillerDadosComplementares;

	private List<Referencia> referenciasPessoais;
	
	private List<Referencia> referenciasComerciais;
	
	private Banco referenciaBancaria;

	protected PropostaFinanciamento(String id, Cabecalho cabecalho) {
		super(id, cabecalho);
	}
	
	public DadoPessoalDetalhado getDadosPessoais() {
		return dadosPessoais;
	}
	
	protected void setDadosPessoais(DadoPessoalDetalhado dadosPessoais) {
		this.dadosPessoais = dadosPessoais;
	}
	
	public Indicador getIndicadores() {
		return indicadores;
	}
	
	protected void setIndicadores(Indicador indicadores) {
		this.indicadores = indicadores;
	}
	
	public DadoProfissional getDadosProfissionais() {
		return dadosProfissionais;
	}
	
	protected void setDadosProfissionais(DadoProfissional dadosProfissionais) {
		this.dadosProfissionais = dadosProfissionais;
	}
	
	public DadoConjuge getDadosConjuge() {
		return dadosConjuge;
	}
	
	public void setDadosConjuge(DadoConjuge dadosConjuge) {
		this.dadosConjuge = dadosConjuge;
	}

	public void setEscolaridade(String lerString) {
		// TODO Auto-generated method stub
		
	}

	public void setFormacao(String lerString) {
		// TODO Auto-generated method stub
		
	}

	public void setPossuiCartao(Boolean lerBoolean) {
		// TODO Auto-generated method stub
		
	}

	public void setPossuiVeiculoProprio(Boolean lerBoolean) {
		// TODO Auto-generated method stub
		
	}

	public boolean isPossuiVeiculoProprio() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setPlaca(String lerString) {
		// TODO Auto-generated method stub
		
	}

	public void setRenavam(String lerString) {
		// TODO Auto-generated method stub
		
	}

	public void setVeiculoQuitado(Boolean lerBoolean) {
		// TODO Auto-generated method stub
		
	}
	
	public Boolean isPossuiExperienciaCredito() {
		return possuiExperienciaCredito;
	}

	public void setPossuiExperienciaCredito(Boolean possuiExperienciaCredito) {
		this.possuiExperienciaCredito = possuiExperienciaCredito;
		
	}

	public void setLocalExperienciaCredito(String lerString) {
		// TODO Auto-generated method stub
		
	}

	public void setPlanoExperienciaCredito(Integer lerInt) {
		// TODO Auto-generated method stub
		
	}

	public void setValorPrestacaoExperienciaCredito(Integer lerInt) {
		// TODO Auto-generated method stub
		
	}

	public void setInicioExperienciaCredito(Date lerDataCurta) {
		// TODO Auto-generated method stub
		
	}

	public void setClassificacaoCliente(String lerString) {
		// TODO Auto-generated method stub
		
	}

	public void setPossuiCartaoFinanceira(Boolean lerBoolean) {
		// TODO Auto-generated method stub
		
	}

	public void setPossuiContaCorrente(Boolean lerBoolean) {
		// TODO Auto-generated method stub
		
	}

	public void setPossuiDependente(Boolean lerBoolean) {
		// TODO Auto-generated method stub
		
	}

	public void setQuantidadeDependente(Integer lerInt) {
		// TODO Auto-generated method stub
		
	}

	public void setNomeCartao(String lerString) {
		// TODO Auto-generated method stub
		
	}

	public void setIndicadorCapturarFoto(String lerString) {
		// TODO Auto-generated method stub
		
	}

	public void setIndicadorCapturarDocumento(String lerString) {
		// TODO Auto-generated method stub
		
	}

	public void setIndicadorCapturarBiometria(String lerString) {
		// TODO Auto-generated method stub
		
	}
	
	public String getFillerDadosComplementares() {
		return fillerDadosComplementares;
	}

	protected  void setFillerDadosComplementares(String fillerDadosComplementares) {
		this.fillerDadosComplementares = fillerDadosComplementares;
	}
	
	public List<Referencia> getReferenciasPessoais() {
		return referenciasPessoais;
	}

	protected void setReferenciasPessoais(List<Referencia> referencias) {
		this.referenciasPessoais = referencias;
	}
	
	public List<Referencia> getReferenciasComerciais() {
		return referenciasComerciais;
	}
	
	protected void setReferenciasComerciais(List<Referencia> referenciasComerciais) {
		this.referenciasComerciais = referenciasComerciais;
	}
	
	public Banco getReferenciaBancaria() {
		return referenciaBancaria;
	}
	
	protected void setReferenciaBancaria(Banco referenciaBancaria) {
		this.referenciaBancaria = referenciaBancaria;
	}

	public void setTabelaFinanciamento(String lerString) {
		// TODO Auto-generated method stub
		
	}

	public void setSinalCarencia(String lerString) {
		// TODO Auto-generated method stub
		
	}

}
