package com.stefanini.mav.mensagem;


/**
 * 
 * 0001 a 0005	Tam-Mensagem	5	N	Tamanho da Mensagem
 * 0006 a 0009	Cod-Mensagem	4	N	Código da Mensagem = 0410
 * 0010 a 0015	Num-Transacao	6	N	Numero da Transação
 * 0016 a 0030	Num-Proposta	15	A	Numero da Proposta
 * 0031 a 0038	Cod-Usuario	8	A	Usuário responsável pela Mensagem ou Código do Funcionário do Lojista
 * 0039 a 0043	Cod-Retorno	5	A	Código de Retorno
 * 0044 a 0052	Cod-Lojista	9	N	Código do Lojista + Código da Filial
 * 0053 a 0053	Num-Versao	1	A	Número da Versão do Layout
 * 0054 a 0083	Campo-Lojista	30	A	Uso exclusivo do lojista
 * 
 * @author Rodrigo
 *
 */
public class Cabecalho {
	
	private Integer tamanho;
	
	private TipoMensagem tipo;
	
	private Integer numeroTransacao;
	
	private String numeroProposta;
	
	private String codigoUsuario;
	
	private String codigoRetorno;
	
	private Integer codigoLojista;
	
	private String versao;
	
	private String campoLojista;
	
	private Cabecalho() {
	}
	
	public static Cabecalho novo(StringBuilder input) {
		
		Cabecalho c = new Cabecalho();
		//0001 a 0005	Tam-Mensagem	5	N	Tamanho da Mensagem
		c.tamanho = Integer.valueOf(input.substring(0, 5));
		
		//0006 a 0009	Cod-Mensagem	4	N	Código da Mensagem
		c.tipo = TipoMensagem.parse(input.substring(5, 9));
		
		//0010 a 0015	Num-Transacao	6	N	Numero da Transação		X
		c.numeroTransacao = Integer.valueOf(input.substring(9, 15));
		
		//0016 a 0030	Num-Proposta	15	A	Numero da Proposta
		c.numeroProposta = input.substring(15, 30);
		
		//0031 a 0038	Cod-Usuario	8	A	Usuário responsável pela Mensagem ou Código do Funcionário do Lojista
		c.codigoUsuario = input.substring(30, 38);
		
		//0039 a 0043	Cod-Retorno	5	A	Código de Retorno
		c.codigoRetorno = input.substring(38, 43);
		
		//0044 a 0052	Cod-Lojista	9	N	Código do Lojista + Código da Filial
		c.codigoLojista = Integer.valueOf(input.substring(43, 52));
		
		//0053 a 0053	Num-Versao	1	A	Número da Versão do Layout
		c.versao = input.substring(52, 53);
		
		//0054 a 0083	Campo-Lojista	30	A	Uso exclusivo do lojista
		c.campoLojista = input.substring(53, 83);
		
		return c;
		
	}
	
	public Integer getTamanho() {
		return tamanho;
	}

	public TipoMensagem getTipo() {
		return tipo;
	}

	public Integer getNumeroTransacao() {
		return numeroTransacao;
	}

	public String getNumeroProposta() {
		return numeroProposta;
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public String getCodigoRetorno() {
		return codigoRetorno;
	}

	public Integer getCodigoLojista() {
		return codigoLojista;
	}
	
	public String getVersao() {
		return versao;
	}

	public String getCampoLojista() {
		return campoLojista;
	}

}
