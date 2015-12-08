package com.stefanini.mav.mensagem;

import java.util.Date;

import com.stefanini.mav.es.MapAtributo;
import com.stefanini.mav.es.MapBean;
import com.stefanini.mav.es.PosicaoInicio;

@PosicaoInicio(posicao = 83)
public class RespostaCapturaSimplificada extends MensagemBasica {
	
	private static final long serialVersionUID = -7402704207923363603L;

	@MapBean
	private DadoConsulta dadosConsulta;

	@MapBean
	private DadoCliente dadosCliente;

	@MapBean
	private DadoOperacaoCartao dadosOperacaoCartao;

	@MapAtributo(tamanho = 570, trim = false)
	private String filler;

	@MapBean
	private Indicador indicadores;

	public RespostaCapturaSimplificada(String id, Cabecalho cabecalho) {
		super(id, cabecalho);
	}

	public DadoConsulta getDadosConsulta() {
		return dadosConsulta;
	}

	public void setDadosConsulta(DadoConsulta dadosConsulta) {
		this.dadosConsulta = dadosConsulta;
	}
	
	public DadoCliente getDadosCliente() {
		return dadosCliente;
	}
	
	public void setDadosCliente(DadoCliente dadosCliente) {
		this.dadosCliente = dadosCliente;
	}
	
	public DadoOperacaoCartao getDadosOperacaoCartao() {
		return dadosOperacaoCartao;
	}

	public void setDadosOperacaoCartao(DadoOperacaoCartao dadosOperacaoCartao) {
		this.dadosOperacaoCartao = dadosOperacaoCartao;
	}

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	public Indicador getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(Indicador indicadores) {
		this.indicadores = indicadores;
	}

	// 0084 a 0166 Filler 83 A Filler
	// 0167 a 0171 mensagemAutorizador 5 A Parecer do autorizador de crédito
	// (Política): Parecer / Msg Score / Motivo Aprov/Neg
	// 0172 a 0179 data 8 N Data do Sistema
	// 0180 a 0185 hora 6 N Hora do Sistema
	// 0186 a 0187 codigoStatusProposta 2 A Código do Status da Proposta (02, 03
	// ou 04) "02 = A0062 = Elegível (bola verde)
	// 03 = A0063 = Segue Fluxo, com Pendencia (bola amarela)
	// 04 = A0064 = Não Elegível (bola vermelha)"
	// 0188 a 0267 parecer 80 A
	// 0268 a 0269 produto 2 A
	public static class DadoConsulta {

		@MapAtributo(tamanho = 83, trim = false)
		private String filler;

		@MapAtributo(tamanho = 5)
		private String mensagemAutorizador;

		@MapAtributo(tamanho = 14, formato = "ddMMyyyyHHmmss")
		private Date data;

		@MapAtributo(tamanho = 2)
		private StatusProposta codigoStatusProposta;

		@MapAtributo(tamanho = 80)
		private String parecer;

		@MapAtributo(tamanho = 2)
		private String produto;

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

		public StatusProposta getCodigoStatusProposta() {
			return codigoStatusProposta;
		}

		public void setCodigoStatusProposta(StatusProposta codigoStatusProposta) {
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
	}

	// 0270 a 0280 CPF 11 A
	// 0281 a 0288 dataNasc 8 N
	// 0289 a 0289 ClienteEmancipado 1 A
	// 0290 a 0291 CodProduto 2 A
	// 0292 a 0292 CobraTac 1 A Indica se o financiamento deve cobrar Tac (1) ou
	// não (0)
	// 0293 a 0293 Elegibilidade Seguro 1 A Indica se o cliente pode adquirir
	// seguro (1) ou não (0)
	// 0294 a 0301 Codigo Produto Losango 8 A Código do Produto do Seguro
	// 0302 a 0303 Qtd Numero Sorte 2 N Quantidade máxima de Numeros da Sorte
	// que podem ser ofertados
	// 0304 a 0350 Filler 47 A

	public static class DadoCliente {

		@MapAtributo(tamanho = 11)
		private String cpf;

