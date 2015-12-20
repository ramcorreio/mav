package com.stefanini.mav.mensagem;

import com.stefanini.mav.es.ConfiguracaoMensagem;
import com.stefanini.mav.mensagem.Cabecalho.Fluxo;

@ConfiguracaoMensagem(inicio = 83, sentido = Fluxo.SAIDA)
public class RespostaConsultaProposta extends RespostaPropostaPadrao {

	private static final long serialVersionUID = -5872233495343112175L;

	public RespostaConsultaProposta(String id, Cabecalho cabecalho) {
		super(id, cabecalho);
	}

}
