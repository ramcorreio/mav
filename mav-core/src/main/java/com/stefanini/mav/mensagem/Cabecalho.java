package com.stefanini.mav.mensagem;


/**
 * 
 * 0001 a 0005	Tam-Mensagem	5	N	Tamanho da Mensagem
 * 0006 a 0009	Cod-Mensagem	4	N	Código da Mensagem = 0410
 * 0010 a 0015	Num-Transacao	6	N	Numero da Transação
 * 0016 a 0030	Num-Proposta	15	A	Numero da Proposta
 * 0031 a 0038	Cod-Usuario	8	A	Usuário responsável pela Mensagem ou Código do Funcionário do Lojista
 * 0039 a 0043	Cod-Retorno	5	A	Código de Retorno
 * 0044 a 0052	Cod-Lojista	9	N	Código do Lojista + Código da Filial
 * 0053 a 0053	Num-Versao	1	A	Número da Versão do Layout
 * 0054 a 0083	Campo-Lojista	30	A	Uso exclusivo do lojista
 * 
 * @author Rodrigo
 *
 */
public class Cabecalho {
	
	private Integer tamanho;
	
	private TipoMensagem tipo;
	
	private Integer numeroTransacao;
	
	private String numeroProposta;
	
	private String codigoUsuario;
	
	private String codigoRetorno;
	
	private Integer codigoLojista;
	
	private String versao;
	
	private String campoLojista;
	
	protected Cabecalho() {
	}
	
	public Integer getTamanho() {
		return tamanho;
	}

	protected void setTamanho(Integer tamanho) {
		this.tamanho = tamanho;
	}

	public TipoMensagem getTipo() {
		return tipo;
	}

	protected void setTipo(TipoMensagem tipo) {
		this.tipo = tipo;
	}

	public Integer getNumeroTransacao() {
		return numeroTransacao;
	}

	protected void setNumeroTransacao(Integer numeroTransacao) {
		this.numeroTransacao = numeroTransacao;
	}

	public String getNumeroProposta() {
		return numeroProposta;
	}

	protected void setNumeroProposta(String numeroProposta) {
		this.numeroProposta = numeroProposta;
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	protected void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getCodigoRetorno() {
		return codigoRetorno;
	}

	protected void setCodigoRetorno(String codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}

	public Integer getCodigoLojista() {
		return codigoLojista;
	}

	protected void setCodigoLojista(Integer codigoLojista) {
		this.codigoLojista = codigoLojista;
	}

	public String getVersao() {
		return versao;
	}

	protected void setVersao(String versao) {
		this.versao = versao;
	}

	public String getCampoLojista() {
		return campoLojista;
	}

	protected void setCampoLojista(String campoLojista) {
		this.campoLojista = campoLojista;
	}
	
	
}
