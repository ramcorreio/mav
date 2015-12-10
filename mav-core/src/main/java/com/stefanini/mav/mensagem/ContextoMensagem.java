package com.stefanini.mav.mensagem;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.stefanini.mav.es.ContextoEntradaSaida;
import com.stefanini.mav.es.MapeamentoNaoEncontrado;

public abstract class ContextoMensagem<M extends MensagemBasica> {
	
	private Class<M> clazz;
	
	protected final CodigoMensagem tipo;
	
	public ContextoMensagem(CodigoMensagem tipo, Class<M> clazz) {
		this.tipo = tipo;
		this.clazz = clazz;
	}
	
	/**
	 * Função para criar hash da string informada  
	 * @param senha
	 * @return
	 * @throws MensagemNaoEncontradaException 
	 */
    public static String md5(String input) throws MensagemNaoEncontradaException {
    	
    	String sen = "";  
        MessageDigest md = null;  
        try {  
            md = MessageDigest.getInstance("MD5");  
        } catch (NoSuchAlgorithmException e) {  
            throw new MensagemNaoEncontradaException(e); 
        }  
        BigInteger hash = new BigInteger(1, md.digest(input.getBytes()));  
        sen = hash.toString(16);              
        return sen;  
    }
    
    /**
     * Verificar se o fluxo foi definido corretamente
     * 
     * @param mensagem
     * 
     * @throws MensagemNaoEncontradaException 
     */
    private void verificarFluxo(M mensagem) throws MensagemNaoEncontradaException {
    	
    	if(mensagem.getCabecalho().getSentidoFluxo() == null) {
    		
    		throw new MensagemNaoEncontradaException("fluxo vazio");
    	}
    	
    }
	
	public M ler(String input) throws MensagemNaoEncontradaException {
		
		String hash = md5(input);
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
		
		M instance;
		try {
			instance = ContextoEntradaSaida.ler(input, clazz, new Object[]{hash, cabecalho});
			
		} catch (IllegalArgumentException | SecurityException | MapeamentoNaoEncontrado e) {
			throw new MensagemNaoEncontradaException("Classe " + clazz.getName() + " inválida", e);
		}
		
		verificarFluxo(instance);
		
		return instance;
	}
	
	public String escrever(M mensagem) throws MensagemNaoEncontradaException, MapeamentoNaoEncontrado {
		
		StringBuilder b = new StringBuilder();
		b.append(ContextoEntradaSaida.escrever(mensagem.getCabecalho()));
		b.append(ContextoEntradaSaida.escrever(mensagem));
		return b.toString();
	}
}
