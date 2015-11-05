package com.stefanini.mav.mensagem;

public class LeitorCapturaSimplificada extends LeitorMensagem<SolicitacaoCapturaSimplificada> {

	private DadosPessoais montarDadoPessoais(String input) {
		
		DadosPessoais dadosPessoais = new DadosPessoais();
		return dadosPessoais;
	}
	
	@Override
	public SolicitacaoCapturaSimplificada montarMensagem(String input, Cabecalho cabecalho) {
		
		DadosPessoais dadosPessoais = montarDadoPessoais(input);
		
		return new SolicitacaoCapturaSimplificada(cabecalho, dadosPessoais);
	}
}
