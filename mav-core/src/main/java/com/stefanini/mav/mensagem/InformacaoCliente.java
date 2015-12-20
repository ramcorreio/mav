package com.stefanini.mav.mensagem;

import com.stefanini.mav.es.ConfiguracaoMensagem;
import com.stefanini.mav.mensagem.Cabecalho.Fluxo;

@ConfiguracaoMensagem(inicio = 83, sentido = Fluxo.ENTRADA)
public class InformacaoCliente extends MensagemBasica {

	private static final long serialVersionUID = -7583220578896409471L;

	public InformacaoCliente(String id, Cabecalho cabecalho) {
		super(id, cabecalho);
	}
}
