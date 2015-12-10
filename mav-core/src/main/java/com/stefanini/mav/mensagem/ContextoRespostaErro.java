package com.stefanini.mav.mensagem;

public class ContextoRespostaErro extends ContextoMensagem<RespostaErro> {

	public ContextoRespostaErro(CodigoMensagem tipo) {
		super(tipo, RespostaErro.class);
	}
}