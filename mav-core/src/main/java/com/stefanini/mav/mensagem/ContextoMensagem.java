package com.stefanini.mav.mensagem;

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class ContextoMensagem<M extends MensagemBasica> {
	
	private Class<M> clazz;
	
	protected final CodigoMensagem tipo;
	
	public ContextoMensagem(CodigoMensagem tipo, Class<M> clazz) {
		this.tipo = tipo;
		this.clazz = clazz;
	}
	
	private Cabecalho lerCabecalho(String input) {
		
		Cabecalho c = new Cabecalho();
		
		//0001 a 0005	Tam-Mensagem	5	N	Tamanho da Mensagem
		c.setTamanho(Integer.valueOf(input.substring(0, 5)));
		
		//0006 a 0009	Cod-Mensagem	4	N	Código da Mensagem
		c.setCodigo(CodigoMensagem.parse(input.substring(5, 9)));
		
		//0010 a 0015	Num-Transacao	6	N	Numero da Transação		X
		c.setNumeroTransacao(Integer.valueOf(input.substring(9, 15)));
		
		//0016 a 0030	Num-Proposta	15	A	Numero da Proposta
		c.setNumeroProposta(input.substring(15, 30).trim());
		
		//0031 a 0038	Cod-Usuario	8	A	Usuário responsável pela Mensagem ou Código do Funcionário do Lojista
		c.setCodigoUsuario(input.substring(30, 38).trim());
		
		//0039 a 0043	Cod-Retorno	5	A	Código de Retorno
		c.setCodigoRetorno(input.substring(38, 43).trim());
		
		//0044 a 0052	Cod-Lojista	9	N	Código do Lojista + Código da Filial
		c.setCodigoLojista(Integer.valueOf(input.substring(43, 52)));
		
		//0053 a 0053	Num-Versao	1	A	Número da Versão do Layout
		c.setVersao(input.substring(52, 53));
		
		//0054 a 0083	Campo-Lojista	30	A	Uso exclusivo do lojista
		c.setCampoLojista(input.substring(53, 83).trim());
		
		return c;
	}
	
	protected Boolean lerBoolean(String input) {
		return Boolean.valueOf(Integer.valueOf(input) != 0);
	}
	
	protected void escreverBoolean(StringBuilder b, int tamanho, Boolean input) {
		escreverInt(b, tamanho, input ? 1 : 0);
	}
	
	protected void escreverInt(StringBuilder b, int tamanho, Integer input) {
		b.append(String.format("%0" + tamanho + "d", input));
	}
	
	protected void escreverString(StringBuilder b, int tamanho, String input) {
		b.append(String.format("%-" + tamanho + "s" , input));
	}
	
	private String escreverCabecalho(Cabecalho cabecalho) {
		
		StringBuilder b = new StringBuilder();
		//0001 a 0005	Tam-Mensagem	5	N	Tamanho da Mensagem
		escreverInt(b, 5, cabecalho.getTamanho());
		
		//0006 a 0009	Cod-Mensagem	4	N	Código da Mensagem
		escreverInt(b, 4, cabecalho.getCodigo().toInt());
		
		//0010 a 0015	Num-Transacao	6	N	Numero da Transação		X
		escreverInt(b, 6, cabecalho.getNumeroTransacao());
		
		//0016 a 0030	Num-Proposta	15	A	Numero da Proposta
		escreverString(b, 15, cabecalho.getNumeroProposta());
		
		//0031 a 0038	Cod-Usuario	8	A	Usuário responsável pela Mensagem ou Código do Funcionário do Lojista
		escreverString(b, 8, cabecalho.getCodigoUsuario());
		
		//0039 a 0043	Cod-Retorno	5	A	Código de Retorno
		escreverString(b, 5, cabecalho.getCodigoRetorno());
		
		//0044 a 0052	Cod-Lojista	9	N	Código do Lojista + Código da Filial
		escreverInt(b, 9, cabecalho.getCodigoLojista());
		
		//0053 a 0053	Num-Versao	1	A	Número da Versão do Layout
		escreverString(b, 1, cabecalho.getVersao());
		
		//0054 a 0083	Campo-Lojista	30	A	Uso exclusivo do lojista
		escreverString(b, 30, cabecalho.getCampoLojista());
		
		return b.toString();
		
	}
	
	/**
	 * Função para criar hash da string informada  
	 * @param senha
	 * @return
	 * @throws MensagemNaoEncontradaException 
	 */
    public static String md5(String input) throws MensagemNaoEncontradaException{  
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
		Cabecalho cabecalho = lerCabecalho(input);
		if(!tipo.equals(cabecalho.getCodigo())) {
			throw new MensagemNaoEncontradaException("Tipo " + tipo + " inválido");
		}
		
		
		M instance;
		try {
			instance = clazz.getDeclaredConstructor(String.class, Cabecalho.class).newInstance(hash, cabecalho);
			
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new MensagemNaoEncontradaException("Classe " + clazz.getName() + " inválida");
		}
		
		ler(input, instance);
		
		verificarFluxo(instance);
		
		return instance;
	}
	
	public String escrever(M mensagem) throws MensagemNaoEncontradaException {
		
		StringBuilder b = new StringBuilder();
		b.append(escreverCabecalho(mensagem.getCabecalho()));
		escrever(b, mensagem);
		return b.toString();
	}
	
	public abstract void ler(String input, M mensagem) throws MensagemNaoEncontradaException;
	
	public abstract void escrever(StringBuilder b, M mensagem) throws MensagemNaoEncontradaException;	
}
