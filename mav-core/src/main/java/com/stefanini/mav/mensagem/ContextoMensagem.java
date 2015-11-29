package com.stefanini.mav.mensagem;

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;

import com.stefanini.mav.util.UtilsDate;

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
		c.setTamanho(lerInt(input, 0, 5));
		
		//0006 a 0009	Cod-Mensagem	4	N	Código da Mensagem
		c.setCodigo(CodigoMensagem.parse(lerStringCheia(input, 5, 4)));
		
		//0010 a 0015	Num-Transacao	6	N	Numero da Transação		X
		c.setNumeroTransacao(lerInt(input, 9, 6));
		
		//0016 a 0030	Num-Proposta	15	A	Numero da Proposta
		c.setNumeroProposta(lerString(input, 15, 15));
		
		//0031 a 0038	Cod-Usuario	8	A	Usuário responsável pela Mensagem ou Código do Funcionário do Lojista
		c.setCodigoUsuario(lerString(input, 30, 8));
		
		//0039 a 0043	Cod-Retorno	5	A	Código de Retorno
		c.setCodigoRetorno(lerString(input, 38, 5));
		
		//0044 a 0052	Cod-Lojista	9	N	Código do Lojista + Código da Filial
		c.setCodigoLojista(lerInt(input, 43, 9));
		
		//0053 a 0053	Num-Versao	1	A	Número da Versão do Layout
		c.setVersao(lerString(input, 52, 1));
		
		//0054 a 0083	Campo-Lojista	30	A	Uso exclusivo do lojista
		c.setCampoLojista(lerStringCheia(input, 53, 30));
		
		return c;
	}
	
	protected static void escreverBoolean(StringBuilder b, int tamanho, Boolean input) {
		escreverInt(b, tamanho, input ? 1 : 0);
	}
	
	protected static void escreverInt(StringBuilder b, int tamanho, Integer input) {
		b.append(String.format("%0" + tamanho + "d", input));
	}
	
	protected static void escreverString(StringBuilder b, int tamanho, String input) {
		b.append(escreverString(tamanho, input));
	}
	
	protected static String escreverString(int tamanho, String input) {
		return String.format("%-" + tamanho + "s" , input);
	}
	
	/**
	 * Função que fará a leitura do trecho definido da string sem remoção dos espaços em branco no início e fim
	 * 
	 * @param input String para leitura
	 * @param inicio posição onde inicia a leitura
	 * @param tamanho tamanho a ser lido
	 * @return Intervalo lido da string de entrada 
	 */
	protected static String lerStringCheia(String input, int inicio, int tamanho) {
		return input.substring(inicio, inicio + tamanho);
	}
	
	/**
	 * Função que fará a leitura do trecho definido da string removendo os espaços em branco no início e fim
	 * 
	 * @param input String para leitura
	 * @param inicio posição onde inicia a leitura
	 * @param tamanho tamanho a ser lido
	 * @return Intervalo lido da string de entrada 
	 */
	protected static String lerString(String input, int inicio, int tamanho) {
		return lerStringCheia(input, inicio, tamanho).trim();
	}
	
	/**
	 * Função que fará a leitura do trecho definido da string e converte para inteiro
	 * 
	 * @param input String para leitura
	 * @param inicio posição onde inicia a leitura
	 * @param tamanho tamanho a ser lido
	 * @return Intervalo lido da string de entrada 
	 */
	protected static Integer lerInt(String input, int inicio, int tamanho) {
		return Integer.valueOf(lerString(input, inicio, tamanho));
	}
	
	protected static Long lerLong(String input, int inicio, int tamanho) {
		return Long.valueOf(lerString(input, inicio, tamanho));
	}
	
	protected static Double lerDouble(String input, int inicio, int tamanho) throws ParseException {
		
		return lerDouble(input, inicio, tamanho, 2);
	}
	
	protected static Double lerDouble(String input, int inicio, int tamanho, int decimal) throws ParseException {
		
		int intval = tamanho - decimal;
		
		DecimalFormat formmatter = (DecimalFormat) DecimalFormat.getInstance();
		formmatter.setGroupingUsed(false);
		formmatter.setMaximumIntegerDigits(tamanho - decimal);
		formmatter.setMinimumFractionDigits(decimal);
		formmatter.setMaximumFractionDigits(decimal);
		String valor = lerString(input, inicio, tamanho);
		
		//montagem de valor com ponto
		String valorComPonto = valor.substring(0, intval);
		valorComPonto += Character.toString(formmatter.getDecimalFormatSymbols().getDecimalSeparator());
		valorComPonto += valor.substring(intval);
		
		return Double.valueOf(formmatter.parse(valorComPonto).doubleValue());
	}
	
	protected static Boolean lerBooleanString(String input, int inicio, String comparador) {
		try {
			String bool = lerString(input, inicio, 1);
			return bool.isEmpty() ? false : (bool.equals(comparador) ? true : false);	
		}
		catch(NullPointerException e) {
			return false;
		}
	}
	
	protected static Boolean lerBooleanString(String input, int inicio) {
		
		return lerBooleanString(input, inicio, "S");
	}
	
	protected static Boolean lerBoolean(String input, int inicio) {
		try {
			return Boolean.valueOf(lerInt(input, inicio, 1) != 0);	
		}
		catch(NumberFormatException e) {
			return false;
		}
	}
	
	protected static Date lerDataCurta(String input, int inicio) throws ParseException {
		return UtilsDate.parse("01" + lerString(input, inicio, 6));
	}
	
	protected static Date lerData(String input, int inicio) throws ParseException {
		return lerData(input, inicio, 8);
	}
	
	protected static Date lerData(String input, int inicio, int tamanho) throws ParseException {
		return UtilsDate.parse(lerString(input, inicio, tamanho));
	}
	
	protected static Date lerDataHota(String input, int inicio) throws ParseException {
		return UtilsDate.parseDateHora(lerString(input, inicio, 14));
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
	
	abstract void ler(String input, M mensagem) throws MensagemNaoEncontradaException;
	
	abstract void escrever(StringBuilder b, M mensagem) throws MensagemNaoEncontradaException;	
	
	//abstract void gerarErro(StringBuilder b, M mensagem) throws MensagemNaoEncontradaException;
}
