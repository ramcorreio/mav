package com.stefanini.mav.mensagem;

import java.util.Date;

import com.stefanini.mav.es.ConfiguracaoMensagem;
import com.stefanini.mav.es.MapAtributo;
import com.stefanini.mav.mensagem.Cabecalho.Fluxo;

@ConfiguracaoMensagem(inicio = 83, sentido = Fluxo.SAIDA)
public class RespostaPropostaFinanciamento extends MensagemBasica {

	private static final long serialVersionUID = 2703729128402664733L;
	
	@MapAtributo
	private String liberacaoCessao;
	
	@MapAtributo(tamanho = 255)
	private String restricao;
	
	@MapAtributo(tamanho = 7)
	private Integer codAutorizador;
	
	@MapAtributo(tamanho = 8)
	private Date dataAutorizacao;
	
	@MapAtributo(tamanho = 50, trim = false)
	private String fillerRespostaProposta;
	
	@MapAtributo(tamanho = 2)
	private Integer prestacoes;
	
	@MapAtributo(tamanho = 15)
	private Integer valorPrestacao;
	

	public RespostaPropostaFinanciamento(String id, Cabecalho cabecalho) {
		super(id, cabecalho);
	}

	public String getLiberacaoCessao() {
		return liberacaoCessao;
	}

	public void setLiberacaoCessao(String liberacaoCessao) {
		this.liberacaoCessao = liberacaoCessao;
	}

	public String getRestricao() {
		return restricao;
	}

	public void setRestricao(String restricao) {
		this.restricao = restricao;
	}

	public Integer getCodAutorizador() {
		return codAutorizador;
	}

	public void setCodAutorizador(Integer codAutorizador) {
		this.codAutorizador = codAutorizador;
	}

	public Date getDataAutorizacao() {
		return dataAutorizacao;
	}

	public void setDataAutorizacao(Date dataAutorizacao) {
		this.dataAutorizacao = dataAutorizacao;
	}

	public String getFiller() {
		return fillerRespostaProposta;
	}

	public void setFillerRespostaProposta(String fillerRespostaProposta) {
		this.fillerRespostaProposta = fillerRespostaProposta;
	}
	
	public Integer getPrestacoes() {
		return prestacoes;
	}
	
	public void setPrestacoes(Integer prestacoes) {
		this.prestacoes = prestacoes;
	}
	
	public Integer getValorPrestacao() {
		return valorPrestacao;
	}
	
	public void setValorPrestacao(Integer valorPrestacao) {
		this.valorPrestacao = valorPrestacao;
	}
}
