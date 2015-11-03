package com.stefanini.mav.mensagem;


public class MensagemFactory {
	
	public static MensagemBasica parse(String messagem) throws MensagemNaoEncontradaException {
		
		if(messagem == null || messagem.isEmpty()) {
			throw new MensagemNaoEncontradaException();
		}
		
		StringBuilder builder = new StringBuilder(messagem);
		TipoMensagem codigo = TipoMensagem.parse(builder.substring(5, 9));
		
		LeitorMensagem<? extends MensagemBasica> leitor = null;
		switch (codigo) {
			case C0450:
				leitor = new LeitorCapturaSimplificada(builder, codigo);
			break;

			default:
				throw new MensagemNaoEncontradaException(codigo.toString());
		}
		
		return leitor.ler();
	}

}
