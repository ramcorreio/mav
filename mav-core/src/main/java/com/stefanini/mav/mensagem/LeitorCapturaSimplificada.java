package com.stefanini.mav.mensagem;

public class LeitorCapturaSimplificada extends LeitorMensagem<SolicitacaoCapturaSimplificada> {

	protected LeitorCapturaSimplificada(StringBuilder input, TipoMensagem tipo) {
		super(input, tipo);
	}

	@Override
	public SolicitacaoCapturaSimplificada montarMensagem(Cabecalho cabecalho) {
		
		SolicitacaoCapturaSimplificada capturaSimplificada = new SolicitacaoCapturaSimplificada(cabecalho);
		return capturaSimplificada;
	}

}
