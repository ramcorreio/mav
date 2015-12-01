package com.stefanini.mav.mensagem;

import java.io.Serializable;

import com.stefanini.mav.es.MapAtributo;

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
public class Cabecalho implements Serializable {
	
	public enum Fluxo {
		
		ENTRADA, SAIDA;
	}
	
	private static final long serialVersionUID = 7347744883680813181L;
	
	private Fluxo sentidoFluxo;

	//@MapAtributo(tamanho = 5)
	private Integer tamanho;
	
	//@MapAtributo(tamanho = 4)
	private CodigoMensagem codigo;
	
	//@MapAtributo(tamanho = 6)
	private Integer numeroTransacao;
	
	//@MapAtributo(tamanho = 15)
	private String numeroProposta;
	
	//@MapAtributo(tamanho = 8)
	private String codigoUsuario;
	
	//@MapAtributo(tamanho = 5)
	private String codigoRetorno;
	
	//@MapAtributo(tamanho = 9)
	private Integer codigoLojista;
	
	//@MapAtributo(tamanho = 1)
	private String versao;
	
	//@MapAtributo(tamanho = 30, trim = false)
	private String campoLojista;
	
	@Override
	public boolean equals(Object obj) {
		
		if(!Cabecalho.class.isInstance(obj)) {
			
			return false;
		}
		
		Cabecalho outro = Cabecalho.class.cast(obj);
		
		return sentidoFluxo.equals(outro.sentidoFluxo)
				&& tamanho.equals(outro.tamanho)
				&& codigo.equals(outro.codigo)
				&& numeroTransacao.equals(outro.numeroTransacao)
				&& numeroProposta.equals(outro.numeroProposta)
				&& codigoUsuario.equals(outro.codigoUsuario)
				&& codigoRetorno.equals(outro.codigoRetorno)
				&& codigoLojista.equals(outro.codigoLojista)
				&& versao.equals(outro.versao)
				&& campoLojista.equals(outro.campoLojista);
	}
	
	public Fluxo getSentidoFluxo() {
		return sentidoFluxo;
	}
	
	public void setSentidoFluxo(Fluxo sentidoFluxo) {
		this.sentidoFluxo = sentidoFluxo;
	}
	
	public Integer getTamanho() {
		return tamanho;
	}

	public void setTamanho(Integer tamanho) {
		this.tamanho = tamanho;
	}

	public CodigoMensagem getCodigo() {
		return codigo;
	}

	public void setCodigo(CodigoMensagem codigo) {
		this.codigo = codigo;
	}

	public Integer getNumeroTransacao() {
		return numeroTransacao;
	}

	public void setNumeroTransacao(Integer numeroTransacao) {
		this.numeroTransacao = numeroTransacao;
	}

	public String getNumeroProposta() {
		return numeroProposta;
	}

	public void setNumeroProposta(String numeroProposta) {
		this.numeroProposta = numeroProposta;
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getCodigoRetorno() {
		return codigoRetorno;
	}

	public void setCodigoRetorno(String codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}

	public Integer getCodigoLojista() {
		return codigoLojista;
	}

	public void setCodigoLojista(Integer codigoLojista) {
		this.codigoLojista = codigoLojista;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public String getCampoLojista() {
		return campoLojista;
	}

	public void setCampoLojista(String campoLojista) {
		this.campoLojista = campoLojista;
	}

	public boolean isOk() {
		
		return codigo.isOk();
	}
	
	
}
