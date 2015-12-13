package com.stefanini.mav.mensagem;

import java.util.Date;
import java.util.List;

import com.stefanini.mav.es.ConfiguracaoMensagem;
import com.stefanini.mav.es.MapAtributo;
import com.stefanini.mav.es.MapBean;
import com.stefanini.mav.es.MapLista;
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
	
	@MapBean
	private DadoCarne dadosImpressao;

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
	
	public DadoCarne getDadosImpressao() {
		return dadosImpressao;
	}
	
	public void setDadosImpressao(DadoCarne dadosImpressao) {
		this.dadosImpressao = dadosImpressao;
	}
	
	//0422 a 0451	Nome_Cedente	30	A	Nome do Cedente 	
	//0452 a 0453	Especie_Doc	2	A	Especie do Documento	
	//0454 a 0454	Aceite	1	A	Identificação do aceite Default=N	
	//0455 a 0457	CIP	3	A	CIP	
	//0458 a 0460	Carteira	3	A	Tipo de cobrança	
	//0461 a 0465	Especie	5	A	Tipo de Moeda	
	//0466 a 0480	Quantidade	15	N	Quantidade relacionada a especie de moeda (cinco (5) decimais)	
	//0481 a 0495	Valor_Mora_Dia	15	N	Valor do juros de mora expresso em quantidade de moeda (2) decimais)	
	//0496 a 0510	Val_A_Pagar	15	N	Valor da parcela a pagar com os encargos	
	//0511 a 0513	Banco	3	N	Codigo do Banco	
	//0514 a 0517	Agencia_Cedente	4	N	Codigo da Agencia	
	//0518 a 0518	Agencia_Digito_Cedente	1	A	Digito verificador da Agencia	
	//0519 a 0525	Codigo_Cedente	7	N	Número da conta do cedente	
	//0526 a 0526	Digito_Cedente	1	A	Digito verificador da C.C. do cedente	
	//0527 a 0606	Msg_01	80	A	Campo de Instrução	
	//0607 a 0686	Msg_02	80	A	Campo de Instrução	
	//0687 a 0766	Msg_03	80	A	Campo de Instrução	
	//0767 a 0846	Msg_04	0080	A	Campo de Instrução	
	//0847 a 0926	Msg_05	0080	A	Campo de Instrução	
	//0927 a 1006	Msg_06	0080	A	Campo de Instrução	
	//1007 a 1086	Filler	80	A
	public static class DadoCarne {
		
		@MapAtributo(tamanho = 30)
		private String nomeCedente;
		
		@MapAtributo(tamanho = 2)
		private String especieDocumento;
		
		@MapAtributo(comparador = "S")
		private Boolean aceite;
		
		@MapAtributo(tamanho = 3)
		private String cip;
		
		@MapAtributo(tamanho = 3)
		private String carteira;
		
		@MapAtributo(tamanho = 5)
		private String especie;	
		
		@MapAtributo(tamanho = 15, scale = 5)
		private Double quantidade;
		
		@MapAtributo(tamanho = 15, scale = 2)
		private Double valorMoraDia;
		
		@MapAtributo(tamanho = 15)
		private Integer valorPagar;	

		@MapAtributo(tamanho = 3)
		private Integer banco;
		
		@MapAtributo(tamanho = 4)
		private Integer agenciaCedente;
		
		@MapAtributo
		private String dvAgenciaCedente;
		
		@MapAtributo(tamanho = 7)
		private Integer codigoCedente;
		
		@MapAtributo
		private String digitoCedente;
			
		@MapLista(maxSize = 6, attr = @MapAtributo(tamanho = 80))
		private List<String> mensagens;
		
		@MapAtributo(tamanho = 80, trim = false)
		private String filler;

		public String getNomeCedente() {
			return nomeCedente;
		}

		public void setNomeCedente(String nomeCedente) {
			this.nomeCedente = nomeCedente;
		}

		public String getEspecieDocumento() {
			return especieDocumento;
		}

		public void setEspecieDocumento(String especieDocumento) {
			this.especieDocumento = especieDocumento;
		}

		public Boolean getAceite() {
			return aceite;
		}

		public void setAceite(Boolean aceite) {
			this.aceite = aceite;
		}

		public String getCip() {
			return cip;
		}

		public void setCip(String cip) {
			this.cip = cip;
		}

		public String getCarteira() {
			return carteira;
		}

		public void setCarteira(String carteira) {
			this.carteira = carteira;
		}

		public String getEspecie() {
			return especie;
		}

		public void setEspecie(String especie) {
			this.especie = especie;
		}

		public Double getQuantidade() {
			return quantidade;
		}

		public void setQuantidade(Double quantidade) {
			this.quantidade = quantidade;
		}

		public Double getValorMoraDia() {
			return valorMoraDia;
		}

		public void setValorMoraDia(Double valorMoraDia) {
			this.valorMoraDia = valorMoraDia;
		}

		public Integer getValorPagar() {
			return valorPagar;
		}

		public void setValorPagar(Integer valorPagar) {
			this.valorPagar = valorPagar;
		}

		public Integer getBanco() {
			return banco;
		}

		public void setBanco(Integer banco) {
			this.banco = banco;
		}

		public Integer getAgenciaCedente() {
			return agenciaCedente;
		}

		public void setAgenciaCedente(Integer agenciaCedente) {
			this.agenciaCedente = agenciaCedente;
		}

		public String getDvAgenciaCedente() {
			return dvAgenciaCedente;
		}

		public void setDvAgenciaCedente(String dvAgenciaCedente) {
			this.dvAgenciaCedente = dvAgenciaCedente;
		}

		public Integer getCodigoCedente() {
			return codigoCedente;
		}

		public void setCodigoCedente(Integer codigoCedente) {
			this.codigoCedente = codigoCedente;
		}

		public String getDigitoCedente() {
			return digitoCedente;
		}

		public void setDigitoCedente(String digitoCedente) {
			this.digitoCedente = digitoCedente;
		}

		public List<String> getMensagens() {
			return mensagens;
		}

		public void setMensagens(List<String> mensagens) {
			this.mensagens = mensagens;
		}

		public String getFiller() {
			return filler;
		}

		public void setFiller(String filler) {
			this.filler = filler;
		}
	}
}
