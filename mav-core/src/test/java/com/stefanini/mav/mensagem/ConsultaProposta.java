package com.stefanini.mav.mensagem;

import com.stefanini.mav.es.ConfiguracaoMensagem;
import com.stefanini.mav.es.MapBean;
import com.stefanini.mav.mensagem.Cabecalho.Fluxo;

@ConfiguracaoMensagem(inicio = 83, sentido = Fluxo.ENTRADA)
public class ConsultaProposta extends MensagemBasica {

	private static final long serialVersionUID = -1751770187935762666L;
	
	@MapBean
	private Indicador indicadores;

	public ConsultaProposta(String id, Cabecalho cabecalho) {
		super(id, cabecalho);
	}
	
	public Indicador getIndicadores() {
		return indicadores;
	}
	
	public void setIndicadores(Indicador indicadores) {
		this.indicadores = indicadores;
	}

}
