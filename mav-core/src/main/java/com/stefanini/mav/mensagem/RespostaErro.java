package com.stefanini.mav.mensagem;

import java.io.Serializable;

import com.stefanini.mav.es.MapAtributo;
import com.stefanini.mav.es.MapBean;
import com.stefanini.mav.es.PosicaoInicio;

@PosicaoInicio(posicao = 83)
public class RespostaErro extends MensagemBasica {

	private static final long serialVersionUID = 9221336402909229843L;

	@MapAtributo(tamanho = 81)
	private String descricao;

	@MapBean
	private Indicador indicadores;

	public RespostaErro(String id, Cabecalho cabecalho) {
		super(id, cabecalho);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Indicador getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(Indicador indicadores) {
		this.indicadores = indicadores;
	}

	// 0165 a 0165 Identificação do Canal 1 A Dados Losango
	// 0166 a 0175 Versão do Canal 10 A Uso exclusivo da Losango
	// 0176 a 0176 Política 1 A Uso exclusivo da Losango
	// 0177 a 0178 Ambiente 2 A Uso exclusivo da Losango
	public static class Indicador implements Serializable {

		private static final long serialVersionUID = -1235658988407468213L;

		@MapAtributo
		private String identificadorCanal;

		@MapAtributo(tamanho = 10)
		private String versaoCanal;

		@MapAtributo
		private String politica;

		@MapAtributo(tamanho = 2)
		private String ambiente;

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
