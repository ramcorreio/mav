package com.stefanini.mav.mensagem;

import java.util.Date;

import com.stefanini.mav.es.MapAtributo;
import com.stefanini.mav.es.MapBean;
import com.stefanini.mav.es.PosicaoInicio;

@PosicaoInicio(posicao = 83)
public class RespostaCapturaSimplificada extends MensagemBasica {

	private static final long serialVersionUID = -7402704207923363603L;

	//@MapAtributo(tamanho = 83)
	private String filler;

	//@MapAtributo(tamanho = 5)
	private String mensagemAutorizador;

	//@MapAtributo(tamanho = 14, formato = "ddMMyyyyHHmmss")
	private Date data;

	//@MapAtributo(tamanho = 2)
	private String codigoStatusProposta;

	//@MapAtributo(tamanho = 80)
	private String parecer;

	//@MapAtributo(tamanho = 2)
	private String produto;
	
	/*@MapAtributo
	@MapBean({
		@MapBean({
			@MapAtributo(nome = "emancipado", tamanho = 1),
			@MapAtributo(nome = "codigoProduto", tamanho = 2)
		})
		@MapAtributo(nome = "cpf", tamanho = 11),
		@MapAtributo(nome = "dataNascimento", tamanho = 8),
		@MapAtributo(nome = "complemento"),
		@MapAtributo(nome = "cobraTac", tamanho = 1),
		@MapAtributo(nome = "elegibilidadeSeguro", tamanho = 1),
		@MapAtributo(nome = "codigoProdutoLosango", tamanho = 8),
		@MapAtributo(nome = "qtdNumeroSorte", tamanho = 2),
		@MapAtributo(nome = "filler", tamanho = 47)
	})*/
	private DadoCliente dadosPessoais;

	private DadoOperacaoCartao dadosOperacaoCartao;

	private Indicador indicadores;

	public RespostaCapturaSimplificada(String id, Cabecalho cabecalho) {
		super(id, cabecalho);
	}

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	public String getMensagemAutorizador() {
		return mensagemAutorizador;
	}

	public void setMensagemAutorizador(String mensagemAutorizador) {
		this.mensagemAutorizador = mensagemAutorizador;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}

	public String getCodigoStatusProposta() {
		return codigoStatusProposta;
	}

	public void setCodigoStatusProposta(String codigoStatusProposta) {
		this.codigoStatusProposta = codigoStatusProposta;
	}

	public String getParecer() {
		return parecer;
	}

	public void setParecer(String parecer) {
		this.parecer = parecer;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}
	
	public DadoCliente getDadosPessoais() {
		return dadosPessoais;
	}
	
	public void setDadosPessoais(DadoCliente dadosPessoais) {
		this.dadosPessoais = dadosPessoais;
	}

	public DadoOperacaoCartao getDadosOperacaoCartao() {
		return dadosOperacaoCartao;
	}

	public void setDadosOperacaoCartao(DadoOperacaoCartao dadosOperacaoCartao) {
		this.dadosOperacaoCartao = dadosOperacaoCartao;
	}

	public Indicador getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(Indicador indicadores) {
		this.indicadores = indicadores;
	}

}
