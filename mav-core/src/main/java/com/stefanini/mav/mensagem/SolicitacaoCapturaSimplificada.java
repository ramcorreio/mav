package com.stefanini.mav.mensagem;

import java.io.Serializable;
import java.util.Date;

import com.stefanini.mav.es.MapAtributo;
import com.stefanini.mav.es.MapBean;
import com.stefanini.mav.mensagem.Cabecalho.Fluxo;
import com.stefanini.mav.es.ConfiguracaoMensagem;

@ConfiguracaoMensagem(inicio = 83, sentido = Fluxo.ENTRADA)
public class SolicitacaoCapturaSimplificada extends MensagemBasica {

	private static final long serialVersionUID = -7402704207923363603L;

	@MapBean
	private DadoPessoal dadosPessoais;

	@MapBean
	private DadoOperacaoCartao dadosOperacaoCartao;

	@MapBean
	private DadoComplementar complemento;

	// 0186 a 0186 Identificação do Canal 1 A Dados Losango T X
	// 0187 a 0196 Versão do Canal 10 A Uso exclusivo da Losango
	// 0197 a 0197 Política 1 A Uso exclusivo da Losango
	// 0198 a 0199 Ambiente 2 A Uso exclusivo da Losango
	@MapBean
	private Indicador indicadores;

	public SolicitacaoCapturaSimplificada(String id, Cabecalho cabecalho) {
		super(id, cabecalho);
	}

	public DadoPessoal getDadosPessoais() {
		return dadosPessoais;
	}

	public void setDadosPessoais(DadoPessoal dadosPessoais) {
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

	// 0084 a 0094 CPF 11 N CPF do Cliente X
	// 0095 a 0102 Data de Nascimento 8 N Data de nascimento do cliente
	// (ddmmaaaa). X
	// 0103 a 0142 Filler 40 A Filler
	public static class DadoPessoal {

		@MapAtributo(tamanho = 11)
		private String cpf;

		@MapAtributo(tamanho = 8)
		private Date dataNascimento;

		@MapAtributo(tamanho = 40, trim = false)
		private String filler;

		public String getCpf() {
			return cpf;
		}

		public void setCpf(String cpf) {
			this.cpf = cpf;
		}

		public Date getDataNascimento() {
			return dataNascimento;
		}

		public void setDataNascimento(Date dataNascimento) {
			this.dataNascimento = dataNascimento;
		}

		public String getFiller() {
			return filler;
		}

		public void setFiller(String filler) {
			this.filler = filler;
		}
	}

	public static class DadoOperacaoCartao implements Serializable {

		private static final long serialVersionUID = 7678829367254641220L;

		@MapAtributo(tamanho = 3)
		private String codigoOrg;

		@MapAtributo(tamanho = 3)
		private String codigoLogo;

		@MapAtributo(tamanho = 3)
		private String codigoCampanha;

		@MapAtributo(tamanho = 3)
		private String codigoModalidade;

		@MapAtributo(tamanho = 28, trim = false)
		private String filler;

		public String getCodigoOrg() {
			return codigoOrg;
		}

		public void setCodigoOrg(String codigoOrg) {
			this.codigoOrg = codigoOrg;
		}

		public String getCodigoLogo() {
			return codigoLogo;
		}

		public void setCodigoLogo(String codigoLogo) {
			this.codigoLogo = codigoLogo;
		}

		public String getCodigoCampanha() {
			return codigoCampanha;
		}

		public void setCodigoCampanha(String codigoCampanha) {
			this.codigoCampanha = codigoCampanha;
		}

		public String getCodigoModalidade() {
			return codigoModalidade;
		}

		public void setCodigoModalidade(String codigoModalidade) {
			this.codigoModalidade = codigoModalidade;
		}

		public String getFiller() {
			return filler;
		}

		public void setFiller(String filler) {
			this.filler = filler;
		}
	}

	// 0183 a 0183 Flag Cliente Emancipado 1 A Cliente Emancipado "0" X
	// 0184 a 0185 Produto 2 A Código do Produto Ver tabela de dominio
	// TOPxProduto X
	public static class DadoComplementar implements Serializable {

		private static final long serialVersionUID = -3527343656488827048L;

		@MapAtributo
		private Boolean emancipado;

		@MapAtributo(tamanho = 2)
		private String codigoProduto;

		public Boolean isEmancipado() {
			return emancipado;
		}

		public void setEmancipado(Boolean emancipado) {
			this.emancipado = emancipado;
		}

		public String getCodigoProduto() {
			return codigoProduto;
		}

		public void setCodigoProduto(String codigoProduto) {
			this.codigoProduto = codigoProduto;
		}
	}	
}
