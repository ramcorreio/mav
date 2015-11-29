package com.stefanini.mav.mensagem;

import java.util.Date;
import java.util.List;

public class PropostaFinanciamento extends MensagemBasica {

	private static final long serialVersionUID = 2703729128402664733L;

	private DadoPessoalDetalhado dadosPessoais;

	private Indicador indicadores;

	private DadoProfissional dadosProfissionais;

	private DadoConjuge dadosConjuge;

	private Integer escolaridade;

	private String formacao;

	private Boolean possuiCartao;

	private Boolean possuiVeiculoProprio;

	private String placa;

	private String renavam;

	private Boolean veiculoQuitado;

	private Boolean possuiExperienciaCredito;

	private String localExperienciaCredito;

	private Integer planoExperienciaCredito;

	private Integer valorPrestacaoExperienciaCredito;

	private Date inicioExperienciaCredito;

	private String classificacaoCliente;

	private Boolean possuiCartaoFinanceira;

	private Boolean possuiContaCorrente;

	private Boolean possuiDependente;

	private Integer quantidadeDependente;

	private String nomeCartao;

	private String indicadorCapturarFoto;

	private String indicadorCapturarDocumento;

	private String indicadorCapturarBiometria;

	private String fillerDadosComplementares;

	private List<Referencia> referenciasPessoais;

	private List<Referencia> referenciasComerciais;

	private Banco referenciaBancaria;

	private Integer tabelaFinanciamento;

	private String sinalCarencia;

	private Integer carencia;

	private Integer formaPagamento;

	private Date dataOperacao;

	private Integer produto;

	private Integer prestacoes;

	private Double taxaMensal;

	private Double taxaAnual;

	private Integer valorEntrada;

	private Integer tipoPagamento;

	private Integer top;

	private Integer valorTac;

	private Integer pagTac;

	private Integer valorOperacaoSolicitado;

	private Integer valorTotalFinanciado;

	private Integer valorPrestacao;

	private Date vencimentoPrestacao;

	private String descricaoDoBem;

	private Boolean impressaoCarne;

	private String nuPedido;

	private String nuCd;

	private String cpfVendedor;

	private String telefoneVendedor;

	private Boolean prePago;

	private Boolean levaNaHora;

	private Integer betaGama;

	private Integer promotor;

	private Boolean aceitaConsulta;

	private Double cetMensal;

	private Double cetAnual;

	private Double iof;

	private Date dataEvento;

	private Integer valorEntradaLojista;

	private String fillerDadosOperacao;

	private String codigoOfertaAderidaContaCorrente;

	private String codigoPerfilOfertaAderidaCdc;

	private String fillerPreScreening;

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

	protected void setDadosConjuge(DadoConjuge dadosConjuge) {
		this.dadosConjuge = dadosConjuge;
	}

	public Integer getEscolaridade() {
		return escolaridade;
	}

	protected void setEscolaridade(Integer escolaridade) {
		this.escolaridade = escolaridade;
	}

	public String getFormacao() {
		return formacao;
	}

	protected void setFormacao(String formacao) {
		this.formacao = formacao;
	}

	public Boolean getPossuiCartao() {
		return possuiCartao;
	}

	protected void setPossuiCartao(Boolean possuiCartao) {
		this.possuiCartao = possuiCartao;
	}

	public Boolean getPossuiVeiculoProprio() {
		return possuiVeiculoProprio;
	}

	protected void setPossuiVeiculoProprio(Boolean possuiVeiculoProprio) {
		this.possuiVeiculoProprio = possuiVeiculoProprio;
	}

	public String getPlaca() {
		return placa;
	}

	protected void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getRenavam() {
		return renavam;
	}

	protected void setRenavam(String renavam) {
		this.renavam = renavam;
	}

	public Boolean isVeiculoQuitado() {
		return veiculoQuitado;
	}

	protected void setVeiculoQuitado(Boolean veiculoQuitado) {
		this.veiculoQuitado = veiculoQuitado;
	}

	public Boolean isPossuiExperienciaCredito() {
		return possuiExperienciaCredito;
	}

	protected void setPossuiExperienciaCredito(Boolean possuiExperienciaCredito) {
		this.possuiExperienciaCredito = possuiExperienciaCredito;
	}

	public String getLocalExperienciaCredito() {
		return localExperienciaCredito;
	}

	protected void setLocalExperienciaCredito(String localExperienciaCredito) {
		this.localExperienciaCredito = localExperienciaCredito;
	}

	public Integer getPlanoExperienciaCredito() {
		return planoExperienciaCredito;
	}

	protected void setPlanoExperienciaCredito(Integer planoExperienciaCredito) {
		this.planoExperienciaCredito = planoExperienciaCredito;
	}

	public Integer getValorPrestacaoExperienciaCredito() {
		return valorPrestacaoExperienciaCredito;
	}

	protected void setValorPrestacaoExperienciaCredito(Integer valorPrestacaoExperienciaCredito) {
		this.valorPrestacaoExperienciaCredito = valorPrestacaoExperienciaCredito;
	}

