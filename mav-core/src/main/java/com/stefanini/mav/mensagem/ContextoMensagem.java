package com.stefanini.mav.mensagem;

import com.stefanini.mav.es.ContextoEntradaSaida;
import com.stefanini.mav.es.MapeamentoNaoEncontrado;
import com.stefanini.mav.util.Utils;

public class ContextoMensagem<T extends MensagemBasica> {
	
	private Class<T> clazz;
	
	protected final CodigoMensagem tipo;
	
	protected ContextoMensagem(CodigoMensagem tipo, Class<T> clazz) {
		this.tipo = tipo;
		this.clazz = clazz;
	}
    
    /**
     * Verificar se o fluxo foi definido corretamente
     * 
     * @param mensagem
     * 
     * @throws MensagemNaoEncontradaException 
     */
    private void verificarFluxo(T mensagem) throws MensagemNaoEncontradaException {
    	
    	if(mensagem.getCabecalho().getSentidoFluxo() == null) {
    		
    		throw new MensagemNaoEncontradaException("fluxo vazio");
    	}
    	
    }
	
	public T ler(String input) throws MensagemNaoEncontradaException {
		
		String hash = Utils.md5(input);
		Cabecalho cabecalho;
		try {
			cabecalho = ContextoEntradaSaida.ler(input, Cabecalho.class, false, new Object[0]);
		} 
		catch (MapeamentoNaoEncontrado e) {
			
			throw new MensagemNaoEncontradaException(e);
		}

		if(!tipo.equals(cabecalho.getCodigo())) {
			throw new MensagemNaoEncontradaException("Tipo " + tipo + " inválido");
		}
		
		T instance;
		try {
			instance = ContextoEntradaSaida.ler(input, clazz, new Object[]{hash, cabecalho});
			
		} catch (IllegalArgumentException | SecurityException | MapeamentoNaoEncontrado e) {
			throw new MensagemNaoEncontradaException("Classe " + clazz.getName() + " inválida", e);
		}
		
		verificarFluxo(instance);
		
		return instance;
	}
	
	public String escrever(T mensagem) throws MensagemNaoEncontradaException, MapeamentoNaoEncontrado {
		
		StringBuilder b = new StringBuilder();
		b.append(ContextoEntradaSaida.escrever(mensagem.getCabecalho()));
		b.append(ContextoEntradaSaida.escrever(mensagem));
		return b.toString();
	}
}
