package com.stefanini.mav.mensagem;

import java.util.HashMap;
import java.util.Map;

public class MensagemFactory {
	
	private static final Map<CodigoMensagem, ContextoMensagem<? extends MensagemBasica>> leitores = new HashMap<>();
	
	static {
		leitores.put(CodigoMensagem.C0450, new ContextoSolicitacaoCapturaSimplificada());
		leitores.put(CodigoMensagem.C0460, new ContextoRespostaCapturaSimplificada());
		leitores.put(CodigoMensagem.C9100, new ContextoRespostaErro(CodigoMensagem.C9100));
		leitores.put(CodigoMensagem.C9200, new ContextoRespostaErro(CodigoMensagem.C9200));
		leitores.put(CodigoMensagem.C9300, new ContextoRespostaErro(CodigoMensagem.C9300));
		leitores.put(CodigoMensagem.C9400, new ContextoRespostaErro(CodigoMensagem.C9400));
		leitores.put(CodigoMensagem.C9450, new ContextoRespostaErro(CodigoMensagem.C9450));
		leitores.put(CodigoMensagem.C9670, new ContextoRespostaErro(CodigoMensagem.C9670));
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends MensagemBasica> ContextoMensagem<T> loadContexto(CodigoMensagem codigo) {
		
		return (ContextoMensagem<T>) leitores.get(codigo);
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends MensagemBasica> ContextoMensagem<T> parseContexto(String mensagem) throws MensagemNaoEncontradaException {
		
		if(mensagem == null || mensagem.isEmpty()) {
			throw new MensagemNaoEncontradaException();
		}
		
		CodigoMensagem codigo = CodigoMensagem.parse(mensagem.substring(5, 9));
		if(!leitores.containsKey(codigo)) {
			
			throw new MensagemNaoEncontradaException(codigo.toString());
		}
		
		return (ContextoMensagem<T>) leitores.get(codigo);
	}
	
	public static <T extends MensagemBasica> MensagemBasica parse(String mensagem) throws MensagemNaoEncontradaException {
		
		return parseContexto(mensagem).ler(mensagem);
	}

}