	public Date getInicioExperienciaCredito() {
		return inicioExperienciaCredito;
	}

	protected void setInicioExperienciaCredito(Date inicioExperienciaCredito) {
		this.inicioExperienciaCredito = inicioExperienciaCredito;
	}

	public String getClassificacaoCliente() {
		return classificacaoCliente;
	}

	protected void setClassificacaoCliente(String classificacaoCliente) {
		this.classificacaoCliente = classificacaoCliente;
	}

	public Boolean getPossuiCartaoFinanceira() {
		return possuiCartaoFinanceira;
	}

	protected void setPossuiCartaoFinanceira(Boolean possuiCartaoFinanceira) {
		this.possuiCartaoFinanceira = possuiCartaoFinanceira;
	}

	public Boolean getPossuiContaCorrente() {
		return possuiContaCorrente;
	}

	protected void setPossuiContaCorrente(Boolean possuiContaCorrente) {
		this.possuiContaCorrente = possuiContaCorrente;
	}

	public Boolean getPossuiDependente() {
		return possuiDependente;
	}

	protected void setPossuiDependente(Boolean possuiDependente) {
		this.possuiDependente = possuiDependente;
	}

	public Integer getQuantidadeDependente() {
		return quantidadeDependente;
	}

	protected void setQuantidadeDependente(Integer quantidadeDependente) {
		this.quantidadeDependente = quantidadeDependente;
	}

	public String getNomeCartao() {
		return nomeCartao;
	}

	protected void setNomeCartao(String nomeCartao) {
		this.nomeCartao = nomeCartao;
	}

	public String getIndicadorCapturarFoto() {
		return indicadorCapturarFoto;
	}

	protected void setIndicadorCapturarFoto(String indicadorCapturarFoto) {
		this.indicadorCapturarFoto = indicadorCapturarFoto;
	}

	public String getIndicadorCapturarDocumento() {
		return indicadorCapturarDocumento;
	}

	protected void setIndicadorCapturarDocumento(String indicadorCapturarDocumento) {
		this.indicadorCapturarDocumento = indicadorCapturarDocumento;
	}

	public String getIndicadorCapturarBiometria() {
		return indicadorCapturarBiometria;
	}

	protected void setIndicadorCapturarBiometria(String indicadorCapturarBiometria) {
		this.indicadorCapturarBiometria = indicadorCapturarBiometria;
	}

	public String getFillerDadosComplementares() {
		return fillerDadosComplementares;
	}

	protected void setFillerDadosComplementares(String fillerDadosComplementares) {
		this.fillerDadosComplementares = fillerDadosComplementares;
	}

	public List<Referencia> getReferenciasPessoais() {
		return referenciasPessoais;
	}

