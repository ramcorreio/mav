package com.stefanini.mav.mensagem;

import com.stefanini.mav.es.PosicaoInicio;

@PosicaoInicio(posicao = 83)
public class SolicitacaoCapturaSimplificada extends MensagemBasica {

	private static final long serialVersionUID = -7402704207923363603L;

	/*@MapAtributo
	@MapBean({
		@MapAtributo(nome = "cpf", tamanho = 11),
		@MapAtributo(nome = "dataNascimento", tamanho = 8),
		@MapAtributo(nome = "filler", tamanho = 40, trim = false)
	})*/
	private DadoPessoalBasico dadosPessoais;

	/*@MapAtributo
	@MapBean({
		@MapAtributo(nome = "codigoOrg", tamanho = 3),
		@MapAtributo(nome = "codigoLogo", tamanho = 3),
		@MapAtributo(nome = "codigoCampanha", tamanho = 3),
		@MapAtributo(nome = "codigoModalidade", tamanho = 3),
		@MapAtributo(nome = "filler", tamanho = 28, trim = false)
	})*/
	private DadoOperacaoCartao dadosOperacaoCartao;

	/*@MapAtributo
	@MapBean({
		@MapAtributo(nome = "emancipado", tamanho = 1),
		@MapAtributo(nome = "codigoProduto", tamanho = 2)
	})*/
	private DadoComplementar complemento;

	/*@MapAtributo
	@MapBean({
		@MapAtributo(nome = "identificadorCanal", tamanho = 1),
		@MapAtributo(nome = "versaoCanal", tamanho = 10),
		@MapAtributo(nome = "politica", tamanho = 1),
		@MapAtributo(nome = "ambiente", tamanho = 2)
	})*/
	private Indicador indicadores;

	public SolicitacaoCapturaSimplificada(String id, Cabecalho cabecalho) {
		super(id, cabecalho);
	}

	public DadoPessoalBasico getDadosPessoais() {
		return dadosPessoais;
	}

	public void setDadosPessoais(DadoPessoalBasico dadosPessoais) {
		this.dadosPessoais = dadosPessoais;
	}

	public DadoOperacaoCartao getDadosOperacaoCartao() {
		return dadosOperacaoCartao;
	}

	public void setDadosOperacaoCartao(DadoOperacaoCartao dadosOperacaoCartao) {
		this.dadosOperacaoCartao = dadosOperacaoCartao;
	}

	public DadoComplementar getComplemento() {
		return complemento;
	}

	public void setComplemento(DadoComplementar complemento) {
		this.complemento = complemento;
	}

	public Indicador getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(Indicador indicadores) {
		this.indicadores = indicadores;
	}

}
