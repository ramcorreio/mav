package com.stefanini.mav.mensagem;

import java.util.HashMap;
import java.util.Map;

public class MensagemFactory {
	
	private static final Map<TipoMensagem, ContextoMensagem<?>> leitores = new HashMap<>();
	
	static {
		leitores.put(TipoMensagem.C0450, new ContextoSolicitacaoCapturaSimplificada());
		leitores.put(TipoMensagem.C0460, new ContextoRespostaCapturaSimplificada());
	}
	
	public static MensagemBasica parse(String mensagem) throws MensagemNaoEncontradaException {
		
		if(mensagem == null || mensagem.isEmpty()) {
			throw new MensagemNaoEncontradaException();
		}
		
		TipoMensagem codigo = TipoMensagem.parse(mensagem.substring(5, 9));
		
		if(!leitores.containsKey(codigo)) {
			
			throw new MensagemNaoEncontradaException(codigo.toString());
		}
		
		
		return leitores.get(codigo).ler(mensagem, codigo);
	}

}
