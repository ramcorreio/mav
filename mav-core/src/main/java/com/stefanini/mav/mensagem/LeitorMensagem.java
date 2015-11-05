package com.stefanini.mav.mensagem;

public abstract class LeitorMensagem<T extends MensagemBasica> {
	
	private Cabecalho montarCabecalho(String input) {
		
		Cabecalho c = new Cabecalho();
		//0001 a 0005	Tam-Mensagem	5	N	Tamanho da Mensagem
		c.setTamanho(Integer.valueOf(input.substring(0, 5)));
		
		//0006 a 0009	Cod-Mensagem	4	N	Código da Mensagem
		c.setTipo(TipoMensagem.parse(input.substring(5, 9)));
		
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
	
	public T ler(String input, TipoMensagem tipo) throws MensagemNaoEncontradaException {
		
		Cabecalho cabecalho = montarCabecalho(input);
		
		if(!tipo.equals(cabecalho.getTipo())) {
			throw new MensagemNaoEncontradaException("Tipo " + tipo + " inválido");
		}
		
		return montarMensagem(input, cabecalho);
	}
	
	public abstract T montarMensagem(String input, Cabecalho cabecalho);

}