		@MapAtributo(tamanho = 8)
		private Date dataNascimento;

		@MapAtributo
		private Boolean emancipado;

		@MapAtributo(tamanho = 2)
		private String codigoProduto;

		@MapAtributo
		private Boolean cobraTac;

		@MapAtributo
		private Boolean elegibilidadeSeguro;

		@MapAtributo(tamanho = 8)
		private String codigoProdutoLosango;

		@MapAtributo(tamanho = 2)
		private Integer qtdNumeroSorte;

		@MapAtributo(tamanho = 47, trim = false)
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

		public Boolean isCobraTac() {
			return cobraTac;
		}

		public void setCobraTac(Boolean cobraTac) {
			this.cobraTac = cobraTac;
		}

		public Boolean isElegibilidadeSeguro() {
			return elegibilidadeSeguro;
		}

		public void setElegibilidadeSeguro(Boolean elegibilidadeSeguro) {
			this.elegibilidadeSeguro = elegibilidadeSeguro;
		}

		public String getCodigoProdutoLosango() {
			return codigoProdutoLosango;
		}

		public void setCodigoProdutoLosango(String codigoProdutoLosango) {
			this.codigoProdutoLosango = codigoProdutoLosango;
		}

		public Integer getQtdNumeroSorte() {
			return qtdNumeroSorte;
		}

		public void setQtdNumeroSorte(Integer qtdNumeroSorte) {
			this.qtdNumeroSorte = qtdNumeroSorte;
		}

		public String getFiller() {
			return filler;
		}

		public void setFiller(String filler) {
			this.filler = filler;
		}
	}

	// 0351 a 0353 codModalidade 3 A Campos exclusivos para operação de Cartão
	// 0354 a 0356 codOrg 3 A Campos exclusivos para operação de Cartão
	// 0357 a 0359 codLogo 3 A Campos exclusivos para operação de Cartão
	// 0360 a 0362 codCampanha 3 A Campos exclusivos para operação de Cartão

	public static class DadoOperacaoCartao {

		@MapAtributo(tamanho = 3)
		private String codigoOrg;

		@MapAtributo(tamanho = 3)
		private String codigoLogo;

		@MapAtributo(tamanho = 3)
		private String codigoCampanha;

		@MapAtributo(tamanho = 3)
		private String codigoModalidade;

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
	}

	// 0933 a 0933 Identificação do Canal 1 A Dados Losango
	// 0934 a 0943 Versão do Canal 10 A Uso exclusivo da Losango
	// 0944 a 0944 Política 1 A Uso exclusivo da Losango
	// 0945 a 0946 Ambiente 2 A Uso exclusivo da Losango
	public static class Indicador {

		@MapAtributo(tamanho = 1)
		private String identificadorCanal;

		@MapAtributo(tamanho = 10)
		private String versaoCanal;

		@MapAtributo(tamanho = 1)
		private String politica;

		@MapAtributo(tamanho = 2)
		private String ambiente;

		/*
		 * @Override public boolean equals(Object obj) {
		 * 
		 * if(!Indicador.class.isInstance(obj)) { return false; }
		 * 
		 * Indicador outro = Indicador.class.cast(obj);
		 * 
		 * return identificadorCanal.equals(outro.identificadorCanal) &&
		 * versaoCanal.equals(outro.versaoCanal) &&
		 * politica.equals(outro.politica) && ambiente.equals(outro.ambiente); }
		 */

		public String getIdentificadorCanal() {
			return identificadorCanal;
		}

		public void setIdentificadorCanal(String identificadorCanal) {
			this.identificadorCanal = identificadorCanal;
		}

		public String getVersaoCanal() {
			return versaoCanal;
		}

		public void setVersaoCanal(String versaoCanal) {
			this.versaoCanal = versaoCanal;
		}

		public String getPolitica() {
			return politica;
		}

		public void setPolitica(String politica) {
			this.politica = politica;
		}

		public String getAmbiente() {
			return ambiente;
		}

		public void setAmbiente(String ambiente) {
			this.ambiente = ambiente;
		}
	}
}