	protected void setReferenciasPessoais(List<Referencia> referenciasPessoais) {
		this.referenciasPessoais = referenciasPessoais;
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

	public Integer getTabelaFinanciamento() {
		return tabelaFinanciamento;
	}

	protected void setTabelaFinanciamento(Integer tabelaFinanciamento) {
		this.tabelaFinanciamento = tabelaFinanciamento;
	}

	public String getSinalCarencia() {
		return sinalCarencia;
	}

	protected void setSinalCarencia(String sinalCarencia) {
		this.sinalCarencia = sinalCarencia;
	}

	public Integer getCarencia() {
		return carencia;
	}

	protected void setCarencia(Integer carencia) {
		this.carencia = carencia;
	}

	public Integer getFormaPagamento() {
		return formaPagamento;
	}

	protected void setFormaPagamento(Integer formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Date getDataOperacao() {
		return dataOperacao;
	}

	protected void setDataOperacao(Date dataOperacao) {
		this.dataOperacao = dataOperacao;
	}

	public Integer getProduto() {
		return produto;
	}

	protected void setProduto(Integer produto) {
		this.produto = produto;
	}

	public Integer getPrestacoes() {
		return prestacoes;
	}

	protected void setPrestacoes(Integer prestacoes) {
		this.prestacoes = prestacoes;
	}

	public Double getTaxaMensal() {
		return taxaMensal;
	}

	protected void setTaxaMensal(Double taxaMensal) {
		this.taxaMensal = taxaMensal;
	}

	public Double getTaxaAnual() {
		return taxaAnual;
	}

	protected void setTaxaAnual(Double taxaAnual) {
		this.taxaAnual = taxaAnual;
	}

	public Integer getValorEntrada() {
		return valorEntrada;
	}

	protected void setValorEntrada(Integer valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

	public Integer getTipoPagamento() {
		return tipoPagamento;
	}

	protected void setTipoPagamento(Integer tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public Integer getTop() {
		return top;
	}

	protected void setTop(Integer top) {
		this.top = top;
	}

	public Integer getValorTac() {
		return valorTac;
	}

	protected void setValorTac(Integer valorTac) {
		this.valorTac = valorTac;
	}

	public Integer getPagTac() {
		return pagTac;
	}

	protected void setPagTac(Integer pagTac) {
		this.pagTac = pagTac;
	}

	public Integer getValorOperacaoSolicitado() {
		return valorOperacaoSolicitado;
	}

	protected void setValorOperacaoSolicitado(Integer valorOperacaoSolicitado) {
		this.valorOperacaoSolicitado = valorOperacaoSolicitado;
	}

	public Integer getValorTotalFinanciado() {
		return valorTotalFinanciado;
	}

	protected void setValorTotalFinanciado(Integer valorTotalFinanciado) {
		this.valorTotalFinanciado = valorTotalFinanciado;
	}

	public Integer getValorPrestacao() {
		return valorPrestacao;
	}

	protected void setValorPrestacao(Integer valorPrestacao) {
		this.valorPrestacao = valorPrestacao;
	}

	public Date getVencimentoPrestacao() {
		return vencimentoPrestacao;
	}

	protected void setVencimentoPrestacao(Date vencimentoPrestacao) {
		this.vencimentoPrestacao = vencimentoPrestacao;
	}

	public String getDescricaoDoBem() {
		return descricaoDoBem;
	}

	protected void setDescricaoDoBem(String descricaoDoBem) {
		this.descricaoDoBem = descricaoDoBem;
	}

	public Boolean getImpressaoCarne() {
		return impressaoCarne;
	}

	protected void setImpressaoCarne(Boolean impressaoCarne) {
		this.impressaoCarne = impressaoCarne;
	}

	public String getNuPedido() {
		return nuPedido;
	}

	protected void setNuPedido(String nuPedido) {
		this.nuPedido = nuPedido;
	}

	public String getNuCd() {
		return nuCd;
	}

	protected void setNuCd(String nuCd) {
		this.nuCd = nuCd;
	}

	public String getCpfVendedor() {
		return cpfVendedor;
	}

	protected void setCpfVendedor(String cpfVendedor) {
		this.cpfVendedor = cpfVendedor;
	}

	public String getTelefoneVendedor() {
		return telefoneVendedor;
	}

	protected void setTelefoneVendedor(String telefoneVendedor) {
		this.telefoneVendedor = telefoneVendedor;
	}

	public Boolean isPrePago() {
		return prePago;
	}

	protected void setPrePago(Boolean prePago) {
		this.prePago = prePago;
	}

	public Boolean isLevaNaHora() {
		return levaNaHora;
	}

	protected void setLevaNaHora(Boolean levaNaHora) {
		this.levaNaHora = levaNaHora;
	}

	public Integer getBetaGama() {
		return betaGama;
	}

	protected void setBetaGama(Integer betaGama) {
		this.betaGama = betaGama;
	}

	public Integer getPromotor() {
		return promotor;
	}

	protected void setPromotor(Integer promotor) {
		this.promotor = promotor;
	}

	public Boolean getAceitaConsulta() {
		return aceitaConsulta;
	}

	protected void setAceitaConsulta(Boolean aceitaConsulta) {
		this.aceitaConsulta = aceitaConsulta;
	}

	public Double getCetMensal() {
		return cetMensal;
	}

	protected void setCetMensal(Double cetMensal) {
		this.cetMensal = cetMensal;
	}

	public Double getCetAnual() {
		return cetAnual;
	}

	protected void setCetAnual(Double cetAnual) {
		this.cetAnual = cetAnual;
	}

	public Double getIof() {
		return iof;
	}

	protected void setIof(Double iof) {
		this.iof = iof;
	}

	public Date getDataEvento() {
		return dataEvento;
	}

	protected void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
	}

	public Integer getValorEntradaLojista() {
		return valorEntradaLojista;
	}

	protected void setValorEntradaLojista(Integer valorEntradaLojista) {
		this.valorEntradaLojista = valorEntradaLojista;
	}

	public String getFillerDadosOperacao() {
		return fillerDadosOperacao;
	}

	protected void setFillerDadosOperacao(String fillerDadosOperacao) {
		this.fillerDadosOperacao = fillerDadosOperacao;
	}

	public String getCodigoOfertaAderidaContaCorrente() {
		return codigoOfertaAderidaContaCorrente;
	}

	protected void setCodigoOfertaAderidaContaCorrente(String codigoOfertaAderidaContaCorrente) {
		this.codigoOfertaAderidaContaCorrente = codigoOfertaAderidaContaCorrente;
	}

	public String getCodigoPerfilOfertaAderidaCdc() {
		return codigoPerfilOfertaAderidaCdc;
	}

	protected void setCodigoPerfilOfertaAderidaCdc(String codigoPerfilOfertaAderidaCdc) {
		this.codigoPerfilOfertaAderidaCdc = codigoPerfilOfertaAderidaCdc;
	}

	public String getFillerPreScreening() {
		return fillerPreScreening;
	}

	protected void setFillerPreScreening(String fillerPreScreening) {
		this.fillerPreScreening = fillerPreScreening;
	}

}
