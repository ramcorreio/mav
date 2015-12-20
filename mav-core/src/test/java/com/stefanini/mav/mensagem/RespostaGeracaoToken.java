package com.stefanini.mav.mensagem;

import java.util.List;

import com.stefanini.mav.es.ConfiguracaoMensagem;
import com.stefanini.mav.es.MapAtributo;
import com.stefanini.mav.es.MapBean;
import com.stefanini.mav.es.MapLista;
import com.stefanini.mav.mensagem.Cabecalho.Fluxo;

@ConfiguracaoMensagem(inicio = 83, sentido = Fluxo.ENTRADA)
public class RespostaGeracaoToken extends MensagemBasica {

	private static final long serialVersionUID = -5904882476695289867L;

	@MapAtributo(tamanho = 512)
	private String token;
	
	@MapLista(maxSize = 10, attr = @MapAtributo(tamanho = 6))
	private List<String> tipoServico;
	
	@MapBean
	private Indicador indicadores;
	
	public RespostaGeracaoToken(String id, Cabecalho cabecalho) {
		super(id, cabecalho);
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the tipoServico
	 */
	public List<String> getTipoServico() {
		return tipoServico;
	}

	/**
	 * @param tipoServico the tipoServico to set
	 */
	public void setTipoServico(List<String> tipoServico) {
		this.tipoServico = tipoServico;
	}

	/**
	 * @return the indicadores
	 */
	public Indicador getIndicadores() {
		return indicadores;
	}

	/**
	 * @param indicadores the indicadores to set
	 */
	public void setIndicadores(Indicador indicadores) {
		this.indicadores = indicadores;
	}
}
